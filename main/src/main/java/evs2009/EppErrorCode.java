package evs2009;

/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public enum EppErrorCode {
	LOGIN_FAILED(100),
	PERMISSION_DENIED(200);

	private int code;
	private EppErrorCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
