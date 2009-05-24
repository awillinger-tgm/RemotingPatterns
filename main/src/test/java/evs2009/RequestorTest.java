package evs2009;

import org.junit.Before;
import org.junit.Test;

public class RequestorTest {

	private Requestor requestor = null;

	@Before
	public void setUp() {
		this.requestor = new Requestor(new OutputRequestHandler());
	}

	@Test
	public void testLogin() {
		requestor.login("TestUser", "TestPWD");
	}

	private static final class OutputRequestHandler implements RequestHandler {

		@Override
		public void send(byte[] epp) {
			System.out.println(new String(epp));
		}

	}
}
