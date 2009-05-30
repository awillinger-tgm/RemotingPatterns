package comm.socket;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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
			//log.debug("CLNT :: Size: " + data.length + " Data: " + data);
			
			os.write(data);
			os.flush();
			
			long size = SocketPlugin.readSize(is);
			log.info("Client receives bytes:" + size);
			byte[] output = new byte[4096];

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			int i = 0;
			long read = 0;
			
			while ((i = is.read(output)) != -1) {
				//log.debug("Got bytes" + i);
				read += i;
				//log.debug("Read is " + read);
				bos.write(output, 0, i);
				if (read >= size) {
					break;
				}
			}
			
			is.close();
			os.close();
			
			return bos.toByteArray(); 

		} catch (Exception e) {
			throw new ProtocolException(e);
		}
	}
}
