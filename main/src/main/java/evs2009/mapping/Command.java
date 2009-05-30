package evs2009.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Command {
	private Login login;

	private String logout;

	private Create create;

	private Update update;

	private Delete delete;

	private Info info;

	private Check check;

	public String getLogout() {
		return logout;
	}

	public Create getCreate() {
		return create;
	}

	public Update getUpdate() {
		return update;
	}

	public Delete getDelete() {
		return delete;
	}

	public Info getInfo() {
		return info;
	}

	public Check getCheck() {
		return check;
	}

	public String getClTRID() {
		return clTRID;
	}

	private String clTRID;

	public Command() {
		// TODO Auto-generated constructor stub
	}

	public Command(Login login) {
		this.login = login;
	}

	public Command(Check check, String clTRID) {
		super();
		this.check = check;
		this.clTRID = clTRID;
	}

	public Command(Create create, String clTRID) {
		super();
		this.create = create;
		this.clTRID = clTRID;
	}

	public Command(Update update, String clTRID) {
		super();
		this.update = update;
		this.clTRID = clTRID;
	}

	public Command(Delete delete, String clTRID) {
		super();
		this.delete = delete;
		this.clTRID = clTRID;
	}

	public Command(Info info2, String clTRID) {
		this.info = info2;
		this.clTRID = clTRID;
	}

	public Login getLogin() {
		return login;
	}

	public boolean isLogout() {
		return logout != null;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Command setLogout() {
		this.logout = "";
		return this;
	}

	public Command setClTRID(String clTRID) {
		this.clTRID = clTRID;
		return this;
	}

	public CommandType getCommandType() {
		if(this.login != null){
			return CommandType.Login;
		}else if (this.logout != null) {
			return CommandType.Logout;
		}else if (this.create != null) {
			return CommandType.Create;
		}else if (this.info != null) {
			return CommandType.Info;
		}else if (this.update != null) {
			return CommandType.Update;
		}else if (this.delete != null) {
			return CommandType.Delete;
		}
		else if (this.check != null) {
			return CommandType.Check;
		}
		return CommandType.Unknown;
	}

	public static enum CommandType{
		Login, Logout, Create, Info, Update ,Delete, Transfer, Check, Unknown
	}
}
