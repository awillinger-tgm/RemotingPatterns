package comm.soap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import comm.*;

/**
 * 
 * @author Michael Borko<michael@borko.at>, Florian Motlik<flomotlik@gmail.com>,
 *         Michael Greifeneder <mikegr@gmx.net>
 * 
 */
public class SOAPPluginClient implements ProtocolPluginClient {

	private static final Logger log = LoggerFactory
			.getLogger(SOAPPluginClient.class);

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
	public byte[] sendData(AbsoluteObjectReference aor, byte[] data) throws ProtocolException {
		try {

			return null;
		} catch (Exception e) {
			throw new ProtocolException(e);
		}
	}
}
