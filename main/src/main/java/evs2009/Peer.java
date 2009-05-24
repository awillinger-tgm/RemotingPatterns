package evs2009;

/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public interface Peer {

	public void login(String username, String pw) throws EppErrorException;
	public void logout() throws EppErrorException;

	public void   create(String name, byte[] data) throws EppErrorException;
	public byte[] read(String name) throws EppErrorException;
	public void   update(String name, byte[] data) throws EppErrorException;
	public void   delete(String name) throws EppErrorException;

	public void transferRequest(String name, String token) throws EppErrorException;
	public void transferExecute(String token, MetaData info, byte[] data) throws EppErrorException;
	public void transferCancel(String token) throws EppErrorException;

	public MetaData check(String name) throws EppErrorException;

}