package evs2009;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import com.csvreader.*;

import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

/**
 * 
 * @author Michael Borko <michael@borko.at> Florian Motlik <flomotlik@gmail.com>
 *         Michael Greifeneder <mikegr@gmx.net>
 * 
 */
public class Lookup {

	private HashMap<String, String> peers = new HashMap<String, String>();

	public AbsolutObjectReference getReference(String name) {
		return new AbsolutObjectReference(name);
	}

	//private void init() {
	public static void main( String[] args) {
		CsvReader reader;
		try {
			reader = new CsvReader("peers.csv");

			reader.readHeaders();

			while (reader.readRecord()) {
				String peerID = reader.get("PeerID");
				String peerAddress = reader.get("PeerAddress");

				System.out.println("peerID: " + peerID);
				System.out.println("peerAddress: " + peerAddress);
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

}