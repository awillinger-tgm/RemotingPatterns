package comm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import com.csvreader.CsvReader;


public class Lookup {

	private HashMap<String, String> peers = new HashMap<String, String>();

	public Lookup(String path) {
		CsvReader reader;
		try {
			reader = new CsvReader(path+"peers.csv");

			reader.readHeaders();

			while (reader.readRecord()) {
				String peerID = reader.get("PeerID");
				String peerAddress = reader.get("PeerAddress");

				// System.out.println("peerID: " + peerID);
				// System.out.println("peerAddress: " + peerAddress);

				peers.put(peerID, peerAddress);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public AbsoluteObjectReference lookup(String peerName, String clazzName, String objectId) {
		AbsoluteObjectReference aor = new AbsoluteObjectReference();
		aor.setEndpoint(peers.get(peerName));
		aor.setObjectId(objectId);
		aor.setClazzName(clazzName);
		return aor;
	}
	public AbsoluteObjectReference lookup(String peerName) {
		return lookup(peerName, Communication.class.getName(), "peer");
	}
}
