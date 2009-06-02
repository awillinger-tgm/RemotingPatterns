package evs2009;

import org.junit.Before;
import org.junit.Test;

import comm.Communication;

public class RequestorTest {

	private ClientPeer clientPeer = null;

	@Before
	public void setUp() {
		this.clientPeer = new ClientPeer(new OutputRequestHandler());
	}

	@Test
	public void testLogin() {
		clientPeer.login("TestUser", "TestPWD");
	}

	private static final class OutputRequestHandler implements Communication {

		@Override
		public byte[] invoke(byte[] request) {
			System.out.println(new String(request));
			return null;
		}

	}
}
