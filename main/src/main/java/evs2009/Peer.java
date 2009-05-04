package evs2009;

/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public interface Peer {

	public void login() throws EppErrorException;
	public void logout() throws EppErrorException;

	public void   create(String name, byte[] data) throws EppErrorException;
	public byte[] read(String name) throws EppErrorException;
	public void   update(String name, byte[] data) throws EppErrorException;
	public void   delete(String name) throws EppErrorException;

	public void transferRequest() throws EppErrorException;
	public void transferExecute() throws EppErrorException;
	public void transferCancel() throws EppErrorException;

	public MetaData check(String name) throws EppErrorException;

}
