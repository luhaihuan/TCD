package com.fuwa.ypq.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "HFPLM_FUWA_USER")
public class FWUser {
	String uuid;
	String user_no;
	String user_name;
	String user_pwd;
	String email;
	Set<Department> groups;
	Set<FWLog> logs;
	
	boolean actived;
	boolean OA;
	boolean dba;
/*	Integer version;
	@Version
	@Column(name="OPTLOCK")
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	*/
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
	@Column(name = "user_name", length = 255)
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	@Column(name = "user_pwd", length = 255)
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	@Column(name = "email", length = 255)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "user_no", length = 255)
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "FUWA_Department_User",
	joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "uuid")},
	inverseJoinColumns = {@JoinColumn(name = "department_id", referencedColumnName ="uuid")})
	@JSON(serialize=false)
	public Set<Department> getGroups() {
		return groups;
	}
	public void setGroups(Set<Department> groups) {
		this.groups = groups;
	}
	@OneToMany(mappedBy="user")
	@LazyCollection(LazyCollectionOption.EXTRA)
	@JSON(serialize=false)
	public Set<FWLog> getLogs() {
		return logs;
	}
	public void setLogs(Set<FWLog> logs) {
		this.logs = logs;
	}
	@Column(name = "actived")
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}
	@Column(name = "OA")
	public boolean getOA() {
		return OA;
	}
	public void setOA(boolean OA) {
		this.OA = OA;
	}
	@Column(name = "dba")
	public boolean getDba() {
		return dba;
	}
	public void setDba(boolean dba) {
		this.dba = dba;
	}
	public FWUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FWUser(String user_no, String user_name, String user_pwd,
			boolean actived, boolean oA, boolean dba) {
		super();
		this.user_no = user_no;
		this.user_name = user_name;
		this.user_pwd = user_pwd;
		this.actived = actived;
		OA = oA;
		this.dba = dba;
	}
	@Override
	public String toString() {
		return "FWUser [uuid=" + uuid + ", user_no=" + user_no + ", user_name="
				+ user_name + ", user_pwd=" + user_pwd + ", email=" + email
				+ ", groups=" + groups + ", logs=" + logs + ", actived="
				+ actived + ", OA=" + OA + ", dba=" + dba + "]";
	}

    

	
	
}
