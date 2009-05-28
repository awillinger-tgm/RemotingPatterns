package comm;

public class RequestHandler {

	ProtocolPlugin plugin;
	Invoker invoker;
	Requestor requestor;
	public RequestHandler(ProtocolPlugin plugin) throws ProtocolException {
		this.plugin = plugin;
		invoker = new Invoker(plugin.getServer());
		requestor = new Requestor(plugin.getClient());
	}
	
	public void register(String id, Object object) {
		invoker.register(id, object);
	}
	
	public Object getObject(AbsoluteObjectReference aor) throws ClassNotFoundException {
		return requestor.getObject(aor);
	}
}
