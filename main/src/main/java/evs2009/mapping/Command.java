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

	private Check check;

	private String clTRID;

	public Command() {
		// TODO Auto-generated constructor stub
	}

	public Command(Login login) {
		this.login = login;
	}

	public Command(Check check) {
		super();
		this.check = check;
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
}
