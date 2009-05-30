package evs2009.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class ResData {
	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private CheckData chkData;

	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private ObjectData creData;

	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private ObjectData infData;

	public ResData(CheckData chkData) {
		super();
		this.chkData = chkData;
	}

	public ResData(ObjectData creData) {
		super();
		this.creData = creData;
	}
	
	public ResData(ObjectData infData, String roid){
		this.infData = infData;
		infData.setRoid(roid);
	}

	public ResData() {
		// TODO Auto-generated constructor stub
	}

}
