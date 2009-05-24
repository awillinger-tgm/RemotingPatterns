package evs2009;

import java.io.Serializable;

/**
 *
 *@author Michael Borko<michael@borko.at>, Florian Motlik<flomotlik@gmail.com>,
 *        Michael Greifeneder <mikegr@gmx.net>
 *
 */
public class SocketRequestHandler implements Serializable, RequestHandler {

	private final String ip;
	private final int port;

	public SocketRequestHandler(String name) {
		String[] splitted = name.split(":");
		this.ip = splitted[0];
		this.port = Integer.parseInt(splitted[1]);
		System.out.println("IP: " + this.ip + " " + "Port: " + port);
	}

	@Override
	public void send(byte[] epp) {
		ProtocolPluginClient socket = new SocketPluginClient();
		try {
			socket.configure(ip, port);
			System.out.println("RequestHandler: " + epp.length);
			socket.sendData(epp);
		} catch (ProtocolException e) {
			throw new EppErrorException(EppErrorCode.Internal_Server_Error, e
					.getMessage(), e);
		}
	}

}