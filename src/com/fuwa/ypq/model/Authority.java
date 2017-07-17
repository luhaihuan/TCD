package com.fuwa.ypq.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "HFPLM_FUWA_AUTHORITY")
public class Authority {
	String uuid;
	String site_id;
	String group_id;
	String user_id;
	
	String query_type_id;
	
	boolean can_print_excel;
	boolean can_download_excel;
	
	boolean can_print_word;
	boolean can_download_word;
	
	boolean can_print_pdf;
	boolean can_download_pdf;
	
	
	
	boolean can_bom_search;
	boolean can_material_search;
	boolean can_parent_search;
	
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
	@Column(name = "site_id", length = 255)
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	@Column(name = "group_id", length = 255)
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	@Column(name = "user_id", length = 255)
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Column(name = "query_type_id", length = 255)
	public String getQuery_type_id() {
		return query_type_id;
	}
	public void setQuery_type_id(String query_type_id) {
		this.query_type_id = query_type_id;
	}
	@Column(name = "can_print_excel", length = 255)
	public boolean isCan_print_excel() {
		return can_print_excel;
	}
	public void setCan_print_excel(boolean can_print_excel) {
		this.can_print_excel = can_print_excel;
	}
	@Column(name = "can_download_excel", length = 255)
	public boolean isCan_download_excel() {
		return can_download_excel;
	}
	public void setCan_download_excel(boolean can_download_excel) {
		this.can_download_excel = can_download_excel;
	}
	@Column(name = "can_print_word", length = 255)
	public boolean isCan_print_word() {
		return can_print_word;
	}
	public void setCan_print_word(boolean can_print_word) {
		this.can_print_word = can_print_word;
	}
	@Column(name = "can_download_word", length = 255)
	public boolean isCan_download_word() {
		return can_download_word;
	}
	public void setCan_download_word(boolean can_download_word) {
		this.can_download_word = can_download_word;
	}
	@Column(name = "can_print_pdf", length = 255)
	public boolean isCan_print_pdf() {
		return can_print_pdf;
	}
	public void setCan_print_pdf(boolean can_print_pdf) {
		this.can_print_pdf = can_print_pdf;
	}
	@Column(name = "can_download_pdf", length = 255)
	public boolean isCan_download_pdf() {
		return can_download_pdf;
	}
	public void setCan_download_pdf(boolean can_download_pdf) {
		this.can_download_pdf = can_download_pdf;
	}
	
	@Override
	public String toString() {
		return "Authority [uuid=" + uuid + ", site_id=" + site_id
				+ ", group_id=" + group_id + ", user_id=" + user_id
				+ ", query_type_id=" + query_type_id + ", can_print_excel="
				+ can_print_excel + ", can_download_excel="
				+ can_download_excel + ", can_print_word=" + can_print_word
				+ ", can_download_word=" + can_download_word
				+ ", can_print_pdf=" + can_print_pdf + ", can_download_pdf="
				+ can_download_pdf + ", can_bom_search=" + can_bom_search
				+ ", can_material_search=" + can_material_search
				+ ", can_parent_search=" + can_parent_search + "]";
	}
	public Authority() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Authority(boolean can_print_excel, boolean can_download_excel,
			boolean can_print_word, boolean can_download_word,
			boolean can_print_pdf, boolean can_download_pdf) {
		super();
		this.can_print_excel = can_print_excel;
		this.can_download_excel = can_download_excel;
		this.can_print_word = can_print_word;
		this.can_download_word = can_download_word;
		this.can_print_pdf = can_print_pdf;
		this.can_download_pdf = can_download_pdf;
	}
	
	
	public Authority(boolean can_print_excel, boolean can_download_excel,
			boolean can_print_word, boolean can_download_word,
			boolean can_print_pdf, boolean can_download_pdf,
			boolean can_bom_search, boolean can_material_search,
			boolean can_parent_search) {
		super();
		this.can_print_excel = can_print_excel;
		this.can_download_excel = can_download_excel;
		this.can_print_word = can_print_word;
		this.can_download_word = can_download_word;
		this.can_print_pdf = can_print_pdf;
		this.can_download_pdf = can_download_pdf;
		this.can_bom_search = can_bom_search;
		this.can_material_search = can_material_search;
		this.can_parent_search = can_parent_search;
	}
	@Column(name = "can_bom_search", length = 255)
	public boolean isCan_bom_search() {
		return can_bom_search;
	}
	public void setCan_bom_search(boolean can_bom_search) {
		this.can_bom_search = can_bom_search;
	}
	
	@Column(name = "can_material_search", length = 255)
	public boolean isCan_material_search() {
		return can_material_search;
	}
	public void setCan_material_search(boolean can_material_search) {
		this.can_material_search = can_material_search;
	}
	
	@Column(name = "can_parent_search", length = 255)
	public boolean isCan_parent_search() {
		return can_parent_search;
	}
	public void setCan_parent_search(boolean can_parent_search) {
		this.can_parent_search = can_parent_search;
	}
	
	
	
}
