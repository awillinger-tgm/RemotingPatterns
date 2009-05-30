package comm.socket;

import comm.ProtocolPlugin;
import comm.ProtocolPluginClient;
import comm.ProtocolPluginServer;

/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public class SocketPlugin implements ProtocolPlugin {

	public int port;
	
	public SocketPlugin(int port) {
		this.port = port;
	}
	
	@Override
	public ProtocolPluginClient getClient() {
		return new SocketPluginClient();
	}
	@Override
	public ProtocolPluginServer getServer() {
		return new SocketPluginServer(port);
	}

}
