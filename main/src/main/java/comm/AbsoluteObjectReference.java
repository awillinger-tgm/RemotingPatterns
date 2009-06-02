package comm;

import comm.soap.SOAPPluginClient;
import comm.socket.SocketPluginClient;

public class AbsoluteObjectReference {

	public String clazzName;
	public String objectId;
	public String protocol;
	public String location;
	public ProtocolPluginClient pluginClient;

	/**
	 * @return the clazzName
	 */
	public String getClazzName() {
		return clazzName;
	}

	/**
	 * @param clazzName
	 *            the clazzName to set
	 */
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	/**
	 * @return the objectId
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId
	 *            the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return protocol + ":" + location;
	}

	/**
	 * @param endpoint
	 *            the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.protocol = endpoint.substring(0, endpoint.indexOf("::"));
		this.location = endpoint.substring(endpoint.indexOf("::") + 2);

		if (protocol.equals("soap"))
			this.setPluginClient(new SOAPPluginClient());
		else if (protocol.equals("socket"))
			this.setPluginClient(new SocketPluginClient());
	}

	public ProtocolPluginClient getPluginClient() {
		return pluginClient;
	}

	public void setPluginClient(ProtocolPluginClient plugin) {
		try {
			plugin.configure(location);
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pluginClient = plugin;
	}

}
