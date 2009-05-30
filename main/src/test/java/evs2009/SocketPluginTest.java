package evs2009;

import org.junit.Test;

public class SocketPluginTest {
	@Test
	public void socketPluginLookup() throws Exception {
		Application app1 = new Application("test1");
		Application app2 = new Application("test2");
		
		app2.getPeerLookup().lookup("test1").login("mike", "hammer");
		
	}
}
