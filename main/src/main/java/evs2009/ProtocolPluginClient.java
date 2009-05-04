package evs2009;

/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public interface ProtocolPluginClient {

	/**
	 * Has to be called before send any data.
	 * @param peer
	 * @param port
	 */
	public void configure(String peer, int port) throws ProtocolException;

	/**
	 * sends and receives response
	 * @param data
	 * @return
	 */
	public byte[] sendData(byte[] data) throws ProtocolException;
}
