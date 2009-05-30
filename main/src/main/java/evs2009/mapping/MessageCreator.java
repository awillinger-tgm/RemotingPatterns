package evs2009.mapping;

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

	public static Epp check(String... names) {
		return new Epp(new Command(new Check(names)));
	}

	public static Epp checkResponse(String code, String message, String clTRID,
			String svTRID, CheckDataInternal... internals) {
		return new Epp(new Response(new Result(code, message), new ResData(
				new CheckData(internals)), new TrId(clTRID, svTRID)));
	}

	public static Epp create(String name, byte[] data, String clTRID) {
		return new Epp(new Command(new Create(new ObjectData(name, data)),
				clTRID));
	}

	public static Epp createResponse(String code, String message, String name,
			String clTRID, String svTRID) {
		return new Epp(new Response(new Result(code, message), new ResData(
				new ObjectData(name)), new TrId(clTRID, svTRID)));
	}

	public static Epp info(String name, String clTRID) {
		return new Epp(new Command(new Info(new ObjectData(name)), clTRID));
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
}
