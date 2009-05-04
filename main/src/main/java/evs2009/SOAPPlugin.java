package evs2009;

/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public class SOAPPlugin implements ProtocolPlugin {
	@Override
	public ProtocolPluginClient getClient() {
		return new SOAPPluginClient();
	}
	@Override
	public ProtocolPluginServer getServer() {
		return new SOAPPluginServer();
	}
}
