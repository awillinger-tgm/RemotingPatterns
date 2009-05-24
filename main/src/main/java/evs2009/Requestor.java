package evs2009;

public class Requestor implements Peer {
	
	public Requestor(RequestHandler rh) {
	
	}

	@Override
	public MetaData check(String name) throws EppErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(String name, byte[] data) throws EppErrorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String name) throws EppErrorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void login(String username, String pw) throws EppErrorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logout() throws EppErrorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] read(String name) throws EppErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transferCancel(String token) throws EppErrorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferExecute(String token, MetaData info, byte[] data)
			throws EppErrorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferRequest(String name, String token)
			throws EppErrorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String name, byte[] data) throws EppErrorException {
		// TODO Auto-generated method stub
		
	}

}
