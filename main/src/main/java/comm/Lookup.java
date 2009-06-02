package comm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.csvreader.CsvReader;


public class Lookup {

	private HashMap<String, ArrayList<String> > peers = new HashMap<String, ArrayList<String>>();

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

				if( peers.get(peerID) == null )
					peers.put(peerID, new ArrayList<String>() );
				peers.get(peerID).add(peerAddress);
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
	
	public HashMap<String, String> getLocations(String peerName) {
		HashMap<String, String> list = new HashMap<String, String>();
		for( String loc : peers.get(peerName)) {
			list.put(loc.substring(0, loc.indexOf("://")), loc.substring(loc.indexOf("://") + 3));
		}
		return list;
	}
	
	public AbsoluteObjectReference lookup(String peerName, String clazzName, String objectId) {
		AbsoluteObjectReference aor = new AbsoluteObjectReference();
		aor.setEndpoint(peers.get(peerName).get(0));
		aor.setObjectId(objectId);
		aor.setClazzName(clazzName);
		return aor;
	}
	public AbsoluteObjectReference lookup(String peerName) {
		return lookup(peerName, Communication.class.getName(), "peer");
	}
}
