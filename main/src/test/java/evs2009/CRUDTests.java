package evs2009;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CRUDTests {
	private Peer serverPeer;
	private Peer localPeer;
	private final String identifier = "someString";
	private final String testString = "testString";

	@Before
	public void setUp() {
		Application app1 = new Application("testsocket1");
		Application app2 = new Application("testsocket2");

		this.serverPeer = app2.getPeerLookup().lookup("testsocket1");
		this.localPeer = app1.getPeerLookup().lookup("testsocket2");
		
		serverPeer.login(Helper.correctUserName, Helper.correctPassword);
	}

	@After
	public void tearDown() {
		this.serverPeer.logout();
		this.localPeer = null;
	}

	@Test
	public void correctCreationAndRead() {
		insertObject();
		byte[] readBytes = serverPeer.read(identifier);
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
			serverPeer.read(identifier);
			fail();
		} catch (EppErrorException e) {
		}

		try {
			serverPeer.update(identifier, new byte[5]);
			fail();
		} catch (EppErrorException e) {
		}
		try {
			serverPeer.delete(identifier);
			fail();
		} catch (EppErrorException e) {
		}
		try {
			serverPeer.check(identifier);
			fail();
		} catch (EppErrorException e) {
		}
		try {
			serverPeer.transferRequest(identifier, "SomeToken");
			fail();
		} catch (EppErrorException e) {
		}
	}

	@Test
	public void updateCorrect() {
		insertObject();
		String newObject = "newString";
		serverPeer.update(identifier, newObject.getBytes());
		byte[] read = serverPeer.read(identifier);
		assertEquals(newObject, new String(read));
	}

	@Test
	public void deleteCorrect() {
		insertObject();
		serverPeer.delete(identifier);
		try {
			serverPeer.read(identifier);
			fail();
		} catch (EppErrorException e) {
		}
	}

	@Test
	public void checkCorrect() {
		insertObject();
		MetaData check = serverPeer.check(identifier);
		assertEquals(identifier, check.getName());
		assertEquals(getBytes().length, check.getSize());
	}

	@Test
	public void transferRequestCorrect() {
		insertObject();
		String token = "SomeToken";
		serverPeer.transferRequest(identifier, token);
		TransferRequest transferRequest = ((ServerPeerImpl)this.localPeer).getTransferRequest(identifier);
		assertEquals(identifier, transferRequest.getResource());
		assertEquals(token, transferRequest.getToken());
	}

	@Test(expected = EppErrorException.class)
	public void transferRequestExists() throws EppErrorException { 
		insertObject();
		String token = "SomeToken";
		serverPeer.transferRequest(identifier, token);
		serverPeer.transferRequest(identifier, token);
	}
	
	@Test(expected = EppErrorException.class)
	public void transferRequestMissing() throws EppErrorException {
		String token = "SomeToken";
		serverPeer.transferRequest("notExistend", token);
	}

	private void insertObject() {
		serverPeer.create(identifier, getBytes());
	}

	private byte[] getBytes() {
		return testString.getBytes();
	}
}
