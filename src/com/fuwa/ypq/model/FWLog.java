package com.fuwa.ypq.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "HFPLM_FUWA_LOG")
public class FWLog {
	String uuid;
	FWUser user;
	
	String item_id;
	String item_name;
	String item_rev;
	
	String action_date;
	String action_type;
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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userid")
	public FWUser getUser() {
		return user;
	}
	public void setUser(FWUser user) {
		this.user = user;
	}
	@Column(name = "item_id", length = 255)
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	@Column(name = "item_name", length = 255)
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	@Column(name = "item_rev", length = 255)
	public String getItem_rev() {
		return item_rev;
	}
	public void setItem_rev(String item_rev) {
		this.item_rev = item_rev;
	}
	@Column(name = "action_date", length = 255)
	public String getAction_date() {
		return action_date;
	}
	public void setAction_date(String action_date) {
		this.action_date = action_date;
	}
	@Column(name = "action_type", length = 255)
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
}
