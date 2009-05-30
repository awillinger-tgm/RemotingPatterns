package evs2009;

public class Application {


	private final String peerId;

	public Application(String nameList, String peerId) {
		//TODO: handle nameList
		this.peerId = peerId;

		comm.ProtocolPlugin plugin1 = new comm.socket.SocketPlugin(12345);
        comm.ProtocolPlugin plugin2 = new comm.soap.SOAPPlugin(23456);
	}

	public void run() {

	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage Application <namelist> <peerId>");
		}
		new Application(args[0], args[1]).run();

	}
}
