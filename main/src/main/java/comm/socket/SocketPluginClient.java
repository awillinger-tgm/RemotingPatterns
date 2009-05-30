package comm.socket;

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
public class SocketPluginClient implements ProtocolPluginClient {

	private static final Logger log = LoggerFactory
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
	public byte[] sendData(AbsoluteObjectReference aor, byte[] data) throws ProtocolException {
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			os.write(String.valueOf(data.length).getBytes());
			os.write("\n".getBytes());
			log.debug("CLNT :: Size: " + data.length + " Data: " + data);
			os.write(data);
			os.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			int size = Integer.parseInt(reader.readLine());
			byte[] output = new byte[size];
			int i = is.read(output, 0, output.length);
			log.debug("CLNT :: Size: " + size + " Data: " + new String(output, 0, i));
			return output; 

		} catch (Exception e) {
			throw new ProtocolException(e);
		}
	}
}
