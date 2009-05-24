package evs2009;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CRUDTests {
	private Peer peer;
	private String identifier = "someString";

	@Before
	private void setup() throws EppErrorException {
		this.peer = null;
		peer.login(Helper.correctUserName, Helper.correctPassword);
	}

	@After
	private void tearDown() throws EppErrorException {
		this.peer.logout();
		this.peer = null;
	}

	@Test
	private void correctCreation() throws EppErrorException {
		String testString = "testString";
		byte[] sentBytes = testString.getBytes();
		peer.create(identifier, sentBytes);
		byte[] readBytes = peer.read(identifier);
		assertEquals(sentBytes.length, readBytes.length);
		assertEquals(testString, new String(readBytes));
	}

	@Test
	private void createAlreadyExisting() {

	}
}
