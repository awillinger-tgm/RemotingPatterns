package evs2009;

import static  org.junit.Assert.*;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;

public class ApplicationTest {
	@Test
	public void generalTest() throws Exception {
		Application app1 = new Application("test3");
		Application app2 = new Application("test4");
		
		Peer peer1from2 = app2.getPeerLookup().lookup("test3");
		Peer peer2from1 = app1.getPeerLookup().lookup("test4");
		
		byte[] testData = {1,2,3,4,5,6,7,8,9,0};
		String name = "TestData";
		String newName = "TransferFile";
		String user = "mike";
		peer1from2.login(user, "hammer");
		Date before = new Date();
		peer1from2.create("TestData", testData);
		Date after = new Date();
		MetaData metaData = peer1from2.check(name);
		
		assertEquals(testData.length, metaData.getSize());
		assertEquals(user, metaData.getOwner());
		assertEquals(user, metaData.getLastModifier());
		
		
		check(before, after, metaData.getCreationDate());
		
		assertArrayEquals(testData, peer1from2.read(name));
		
		byte[] newData = {42,37,36, 8,15,47,11};
		Date before2 = new Date();;
		peer1from2.update(name, newData);
		Date after2 = new Date();
		assertArrayEquals(newData, peer1from2.read(name));
		
		MetaData md2 = peer1from2.check(name);
		check(before, after, md2.getCreationDate()); //check creation date is still ok
		check(before2, after2, md2.getLastModifcation());
	
		peer1from2.delete(name);
		
		try {
			peer1from2.read(name);
			fail("read must throw exception");
		} catch (Exception e) {
			
		}
		peer1from2.create(newName, testData);
		
		/**
		 *  Almost done!!
		peer1from2.transferRequest(name, UUID.randomUUID().toString());
		
		Thread.sleep(2000);
		assertArrayEquals(testData, peer2from1.read(newName));
		 */
		
	}
	
	private void check(Date before, Date after, Date between) {
		assertTrue(between.after(before));
		assertTrue(between.before(after));
	}
}
