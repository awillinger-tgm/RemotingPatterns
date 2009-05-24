package evs2009;

import java.util.Date;
/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public class MetaData {

	private String name;
	private String owner;
	private Date creationDate;
	private Date lastModifcation;
	private long size;

	public MetaData(String name, String owner, long size) {
		creationDate = new Date();
		lastModifcation = new Date();
		this.size = size;
		this.name = name;
		this.owner = owner;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getLastModifcation() {
		return lastModifcation;
	}
	public void setLastModifcation(Date lastModifcation) {
		this.lastModifcation = lastModifcation;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}

}
