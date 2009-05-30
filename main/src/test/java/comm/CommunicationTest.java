package comm;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CommunicationTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testname() throws Exception {
		MockBridge bridge = new MockBridge();
		MockBridge unused = new MockBridge();
		MockPlugin p1 = new MockPlugin();
		p1.setClient(bridge.getClientSide());
		p1.setServer(unused.getServerSide());
		
		MockPlugin p2 = new MockPlugin();
		p2.setServer(bridge.getServerSide());
		p2.setClient(unused.getClientSide());
		
		comm.ProtocolPlugin cplugin1 = new comm.socket.SocketPlugin(12345);
        //comm.ProtocolPlugin cplugin2 = new comm.soap.SOAPPlugin(23456);
		RequestHandler crh = new RequestHandler(new ProtocolPlugin[]{cplugin1});
		
		comm.ProtocolPlugin splugin1 = new comm.socket.SocketPlugin(12300);
        //comm.ProtocolPlugin splugin2 = new comm.soap.SOAPPlugin(23400);
		RequestHandler srh = new RequestHandler(new ProtocolPlugin[]{splugin1});
		
		MockCommunication con =  new MockCommunication();
		
		srh.register("comTest", con);
		
		Lookup lup = new Lookup("");
		AbsoluteObjectReference aor = lup.lookup("test2");
		Communication remote = (Communication) crh.getObject(aor);
		
		String testMsg = "Hallo";
		byte[] response = remote.invoke(testMsg.getBytes());
		
		assertEquals(testMsg, new String(response));
	}
}
