package evs2009;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import comm.Communication;

import evs2009.mapping.Epp;
import evs2009.mapping.MessageCreator;

public class ClientPeer implements Peer {

	private final Communication comm;
	private final JAXBContext context;

	private String token;

	public ClientPeer(Communication comm) {
		this.comm = comm;
		try {
			context = JAXBContext.newInstance(Epp.class);
		} catch (JAXBException e) {
			throw new EppErrorException(EppErrorCode.XML_ERROR, e.getMessage(),
					e);
		}
	}

	@Override
	public MetaData check(String name) {

		Epp epp = MessageCreator.info(name, token, true);

		Epp response = send(epp);

		byte[] data = response.getResponse().getResData().getInfData()
				.getData();
		try {
			MetaData md = MetaData.unserialize(data);
			return md;
		} catch (Exception e) {
			throw new EppErrorException(EppErrorCode.SERIALIZATION_ERROR,
					"Metadata unserialize failed", e);
		}
	}

	@Override
	public void create(String name, byte[] data) {
		Epp request = MessageCreator.create(name, data, token);
		Epp response = send(request);
		checkResponse(response, "1000", EppErrorCode.PERMISSION_DENIED,
				"Whatever");

	}

	private void checkResponse(Epp response, String code,
			EppErrorCode errorCode, String message) {
		if (!response.getResponse().getResult().getCode().equals(code)) {
			throw new EppErrorException(errorCode, message);
		}
	}

	@Override
	public void delete(String name) {
		Epp request = MessageCreator.delete(name, this.token);
		Epp response = send(request);
		checkResponse(response, "1000", EppErrorCode.PERMISSION_DENIED,
				"Delete Failed");
	}

	@Override
	public void login(String username, String pw) {
		Epp request = MessageCreator.login(username, pw);
		Epp response = send(request);

		checkResponse(response, "1000", EppErrorCode.LOGIN_FAILED,
				"Login Failed");
		token = response.getResponse().getTrID().getSvTRID();

	}

	@Override
	public void logout() {
		Epp request = MessageCreator.logout(token);
		Epp response = send(request);
		this.token = null;
	}

	@Override
	public byte[] read(String name) {
		Epp request = MessageCreator.info(name, this.token, false);
		Epp response = send(request);
		checkResponse(response, "1000", EppErrorCode.Internal_Server_Error,
				"Read Error");
		return response.getResponse().getResData().getInfData().getData();
	}

	@Override
	public void transferCancel(String name) {
		Epp request = MessageCreator.transferCancel(name, this.token);
		Epp response = send(request);
		checkResponse(response, "1000", EppErrorCode.PERMISSION_DENIED,
				"Message");
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
		Epp request = MessageCreator.update(name, data, this.token);
		Epp response = send(request);
		checkResponse(response, "1000", EppErrorCode.PERMISSION_DENIED,
				"Not possible");
	}

	private Epp send(Epp epp) {
		byte[] request = MessageCreator.marshall(context, epp);
		byte[] reponse = comm.invoke(request);
		return MessageCreator.unmarshall(context, reponse);
	}
}
