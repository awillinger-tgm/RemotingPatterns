package evs2009;

import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

/**
 *
 * @author  Michael Borko <michael@borko.at>
 *          Florian Motlik <flomotlik@gmail.com>
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public class Lookup {

	public AbsolutObjectReference getReference(String name) {
		return new AbsolutObjectReference(name);
	}

}
