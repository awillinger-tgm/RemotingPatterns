package evs2009;

/**
 *
 * @author Michael Borko <michael@borko.at> Florian Motlik <flomotlik@gmail.com>
 *         Michael Greifeneder <mikegr@gmx.net>
 *
 */
public class Lookup {

	private comm.Lookup commLookup;
	private comm.RequestHandler rh;
	
	public Lookup(comm.Lookup clookup, comm.RequestHandler rh) {
		this.commLookup = clookup;
		this.rh = rh;
	}
	
	public Peer lookup( String name ) {
		comm.AbsoluteObjectReference aor = commLookup.lookup(name);
		comm.Communication remote = null;
		try {
			remote = (comm.Communication) rh.getObject(aor);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ClientPeer(remote);
	}

}