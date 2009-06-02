package evs2009;

import javax.xml.bind.JAXBContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import comm.Communication;
import evs2009.mapping.CheckData;
import evs2009.mapping.Epp;
import evs2009.mapping.Login;
import evs2009.mapping.MessageCreator;
import evs2009.mapping.ObjectData;
import evs2009.mapping.CheckData.CheckDataInternal;

public class EppCommunication implements Communication {

	private static final Logger log = LoggerFactory
			.getLogger(EppCommunication.class);
	//TODO: configure injection of peerimpl
	private static TransactionManager tm = new TransactionManager(new PeerImpl());

	public EppCommunication() throws Exception {
		context = JAXBContext.newInstance(Epp.class);
	}
	
	private JAXBContext context;

	@Override
	public byte[] invoke(byte[] request) {
		Epp epp = MessageCreator.unmarshall(context, request);
		switch(epp.getCommand().getCommandType()) {
			case Login: return login(epp);
			case Info: return info(epp);
		}
		return null;
	}

	private byte[] login(Epp epp) {
		Login login = epp.getCommand().getLogin();
		Epp response = null;
		try {
			String token = tm.createToken();
			SessionPeer peer = tm.getSession(token);
			peer.login(login.getClID(), login.getPw());
			response = MessageCreator.loginResponse("1000", "Login successful", token, token);
		} catch (Exception e) {
			response = MessageCreator.loginResponse("2400", "Login failed", "", "");

		}
		return MessageCreator.marshall(context, response);
	}

	private byte[] info(Epp epp) {
		ObjectData info = epp.getCommand().getInfo().getInfo();
		String token = epp.getCommand().getClTRID();
		SessionPeer peer = tm.getSession(token);
		String name = info.getName();
		Epp response = null;
		if (info.getOnlyMetadata()){
			MetaData md = peer.check(name);
			try {
				byte[] data = MetaData.serialize(md);
				response = MessageCreator.infoResponse("1000", "lalelu", name, data, name, token, token);
			} catch (Exception e) {
				log.debug("MetaData.serialize failed", e);
				response = MessageCreator.infoResponse("2400", "MetaData.serialize failed", name, null, name, token, token);
			}
		}
		else {
			byte[] data = peer.read(name);
			response = MessageCreator.infoResponse("1000", "lalelu", name, data, name, token, token);
		}
		return MessageCreator.marshall(context, response);

	}

}
