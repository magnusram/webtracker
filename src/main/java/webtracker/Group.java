package webtracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "GROUPS" )
public class Group implements Serializable {
	
	@Id @GeneratedValue	(strategy=GenerationType.AUTO)	
	private long id;
	
	@ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Member> members = new ArrayList<Member>();
	
	private String groupName;
	private String createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date();
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	

	public String toString(){
		StringBuffer toReturn = new StringBuffer(this.groupName + " ");
		for(Member mem: this.members){
			toReturn.append(mem);
		}		
		return toReturn.toString();
	}
	
	
	
	
}
