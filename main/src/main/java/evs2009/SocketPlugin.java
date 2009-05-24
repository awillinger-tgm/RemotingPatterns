package evs2009;

/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public class SocketPlugin implements ProtocolPlugin {

	@Override
	public ProtocolPluginClient getClient() {
		return new SocketPluginClient();
	}
	@Override
	public ProtocolPluginServer getServer() {
		return new SocketPluginServer();
	}

}
