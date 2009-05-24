package evs2009;

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

	private init() {
		CsvReader reader = new CsvReader("peers.csv");

		reader.readHeaders();

		while (reader.readRecord()) {
			String peerID = reader.get("PeerID");
			String peerAddress = reader.get("PeerAddress");

			System.out.println("peerID: "+peerID);
			System.out.println("peerAddress: "+peerAddress);
		}
		reader.close();
	}

}