package evs2009;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CRUDTests {
<<<<<<< HEAD:main/src/test/java/evs2009/CRUDTests.java
	private Peer serverPeer;
	private Peer localPeer;
	private Application app1;
	private Application app2;
=======
	
	private ServerPeer serverPeerInterface;
	private final String identifier = "someString";
>>>>>>> ff95588693db51fedc7a7d919b508cac83004d4e:main/src/test/java/evs2009/CRUDTests.java
	private final String testString = "testString";

	@Before
	public void setUp() {
<<<<<<< HEAD:main/src/test/java/evs2009/CRUDTests.java
		this.app1 = new Application("testsocket1");
		this.app2 = new Application("testsocket2");

		this.serverPeer = app2.getPeerLookup().lookup("testsocket1");
		this.localPeer = app1.getPeerLookup().lookup("testsocket2");
		
		serverPeer.login(Helper.correctUserName, Helper.correctPassword);
=======
		this.serverPeerInterface = new ServerPeerImpl("localPeer", new TransferRequestManager());
		serverPeerInterface.login(Helper.correctUserName,
				Helper.correctPassword);
>>>>>>> ff95588693db51fedc7a7d919b508cac83004d4e:main/src/test/java/evs2009/CRUDTests.java
	}

	@After
	public void tearDown() {
		this.serverPeer.logout();
		this.localPeer = null;
	}

	@Test
	public void correctCreationAndRead() {
		String identifier = "correctCreate";
		insertObject(identifier);
		byte[] readBytes = serverPeer.read(identifier);
		assertEquals(getBytes().length, readBytes.length);
		assertEquals(testString, new String(readBytes));
	}

	@Test(expected = EppErrorException.class)
	public void createAlreadyExisting() throws EppErrorException {
		String identifier = "createAlready";
		insertObject(identifier);
		insertObject(identifier);
	}

	@Test
	public void RUDdoesntExist() {
		String identifier = "RUD";
		insertObject(identifier);
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
<<<<<<< HEAD:main/src/test/java/evs2009/CRUDTests.java
		try {
			serverPeer.transferRequest(identifier, "SomeToken");
			fail();
		} catch (EppErrorException e) {
		}
=======
>>>>>>> ff95588693db51fedc7a7d919b508cac83004d4e:main/src/test/java/evs2009/CRUDTests.java
	}

	@Test
	public void updateCorrect() {
		String identifier = "updateCorrect";
		insertObject(identifier);
		String newObject = "newString";
		serverPeer.update(identifier, newObject.getBytes());
		byte[] read = serverPeer.read(identifier);
		assertEquals(newObject, new String(read));
	}

	@Test
	public void deleteCorrect() {
		String identifier = "deleteCorrect";
		insertObject(identifier);
		serverPeer.delete(identifier);
		try {
			serverPeer.read(identifier);
			fail();
		} catch (EppErrorException e) {
		}
	}

	@Test
	public void checkCorrect() {
		String identifier = "checkCorrect";
		insertObject(identifier);
		MetaData check = serverPeer.check(identifier);
		assertEquals(identifier, check.getName());
		assertEquals(getBytes().length, check.getSize());
	}

<<<<<<< HEAD:main/src/test/java/evs2009/CRUDTests.java
//	@Test
//	public void transferRequestCorrect() {
//		String identifier = "transferRequestCorrect";
//		insertObject(identifier);
//		String token = "SomeToken";
//		serverPeer.transferRequest(identifier, token);
//		TransferRequest transferRequest = app2.getEppCommunication().getServerImpl().getTransferRequest(identifier);
//		
//		assertEquals(token, transferRequest.getToken());
//	}

	@Test(expected = EppErrorException.class)
	public void transferRequestExists() throws EppErrorException { 
		String identifier = "transferRequest";
		insertObject(identifier);
		String token = "SomeToken";
		serverPeer.transferRequest(identifier, token);
		serverPeer.transferRequest(identifier, token);
	}
	
	@Test(expected = EppErrorException.class)
	public void transferRequestMissing() throws EppErrorException {
		String token = "SomeToken";
		serverPeer.transferRequest("notExistend", token);
	}

	private void insertObject(String identifier) {
		serverPeer.create(identifier, getBytes());
=======
	private void insertObject() {
		serverPeerInterface.create(identifier, getBytes());
>>>>>>> ff95588693db51fedc7a7d919b508cac83004d4e:main/src/test/java/evs2009/CRUDTests.java
	}

	private byte[] getBytes() {
		return testString.getBytes();
	}
}
