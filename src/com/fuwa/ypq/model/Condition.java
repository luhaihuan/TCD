package com.fuwa.ypq.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "HFPLM_FUWA_CONDITION")
public class Condition {
	
	String uuid;
	
	
	Set<ConditionFormula> condition_formulas;
	
	QueryType query_type;
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
	
	@OneToMany(mappedBy="condition")
	@LazyCollection(LazyCollectionOption.EXTRA)
	public Set<ConditionFormula> getCondition_formulas() {
		return condition_formulas;
	}

	public void setCondition_formulas(Set<ConditionFormula> condition_formulas) {
		this.condition_formulas = condition_formulas;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="qid")
	public QueryType getQuery_type() {
		return query_type;
	}

	public void setQuery_type(QueryType query_type) {
		this.query_type = query_type;
	}
}
