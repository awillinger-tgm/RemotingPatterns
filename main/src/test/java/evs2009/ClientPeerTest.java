package evs2009;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ClientPeerTest {

	private ClientPeer peer;
	private EppCommunication comm;
	private ServerPeer mockPeer;

	@Before
	public void setup() throws Exception {
		this.mockPeer = Mockito.mock(ServerPeer.class);
		this.comm = new EppCommunication(mockPeer);
		this.peer = new ClientPeer(this.comm);
		peer.login("", "");
	}

	@After
	public void tearDown() {
		peer.logout();
	}

	@Test
	public void loginLogout() {
		peer.logout();
		peer.login("User", "PWD");
		verify(mockPeer).logout();
		Mockito.verify(mockPeer).login("User", "PWD");
		verify(mockPeer).logout();
	}

	@Test
	public void check() {
		MetaData oldMeta = new MetaData("Name", "Owner", 36);
		when(mockPeer.check("SomeItem")).thenReturn(oldMeta);
		peer.login("User", "PWD");
		MetaData meta = peer.check("SomeItem");
		verify(mockPeer).check("SomeItem");
		assertEquals(oldMeta, meta);
	}

	@Test
	public void create() {
		byte[] data = new byte[] { 1, 2, 3, 4, 5 };
		String name = "SomeItem";
		peer.create(name, data);
		verify(mockPeer).create(name, data);
	}
	@Test
	public void read() {
		final String name = "name";
		final byte[] data = new byte[] { 1, 2, 3, 4, 5 };
		when(mockPeer.read(name)).thenReturn(data);
		final byte[] result = peer.read(name);
		verify(mockPeer).read(name);
		assertEquals(data, result);
	} 
	
	@Test 
	public void update() {
		final String name = "name";
		final byte[] data = new byte[] { 1, 2, 3, 4, 5 };
		
		peer.update(name, data);
		verify(mockPeer).update(name, data);
		
	}
	@Test 
	public void delete() {
		final String name = "name";
		peer.delete(name);
		verify(mockPeer).delete(name);
	}

	@Test
	public void transferRequest() {
		final String name = "name";
		final String token = "token";
		peer.transferRequest(name, token);
		verify(mockPeer).transferRequest(name, token);
	}
	
	@Test
	public void transferCancel() {
		final String token = "token";
		peer.transferCancel(token);
		verify(mockPeer).transferCancel(token);
		
	}
	
	@Test 
	public void transferExecute() {
		final MetaData info = new MetaData("name", "owner", 10);
		final String token = "token";
		final byte[] data = new byte[] {0,1,2,3,4,5,6,7,8,9};
		
		peer.transferExecute(token, info, data);
		verify(mockPeer).transferExecute(token, info, data);
	}
	
}
