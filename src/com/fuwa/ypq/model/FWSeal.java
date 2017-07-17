package com.fuwa.ypq.model;

import java.util.Date;
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
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.GenericGenerator;

import com.fuwa.ypq.model.Department;

import net.sf.json.JSONObject;
@Entity
@Table(name = "HFPLM_FUWA_FWSEAL")
public class FWSeal {
	
	String uuid;
	String seal_show_name;
	String seal_true_name;
	boolean have_date;
	Set<Department>  departs;
	public FWSeal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

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
	
	@Column(name = "seal_show_name", length = 255)	
	public String getSeal_show_name() {
		return seal_show_name;
	}



	public void setSeal_show_name(String seal_show_name) {
		this.seal_show_name = seal_show_name;
	}


	@Column(name = "seal_true_name", length = 255)	
	public String getSeal_true_name() {
		return seal_true_name;
	}



	public void setSeal_true_name(String seal_true_name) {
		this.seal_true_name = seal_true_name;
	}



	@Column(name = "have_date")
	public boolean isHave_date() {
		return have_date;
	}
	public void setHave_date(boolean have_date) {
		this.have_date = have_date;
	}
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "FUWA_Department_Seal",
	joinColumns = {@JoinColumn(name = "seal_id", referencedColumnName = "uuid")},
	inverseJoinColumns = {@JoinColumn(name = "department_id", referencedColumnName ="uuid")})
	@JSON(serialize=false)
	public Set<Department> getDeparts() {
		return departs;
	}
	public void setDeparts(Set<Department> departs) {
		this.departs = departs;
	}



	@Override
	public String toString() {
		return "FWSeal [uuid=" + uuid + ", seal_show_name=" + seal_show_name
				+ ", seal_true_name=" + seal_true_name + ", have_date="
				+ have_date + ", departs=" + departs + "]";
	}
	
	
	
	
	
	
}
