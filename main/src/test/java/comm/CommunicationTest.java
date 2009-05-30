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

		RequestHandler crh = new RequestHandler(p1);
		RequestHandler srh = new RequestHandler(p2);

		MockCommunication con =  new MockCommunication();

		srh.register("test", con);

		AbsoluteObjectReference aor = Lookup.lookup("socket://localhost", Communication.class.getName(), "test");
		Communication remote = (Communication) crh.getObject(aor);

		String testMsg = "Hallo";
		byte[] response = remote.invoke(testMsg.getBytes());
		assertEquals(testMsg, new String(response));

	}
}
