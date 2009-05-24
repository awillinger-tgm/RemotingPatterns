package evs2009;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CRUDTests {
	private Peer serverPeerInterface;
	private PeerImpl localPeer;
	private final String identifier = "someString";
	private final String testString = "testString";

	@Before
	public void setUp() {
		this.serverPeerInterface = null;
		this.localPeer = new PeerImpl();
		serverPeerInterface.login(Helper.correctUserName,
				Helper.correctPassword);
	}

	@After
	public void tearDown() {
		this.serverPeerInterface.logout();
		this.serverPeerInterface = null;
	}

	@Test
	public void correctCreationAndRead() {
		insertObject();
		byte[] readBytes = serverPeerInterface.read(identifier);
		assertEquals(getBytes().length, readBytes.length);
		assertEquals(testString, new String(readBytes));
	}

	@Test(expected = EppErrorException.class)
	public void createAlreadyExisting() {
		insertObject();
		insertObject();
	}

	@Test
	public void RUDdoesntExist() {
		try {
			serverPeerInterface.read(identifier);
			fail();
		} catch (EppErrorException e) {
		}

		try {
			serverPeerInterface.update(identifier, new byte[5]);
			fail();
		} catch (EppErrorException e) {
		}
		try {
			serverPeerInterface.delete(identifier);
			fail();
		} catch (EppErrorException e) {
		}
		try {
			serverPeerInterface.check(identifier);
			fail();
		} catch (EppErrorException e) {
		}
		try {
			serverPeerInterface.transferRequest(identifier, "SomeToken");
			fail();
		} catch (EppErrorException e) {
		}
	}

	@Test
	public void updateCorrect() {
		insertObject();
		String newObject = "newString";
		serverPeerInterface.update(identifier, newObject.getBytes());
		byte[] read = serverPeerInterface.read(identifier);
		assertEquals(newObject, new String(read));
	}

	@Test
	public void deleteCorrect() {
		insertObject();
		serverPeerInterface.delete(identifier);
		try {
			serverPeerInterface.read(identifier);
			fail();
		} catch (EppErrorException e) {
		}
	}

	@Test
	public void checkCorrect() {
		insertObject();
		MetaData check = serverPeerInterface.check(identifier);
		assertEquals(identifier, check.getName());
		assertEquals(getBytes().length, check.getSize());
	}

	@Test
	public void transferRequestCorrect() {
		insertObject();
		String token = "SomeToken";
		serverPeerInterface.transferRequest(identifier, token);
		TransferRequest transferRequest = this.localPeer
				.getTransferRequest(identifier);
		assertEquals(identifier, transferRequest.getResource());
		assertEquals(token, transferRequest.getToken());
	}

	@Test
	public void transferExecuteCorrect() {
		// TODO
	}

	private void insertObject() {
		serverPeerInterface.create(identifier, getBytes());
	}

	private byte[] getBytes() {
		return testString.getBytes();
	}
}
