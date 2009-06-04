package evs2009;

/**
 * This class is an interceptor for Peer, providing session and transaction
 * management.
 * 
 * @author Michael Borko<michael@borko.at>, Florian Motlik<flomotlik@gmail.com>,
 *         Michael Greifeneder <mikegr@gmx.net>
 * 
 */
public class SessionPeer implements Peer {

	private ServerPeer localPeer;
	private TransactionManager transactionManager;
	private String token;
	private boolean loggedIn = false;

	public SessionPeer(TransactionManager tm, String token, ServerPeer localPeer) {
		this.localPeer = localPeer;
		this.transactionManager = tm;
		this.token = token;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#check(java.lang.String)
	 */
	@Override
	public MetaData check(String name) {
		if (!loggedIn)
			throw new EppErrorException(EppErrorCode.NOT_LOGGED_IN);
		return localPeer.check(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#create(java.lang.String, byte[])
	 */
	@Override
	public void create(final String name, final byte[] data) {
		if (!loggedIn)
			throw new EppErrorException(EppErrorCode.NOT_LOGGED_IN);
		CreateAction createAction = new CreateAction(transactionManager, name,
				data);
		createAction.action();
		transactionManager.addAction(token, createAction);
	}

	private static class CreateAction implements Action {

		private String name;
		private byte[] data;
		private Peer peer;
		private TransactionManager tm;
		private MetaData updatedMetaData;

		public CreateAction(TransactionManager tm, String name, byte[] data) {
			this.peer = tm.getPeer();
			this.name = name;
			this.data = data;
			this.tm = tm;
		}

		@Override
		public void action() {
			tm.lock(name);
			try {
				peer.create(name, data);
				updatedMetaData = peer.check(name);

			} finally {
				tm.unlock(name);
			}
		}

		@Override
		public void rollback() {
			if (updatedMetaData == null) {
				return;
			}
			if (!updatedMetaData.equals(peer.check(name))) {
				throw new EppErrorException(EppErrorCode.ROLLBACK_FAILED,
						"Resource modified meanwhile");
			}
			peer.delete(name);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#delete(java.lang.String)
	 */
	@Override
	public void delete(String name) {
		if (!loggedIn)
			throw new EppErrorException(EppErrorCode.NOT_LOGGED_IN);
		DeleteAction action = new DeleteAction(transactionManager, name);
		transactionManager.addAction(token, action);
		action.action();
	}

	private static class DeleteAction implements Action {

		private String name;
		private ServerPeer peer;
		private TransactionManager tm;
		private Resource resource;

		public DeleteAction(TransactionManager tm, String name) {
			super();
			this.name = name;
			this.peer = tm.getPeer();
			this.tm = tm;
		}

		@Override
		public void action() {
			try {
				tm.lock(name);
				resource = peer.getResource(name);
				peer.delete(name);
			} finally {
				tm.unlock(name);
			}
		}

		@Override
		public void rollback() {
			try {
				tm.lock(name);
				if (peer.getResource(name) != null) {
					throw new EppErrorException(EppErrorCode.ROLLBACK_FAILED,
							"Resource meanwhile modified");
				}
				peer.updateResource(name, resource);
			} finally {
				tm.unlock(name);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#login(java.lang.String, java.lang.String)
	 */
	@Override
	public void login(String username, String pw) {
		localPeer.login(username, pw);
		loggedIn = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#logout()
	 */
	@Override
	public void logout() {
		this.localPeer.logout();
		loggedIn = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#read(java.lang.String)
	 */
	@Override
	public byte[] read(String name) {
		if (!loggedIn)
			throw new EppErrorException(EppErrorCode.NOT_LOGGED_IN);
		// TODO: Do we need lock? transactionManager.lock(name);
		return localPeer.read(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#transferCancel(java.lang.String)
	 */
	@Override
	public void transferCancel(String token) {
		if (!loggedIn)
			throw new EppErrorException(EppErrorCode.NOT_LOGGED_IN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#transferExecute(java.lang.String, evs2009.MetaData,
	 * byte[])
	 */
	@Override
	public void transferExecute(String token, MetaData info, byte[] data) {
		if (!loggedIn)
			throw new EppErrorException(EppErrorCode.NOT_LOGGED_IN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#transferRequest(java.lang.String, java.lang.String)
	 */
	@Override
	public void transferRequest(String name, String token) {
		if (!loggedIn)
			throw new EppErrorException(EppErrorCode.NOT_LOGGED_IN);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see evs2009.Peer#update(java.lang.String, byte[])
	 */
	@Override
	public void update(String name, byte[] data) {
		if (!loggedIn)
			throw new EppErrorException(EppErrorCode.NOT_LOGGED_IN);
		UpdateAction action = new UpdateAction(transactionManager, name, data);
		transactionManager.addAction(token, action);
		action.action();
	}

	private static class UpdateAction implements Action {
		private TransactionManager tm;
		private String name;

		private Resource resource;
		private byte[] newData;
		private ServerPeer peer;

		public UpdateAction(TransactionManager tm, String name, byte[] newData) {
			super();
			this.tm = tm;
			this.peer = tm.getPeer();
			this.name = name;
			this.newData = newData;
		}

		@Override
		public void action() {
			tm.lock(name);
			try {
				resource = peer.getResource(name);
				peer.update(name, newData);
			} finally {
				tm.unlock(name);
			}

		}

		@Override
		public void rollback() {
			tm.lock(name);
			try {
				if (!resource.getMetaData().equals(peer.check(name))) {
					throw new EppErrorException(EppErrorCode.ROLLBACK_FAILED,
							"Resource modified meanwhile");
				}
				peer.updateResource(name, resource);
			} finally {
				tm.unlock(name);
			}
		}
	}

}
