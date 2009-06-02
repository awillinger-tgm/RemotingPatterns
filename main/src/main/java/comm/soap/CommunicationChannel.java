package comm.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import comm.Invoker;

@WebService
public class CommunicationChannel {

	private Invoker invoker;
	
	public CommunicationChannel() {
		// TODO Auto-generated constructor stub
	}
	
	public CommunicationChannel(Invoker invoker) {
		this.invoker = invoker;
	}

	@WebMethod
	public String get(@WebParam(name="in") String in) {
		return in;
	}
	/*
	public byte[] invoke(byte[] request) {
		return invoker.handleRequest(request);
	}
	*/
}
