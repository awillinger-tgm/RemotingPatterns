package evs2009;

import org.junit.Test;

public class SocketPluginTest {
	@Test
	public void socketPluginLookup() throws Exception {
		Application app1 = new Application("test3");
		Application app2 = new Application("test4");
		
		app2.getPeerLookup().lookup("test3").login("mike", "hammer");
		
	}
}
