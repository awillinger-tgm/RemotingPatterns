package evs2009;

/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public interface ProtocolPluginServer {

	public void configure(int port, Invoker callback) throws ProtocolException;

	public void openServerPort() throws ProtocolException;

}
