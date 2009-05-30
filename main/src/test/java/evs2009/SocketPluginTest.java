package evs2009;

import org.junit.Test;

public class SocketPluginTest {
	@Test
	public void socketPluginLookup() throws Exception {
		Lookup look = new Lookup();
		ClientPeer req = new ClientPeer(new SocketRequestHandler(
				"localhost:45445"));
		
		SocketPluginServer server = new SocketPluginServer();
		server.configure(45445, new Invoker() {

			@Override
			public byte[] handleRequest(byte[] data) {
				System.out.println(new String(data));
				return data;
			}

		});
		server.openServerPort();
		req.login("TestUser", "TestPW");
		System.out.println("TestCase finished");
	}
}
