package evs2009;

import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.LoginType;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Requestor implements Peer {

	private final RequestHandler handler;

	public Requestor(RequestHandler rh) {
		this.handler = rh;
	}

	@Override
	public MetaData check(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(String name, byte[] data) {

	}

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void login(String username, String pw) {
		LoginType type = new LoginType();
		type.setClID(username);
		type.setPw(pw);
		try {
			JAXBContext context = JAXBContext.newInstance(LoginType.class,
					EppType.class);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(new EppType(type), out);
			handler.send(out.toByteArray());
		} catch (JAXBException e) {
			throw new EppErrorException(EppErrorCode.LOGIN_FAILED, e
					.getMessage(), e);
		}
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] read(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transferCancel(String token) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transferExecute(String token, MetaData info, byte[] data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transferRequest(String name, String token) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(String name, byte[] data) {
		// TODO Auto-generated method stub

	}

}
