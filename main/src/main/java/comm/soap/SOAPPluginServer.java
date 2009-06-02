package comm.soap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import comm.*;

/**
 *
 *@author Michael Borko<michael@borko.at>, Florian Motlik<flomotlik@gmail.com>,
 *        Michael Greifeneder <mikegr@gmx.net>
 * 
 */
public class SOAPPluginServer implements Runnable, ProtocolPluginServer {

	private static final Logger log = LoggerFactory
			.getLogger(SOAPPluginServer.class);
	private int port;
	private Invoker invoker;
	
	public SOAPPluginServer(int port) {
		this.port = port;
	}

	@Override
	public void configure(Invoker callback) {
		this.invoker = callback;
	}

	private final ExecutorService pool = Executors.newCachedThreadPool();

	@Override
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(port);
			while (true) {
				final Socket socket = ss.accept();

				pool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							log.debug("Running listener");
							InputStream is = socket.getInputStream();
							OutputStream os = socket.getOutputStream();
							BufferedReader reader = new BufferedReader(new InputStreamReader(is));
							String nextLine = reader.readLine();
							log.debug(nextLine);
							long size = Long.parseLong(nextLine);
							log.debug("SocketServer: " + size);
							byte[] output = new byte[(int) size];
							
							int i = is.read(output, 0, output.length);
							log.debug("Read: " + i + ";Data:"
									+ new String(output, 0, i));
							byte[] handleRequest = invoker
									.handleRequest(output);
							os.write(String.valueOf(handleRequest.length)
									.getBytes());
							os.write("\n".getBytes());
							os.write(handleRequest);
							os.flush();
						} catch (Exception e) {
							log.warn("Client socket problem", e);
						}
					}
				});
			}

		} catch (Exception e) {
			log.warn("ServerSocket problem", e);
		}
	}

	@Override
	public void openServer() {
		new Thread(this).start();
	}
}
