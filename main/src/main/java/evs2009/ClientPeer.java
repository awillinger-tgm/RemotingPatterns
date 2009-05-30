package evs2009;

import ietf.params.xml.ns.epp_1.EppType;
import ietf.params.xml.ns.epp_1.LoginType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
			throw new EppErrorException(EppErrorCode.XML_ERROR, e
				.getMessage(), e);
		}
	}

	@Override
	public MetaData check(String name) {

		Epp epp = MessageCreator.info(name, token, true);

		Epp response = send(epp);


		//TODO: response.getResponse()
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
		Epp request = MessageCreator.login(username, pw);
		Epp response = send(request);

		if (! response.getResponse().getResult().getCode().equals("1000")) {
			throw new EppErrorException(EppErrorCode.LOGIN_FAILED, "Login failed");
		}
		token = response.getResponse().getTrID().getSvTRID();

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

	private Epp send(Epp epp) {
		byte[] request = MessageCreator.marshall(context, epp);
		byte[] reponse = comm.invoke(request);
		return MessageCreator.unmarshall(context,reponse);
	}




}
