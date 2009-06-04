package evs2009;

import javax.xml.bind.JAXBContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import comm.Communication;

import evs2009.mapping.Epp;
import evs2009.mapping.Login;
import evs2009.mapping.MessageCreator;
import evs2009.mapping.ObjectData;

public class EppCommunication implements Communication {

	private static final Logger log = LoggerFactory
			.getLogger(EppCommunication.class);
	// TODO: configure injection of peerimpl
	private static TransactionManager tm = new TransactionManager(
			new ServerPeerImpl());

	public EppCommunication() throws Exception {
		context = JAXBContext.newInstance(Epp.class);
	}

	EppCommunication(ServerPeer peer) throws Exception {
		this();
		tm = new TransactionManager(peer);
	}

	private JAXBContext context;

	@Override
	public byte[] invoke(byte[] request) {
		Epp epp = MessageCreator.unmarshall(context, request);
		switch (epp.getCommand().getCommandType()) {
		case Login:
			return login(epp);
		case Info:
			return info(epp);
		case Logout:
			return logout(epp);
		case Create:
			return create(epp);
		case Delete:
			return delete(epp);
		case Update:
			return update(epp);
		case TransferCancel:
			return transferCancel(epp);
		case TransferExecute:
//			return transferExecute(epp);
		}
		return null;
	}

//	private byte[] transferExecute(Epp epp) {
//		SessionPeer session = getSession(epp);
//		String name = epp.getCommand().getTransfer().getTransfer().getName();
//		MetaData meta = epp.getCommand().getTransfer().getTransfer().getData();
//		session.transferExecute(name, );
//		return MessageCreator.marshall(context, MessageCreator
//				.transferCancelResponse("1000", "Canceled successfully",
//						getToken(epp), getToken(epp)));
//	}

	private byte[] transferCancel(Epp epp) {
		SessionPeer session = getSession(epp);
		String name = epp.getCommand().getTransfer().getTransfer().getName();
		session.transferCancel(name);
		return MessageCreator.marshall(context, MessageCreator
				.transferCancelResponse("1000", "Canceled successfully",
						getToken(epp), getToken(epp)));
	}

	private byte[] update(Epp epp) {
		SessionPeer session = getSession(epp);
		String name = epp.getCommand().getUpdate().getUpdate().getName();
		byte[] data = epp.getCommand().getUpdate().getUpdate().getData();
		session.update(name, data);
		return MessageCreator.marshall(context, MessageCreator.updateResponse(
				"1000", "Some Message", getToken(epp), getToken(epp)));
	}

	private byte[] delete(Epp epp) {
		SessionPeer session = getSession(epp);
		String name = epp.getCommand().getDelete().getDelete().getName();
		log.debug("Delete in EPPCommunication called: " + name);
		session.delete(name);
		return MessageCreator.marshall(context, MessageCreator.deleteResponse(
				"1000", "Delete Correct", getToken(epp), getToken(epp)));
	}

	private byte[] create(Epp epp) {
		SessionPeer session = getSession(epp);
		String name = epp.getCommand().getCreate().getCreate().getName();
		byte[] data = epp.getCommand().getCreate().getCreate().getData();
		session.create(name, data);
		return MessageCreator.marshall(context, MessageCreator.createResponse(
				"1000", "Object Created", name, getToken(epp), getToken(epp)));
	}

	private byte[] logout(Epp epp) {
		SessionPeer session = getSession(epp);
		session.logout();
		return MessageCreator.marshall(context, MessageCreator.logoutResponse(
				"1500", "Logged Out correctly", getToken(epp), getToken(epp)));
	}

	private SessionPeer getSession(Epp epp) {
		return tm.getSession(getToken(epp));
	}

	private String getToken(Epp epp) {
		return epp.getCommand().getClTRID();
	}

	private byte[] login(Epp epp) {
		Login login = epp.getCommand().getLogin();
		Epp response = null;
		try {
			String token = tm.createToken();
			SessionPeer peer = tm.getSession(token);
			peer.login(login.getClID(), login.getPw());
			response = MessageCreator.loginResponse("1000", "Login successful",
					token, token);
		} catch (Exception e) {
			response = MessageCreator.loginResponse("2400", "Login failed", "",
					"");

		}
		return MessageCreator.marshall(context, response);
	}

	private byte[] info(Epp epp) {
		ObjectData info = epp.getCommand().getInfo().getInfo();
		String token = getToken(epp);
		SessionPeer peer = tm.getSession(token);
		String name = info.getName();
		Epp response = null;
		if (info.getOnlyMetadata()) {
			MetaData md = peer.check(name);
			try {
				byte[] data = MetaData.serialize(md);
				log.debug("MetaData size:" + data.length);
				response = MessageCreator.infoResponse("1000", "lalelu", name,
						data, name, token, token);
			} catch (Exception e) {
				log.debug("MetaData.serialize failed", e);
				response = MessageCreator.infoResponse("2400",
						"MetaData.serialize failed", name, null, name, token,
						token);
			}
		} else {
			byte[] data = peer.read(name);
			response = MessageCreator.infoResponse("1000", "lalelu", name,
					data, name, token, token);
		}
		return MessageCreator.marshall(context, response);

	}

}
