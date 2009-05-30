package evs2009.mapping;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import evs2009.EppErrorCode;
import evs2009.EppErrorException;
import evs2009.mapping.CheckData.CheckDataInternal;

public class MessageCreator {
	public static Epp login(String user, String password) {
		return new Epp(new Command(new Login(user, password)));
	}

	public static Epp loginResponse(String code, String message, String clTRID,
			String svTRID) {
		return new Epp(new Response(new Result(code, message), new TrId(clTRID,
				svTRID)));
	}

	public static Epp logout(String cltrId) {
		return new Epp(new Command().setLogout().setClTRID(cltrId));
	}

	public static Epp logoutResponse(String code, String message,
			String clTRID, String svTRID) {
		return MessageCreator.loginResponse(code, message, clTRID, svTRID);
	}
/*
	public static Epp check(String clTRID, String... names) {
		return new Epp(new Command(new Check(names), clTRID));
	}

	public static Epp checkResponse(String code, String message, String clTRID,
			String svTRID, CheckDataInternal... internals) {
		return new Epp(new Response(new Result(code, message), new ResData(
				new ObjectData(name, data), roid), new TrId(clTRID, svTRID)));
	}
*/
	public static Epp create(String name, byte[] data, String clTRID) {
		return new Epp(new Command(new Create(new ObjectData(name, data)),
				clTRID));
	}

	public static Epp createResponse(String code, String message, String name,
			String clTRID, String svTRID) {
		return new Epp(new Response(new Result(code, message), new ResData(
				new ObjectData(name)), new TrId(clTRID, svTRID)));
	}

	public static Epp info(String name, String clTRID, boolean onlyMetadata) {
		return new Epp(new Command(new Info(new ObjectData(name, onlyMetadata)), clTRID));
	}

	public static Epp infoResponse(String code, String message, String name,
			byte[] data, String roid, String clTRID, String svTRID) {
		return new Epp(new Response(new Result(code, message), new ResData(
				new ObjectData(name, data), roid), new TrId(clTRID, svTRID)));
	}

	public static Epp update(String name, byte[] data, String clTRID) {
		return new Epp(new Command(new Update(new ObjectData(name, data)),
				clTRID));
	}

	public static Epp updateResponse(String code, String message,
			String clTRID, String svTRID) {
		return new Epp(new Response(new Result(code, message), new TrId(clTRID,
				svTRID)));
	}

	public static Epp delete(String name, String clTRID) {
		return new Epp(new Command(new Delete(new ObjectData(name)), clTRID));
	}

	public static Epp deleteResponse(String code, String message,
			String clTRID, String svTRID) {
		return new Epp(new Response(new Result(code, message), new TrId(clTRID,
				svTRID)));
	}

	public static byte[] marshall(JAXBContext context,  Epp epp) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(epp, out);
			return out.toByteArray();
		} catch (JAXBException e) {
			throw new EppErrorException(EppErrorCode.XML_ERROR, e
					.getMessage(), e);
		}
	}


	public static Epp unmarshall(JAXBContext context, byte[] reponse) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(reponse);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (Epp) unmarshaller.unmarshal(bis);
		} catch (JAXBException e) {
			throw new EppErrorException(EppErrorCode.XML_ERROR, e
					.getMessage(), e);
		}
	}
}
