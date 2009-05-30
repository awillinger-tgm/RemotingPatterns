package comm;


public class RequestHandler {

	ProtocolPlugin[] plugins;
	Invoker invoker;
	Requestor requestor;

	public RequestHandler(ProtocolPlugin[] plugins) throws ProtocolException {
		this.plugins = plugins;
		// invoker = new Invoker(plugin.getServer());
		ProtocolPluginServer[] pluginServer = new ProtocolPluginServer[plugins.length];
		for( int i=0; i<plugins.length; i++ ) {
			pluginServer[i] = plugins[i].getServer();
		}
		invoker = new Invoker(pluginServer);
		//requestor = new Requestor(plugin.getClient());
	}

	public void register(String id, Object object) {
		invoker.register(id, object);
	}

	public Object getObject(AbsoluteObjectReference aor)
			throws ClassNotFoundException {
		requestor = new Requestor(aor.getPluginClient());
		return requestor.getObject(aor);
	}
}
