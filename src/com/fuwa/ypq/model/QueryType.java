package com.fuwa.ypq.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "HFPLM_FUWA_CONDITION")
public class QueryType {

	String uuid;
	String query_type_name;
	
	Set<Claue> claues;
	Set<Condition> conditions;
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
	@Column(name = "query_type_name", length = 255)
	public String getQuery_type_name() {
		return query_type_name;
	}
	public void setQuery_type_name(String query_type_name) {
		this.query_type_name = query_type_name;
	}
	@OneToMany(mappedBy="query_type")
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<Claue> getClaues() {
		return claues;
	}
	public void setClaues(Set<Claue> claues) {
		this.claues = claues;
	}
	@OneToMany(mappedBy="query_type")
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<Condition> getConditions() {
		return conditions;
	}
	public void setConditions(Set<Condition> conditions) {
		this.conditions = conditions;
	}
	
}
