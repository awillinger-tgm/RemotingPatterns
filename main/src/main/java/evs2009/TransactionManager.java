package evs2009;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.sun.swing.internal.plaf.synth.resources.synth;

public class TransactionManager {

	private final PeerImpl localPeer;
	public TransactionManager(PeerImpl localPeer) {
		this.localPeer = localPeer;
	}
	private final Map<String, SessionPeer> sessions = new HashMap<String, SessionPeer>();

	private final Map<String, List<Action>> transactions = new HashMap<String, List<Action>>();

	private final Set<String> locks = new HashSet<String>();

	public synchronized SessionPeer getSession(String token) {
		SessionPeer result = sessions.get(token);
		if (result == null) {
			result = new SessionPeer(this,  token, localPeer);
			sessions.put(token, result);
		}
		return result;
	}

	public synchronized void addAction(String token, Action a) {
		List<Action> actions = transactions.get(token);
		if (actions == null) {
			actions = new ArrayList<Action>();
		}
		actions.add(a);
	}

	public void rollback(String token) {
		List<Action> actions = transactions.get(token);
		for (Action action : actions) {
			action.rollback();
		}
	}

	public void lock(String resource) {
		if (locks.contains(resource)) {
			throw new EppErrorException(EppErrorCode.RESOURCE_LOCKED);
		}
		locks.add(resource);
	}
	public void unlock(String resource) {
		locks.remove(resource);
	}

	public PeerImpl getPeer() {
		return localPeer;
	}

	public String createToken() {
		return UUID.randomUUID().toString();
	}
}
