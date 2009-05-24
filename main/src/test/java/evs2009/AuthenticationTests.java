package evs2009;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationTests {

	private Peer peer = null;

	@Before
	public void setUp() throws Exception {
		// TODO setup peer
		peer = null;
	}

	@After
	public void tearDown() throws Exception {
		peer = null;
	}

	@Test
	public void correctLogin() throws Exception {
		peer.login(Helper.correctPassword, Helper.correctPassword);
		peer.logout();
	}

	@Test(expected = EppErrorException.class)
	public void wrongUserName() throws EppErrorException {
		peer.login("wrongUserName", "wrongPassword");
	}

	@Test(expected = EppErrorException.class)
	public void wrongPassword() throws EppErrorException {
		peer.login(Helper.correctUserName, "wrongPassword");
	}

	@Test(expected = EppErrorException.class)
	public void logoutNotLoggedIn() throws EppErrorException {
		peer.logout();
	}
}
