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
@Table(name = "HFPLM_FUWA_CONDITIONFORMULA")
public class ConditionFormula {
	String uuid;
	String targetType;
	String destType;
	String relationType;
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
	@Column(name = "targetType", length = 255)
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	
	@Column(name = "destType", length = 255)
	public String getDestType() {
		return destType;
	}
	
	public void setDestType(String destType) {
		this.destType = destType;
	}
	@Column(name = "relationType", length = 255)
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
		
	Condition condition;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cid")
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
}
