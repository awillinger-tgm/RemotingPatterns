package evs2009;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public class SOAPPluginClient implements ProtocolPluginClient {


	private static final Logger logger = LoggerFactory
			.getLogger(SOAPPluginClient.class);

	@Override
	public void configure(String peer, int port) {
		// TODO Auto-generated method stub

	}
	@Override
	public byte[] sendData(byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}

}
