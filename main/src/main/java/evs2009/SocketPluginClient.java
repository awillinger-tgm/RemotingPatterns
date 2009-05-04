package evs2009;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public class SocketPluginClient implements ProtocolPluginClient {



	private static final Logger logger = LoggerFactory
			.getLogger(SocketPluginClient.class);

	private Socket socket;
	@Override
	public void configure(String peer, int port) throws ProtocolException {
		try {
			this.socket = new Socket(peer, port);
		} catch (Exception e) {
			throw new ProtocolException(e);
		}
	}

	@Override
	public byte[] sendData(byte[] data) throws  ProtocolException{
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();


		} catch (Exception e) {
			throw new ProtocolException(e);
		}
		return null;
	}

}
