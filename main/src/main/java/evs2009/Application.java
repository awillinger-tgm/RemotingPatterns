package evs2009;

import java.awt.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csvreader.CsvReader;

import comm.ProtocolException;
import comm.ProtocolPlugin;
import comm.RequestHandler;

public class Application {

	private static final Logger log = LoggerFactory
			.getLogger(Application.class);

	private final String peerId;
	private Lookup peerLookup;

	public Application(String peerId) {

		// TODO: handle nameList
		this.peerId = peerId;

		comm.Lookup commLookup = new comm.Lookup("");
		HashMap<String, String> list = commLookup.getLocations(peerId);

		ArrayList<ProtocolPlugin> plugins = new ArrayList<ProtocolPlugin>();
		
		String location1 = list.get("socket");
		if (location1 != null) {
			String[] splitted1 = location1.split(":");
			comm.ProtocolPlugin plugin1 = new comm.socket.SocketPlugin(Integer
					.parseInt(splitted1[1]));
			plugins.add(plugin1);
		}

		String location2 = list.get("soap");
		if (location2 != null) {
			String[] splitted2 = location2.split(":");
			comm.ProtocolPlugin plugin2 = new comm.soap.SOAPPlugin(Integer
					.parseInt(splitted2[1].substring(0, splitted2[1]
							.indexOf("/"))));
			plugins.add(plugin2);
		}

		try {
			comm.RequestHandler rh = new comm.RequestHandler(plugins);
			rh.register("peer", new EppCommunication());
			this.peerLookup = new Lookup(commLookup, rh);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Lookup getPeerLookup() {
		return peerLookup;
	}

	public void run() {
		peerLookup.lookup("test2").login("test1", "pw");
	}

	public static void main(String[] args) {
		log.info("Starting application");
		if (args.length < 1) {
			System.out.println("Usage Application <namelist> <peerId>");
		}
		new Application(args[0]).run();
		log.info("Application finished");
	}
}
