package evs2009;

import java.util.Date;
import java.util.HashMap;

public class PeerImpl implements Peer {

	private final HashMap<String, Resource> resources = new HashMap<String, Resource>();
	private final HashMap<String, TransferRequest> transferRequests = new HashMap<String, TransferRequest>();

	//TODO: set self string
	private String self;
	private String other;


	@Override
	public MetaData check(String name) throws EppErrorException {
		Resource resource = resources.get(name);
		if (resource == null) {
			throw new EppErrorException(EppErrorCode.RESOURCE_NOT_FOUND, "");
		}
		return resource.getMetaData();
	}

	@Override
	public void create(String name, byte[] data) throws EppErrorException {
		if (resources.containsKey(name)) {
			throw new EppErrorException(EppErrorCode.RESOURCE_EXISTS, "");
		}
		Resource resource = new Resource();
		resource.setData(data);
		resource.setId(name);
		resource.setMetaData(new MetaData(name, self, data.length));
		resources.put(name, resource);
	}

	@Override
	public void delete(String name) throws EppErrorException {

		if (!resources.containsKey(name)) {
			throw new EppErrorException(EppErrorCode.RESOURCE_NOT_FOUND,"");
		}
		//TODO: check permission
		resources.remove(name);
	}

	@Override
	public void login(String username, String pw) throws EppErrorException {
		//accepting everyone
	}

	@Override
	public void logout() throws EppErrorException {
		//ignore
	}

	@Override
	public byte[] read(String name) throws EppErrorException {
		Resource resource = resources.get(name);
		if (resource == null) {
			throw new EppErrorException(EppErrorCode.RESOURCE_NOT_FOUND, "");
		}
		return resource.getData();
	}

	@Override
	public void transferCancel(String token) throws EppErrorException {
		if (transferRequests.containsKey(token)) {
			throw new EppErrorException(EppErrorCode.TOKEN_NOT_FOUND, "");
		}
		transferRequests.remove(token);
	}

	@Override
	public void transferExecute(String token, MetaData info, byte[] data)
			throws EppErrorException {

	}

	@Override
	public void transferRequest(String name, String token)
			throws EppErrorException {
		transferRequests.put(token, new TransferRequest(other, name, token));
	}

	@Override
	public void update(String name, byte[] data) throws EppErrorException {
		Resource resource = resources.get(name);
		if (resource == null) {
			throw new EppErrorException(EppErrorCode.RESOURCE_NOT_FOUND,"");
		}
		resource.setData(data);
		MetaData metaData = resource.getMetaData();
		metaData.setLastModifcation(new Date());
		metaData.setSize(data.length);
	}

}
