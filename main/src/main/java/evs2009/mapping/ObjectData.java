package evs2009.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class ObjectData {
	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private String name;
	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private byte[] data;

	public ObjectData() {
	}

	public ObjectData(String name, byte[] data) {
		super();
		this.name = name;
		this.data = data;
	}
}
