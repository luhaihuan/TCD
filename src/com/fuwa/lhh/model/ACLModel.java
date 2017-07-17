package com.fuwa.lhh.model;

public class ACLModel {
	String siteStr;
	String groupStr;
	String username;
	String typeStr;
	String printWord;
	String downloadWord;
	String printExcel;
	String downloadExcel;
	String printPDF;
	String downloadPDF;
	
	String bomSearch;
	String materialSearch;
	String parentSearch;
	public ACLModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSiteStr() {
		return siteStr;
	}
	
	public ACLModel(String siteStr, String groupStr, String username,
			String typeStr, String printWord, String downloadWord,
			String printExcel, String downloadExcel, String printPDF,
			String downloadPDF) {
		super();
		this.siteStr = siteStr;
		this.groupStr = groupStr;
		this.username = username;
		this.typeStr = typeStr;
		this.printWord = printWord;
		this.downloadWord = downloadWord;
		this.printExcel = printExcel;
		this.downloadExcel = downloadExcel;
		this.printPDF = printPDF;
		this.downloadPDF = downloadPDF;
	}
	
	
	public void setSiteStr(String siteStr) {
		this.siteStr = siteStr;
	}
	public String getGroupStr() {
		return groupStr;
	}
	public void setGroupStr(String groupStr) {
		this.groupStr = groupStr;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	public String getPrintWord() {
		return printWord;
	}
	public void setPrintWord(String printWord) {
		this.printWord = printWord;
	}
	public String getDownloadWord() {
		return downloadWord;
	}
	public void setDownloadWord(String downloadWord) {
		this.downloadWord = downloadWord;
	}
	public String getPrintExcel() {
		return printExcel;
	}
	public void setPrintExcel(String printExcel) {
		this.printExcel = printExcel;
	}
	public String getDownloadExcel() {
		return downloadExcel;
	}
	public void setDownloadExcel(String downloadExcel) {
		this.downloadExcel = downloadExcel;
	}
	public String getPrintPDF() {
		return printPDF;
	}
	public void setPrintPDF(String printPDF) {
		this.printPDF = printPDF;
	}
	public String getDownloadPDF() {
		return downloadPDF;
	}
	public void setDownloadPDF(String downloadPDF) {
		this.downloadPDF = downloadPDF;
	}
	@Override
	public String toString() {
		return "ACLModel [siteStr=" + siteStr + ", groupStr=" + groupStr
				+ ", username=" + username + ", typeStr=" + typeStr
				+ ", printWord=" + printWord + ", downloadWord=" + downloadWord
				+ ", printExcel=" + printExcel + ", downloadExcel="
				+ downloadExcel + ", printPDF=" + printPDF + ", downloadPDF="
				+ downloadPDF + ", bomSearch=" + bomSearch
				+ ", materialSearch=" + materialSearch + ", parentSearch="
				+ parentSearch + "]";
	}
	public String getBomSearch() {
		return bomSearch;
	}
	public void setBomSearch(String bomSearch) {
		this.bomSearch = bomSearch;
	}
	public String getMaterialSearch() {
		return materialSearch;
	}
	public void setMaterialSearch(String materialSearch) {
		this.materialSearch = materialSearch;
	}
	public String getParentSearch() {
		return parentSearch;
	}
	public void setParentSearch(String parentSearch) {
		this.parentSearch = parentSearch;
	}
	
     
	
	
}
