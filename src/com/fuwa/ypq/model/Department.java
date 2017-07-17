package com.fuwa.ypq.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "HFPLM_FUWA_DEPARTMENT")
public class Department {

	String uuid;
	String department_name;
	
	Site site;
	Set<FWUser> users;
	Set<FWUser> seals;
	Integer version;
/*	@Version
	@Column(name="OPTLOCK")
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}*/
	
	@Id
	@Column(name = "uuid", unique = true, nullable = false, length = 32)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Column(name = "department_name", length = 255)
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sid")
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "FUWA_Department_User",
	joinColumns = {@JoinColumn(name = "department_id", referencedColumnName = "uuid")},
	inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName ="uuid")})
	@OrderBy("user_name asc")
	@JSON(serialize=false)
	public Set<FWUser> getUsers() {
		return users; 
	}
	public void setUsers(Set<FWUser> users) {
		this.users = users;
	}
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "FUWA_Department_Seal",
	joinColumns = {@JoinColumn(name = "department_id", referencedColumnName = "uuid")},
	inverseJoinColumns = {@JoinColumn(name = "seal_id", referencedColumnName ="uuid")})
	@OrderBy("seal_show_name asc")
	@JSON(serialize=false)
	public Set<FWUser> getSeals() {
		return seals;
	}
	public void setSeals(Set<FWUser> seals) {
		this.seals = seals;
	}
	@Override
	public String toString() {
		return "Department [uuid=" + uuid + ", department_name="
				+ department_name + ", site=" + site + ", users=" + users
				+ ", seals=" + seals + ", version=" + version + "]";
	}
	
	
}
