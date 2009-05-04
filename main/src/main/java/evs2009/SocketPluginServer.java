package evs2009;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public class SocketPluginServer implements ProtocolPluginServer {

	private static final Logger log = LoggerFactory.getLogger(SocketPluginServer.class);
	private int port;
	private Invoker invoker;

	@Override
	public void configure(int port, Invoker callback) {
		this.port = port;
		this.invoker = callback;
	}

	private final ExecutorService pool = Executors.newCachedThreadPool();

	@Override
	public void openServerPort() {
		try {
			while(true) {
				ServerSocket ss = new ServerSocket(port);
				final Socket socket = ss.accept();

				pool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							InputStream is = socket.getInputStream();
							OutputStream os = socket.getOutputStream();
							//invoker.handleRequest(data);

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
}
