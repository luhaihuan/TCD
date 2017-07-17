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
@Table(name = "HFPLM_FUWA_CLAUE")
public class Claue {
	String uuid;
	String claue_type;
	String claue_name;
/*	Integer version;
	@Version
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
	@Column(name = "claue_type", length = 255)
	public String getClaue_type() {
		return claue_type;
	}
	public void setClaue_type(String claue_type) {
		this.claue_type = claue_type;
	}
	@Column(name = "claue_name", length = 255)
	public String getClaue_name() {
		return claue_name;
	}
	public void setClaue_name(String claue_name) {
		this.claue_name = claue_name;
	}
	
	QueryType query_type;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="qid")
	public QueryType getQuery_type() {
		return query_type;
	}
	public void setQuery_type(QueryType query_type) {
		this.query_type = query_type;
	}
}
