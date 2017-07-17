package com.fuwa.lhh.model;

public class DepartACLModel {
	String siteId;
	String departId;
	String siteName;
	String departName;
	boolean printWord;
	boolean downloadWord;
	boolean printExcel;
	boolean downloadExcel;
	boolean printPDF;
	boolean downloadPDF;
	
	boolean bomSearch;
	boolean materialSearch;
	boolean parentSearch;
	
	public DepartACLModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DepartACLModel(String siteId, String departId, String siteName,
			String departName, boolean printWord, boolean downloadWord,
			boolean printExcel, boolean downloadExcel, boolean printPDF,
			boolean downloadPDF) {
		super();
		this.siteId = siteId;
		this.departId = departId;
		this.siteName = siteName;
		this.departName = departName;
		this.printWord = printWord;
		this.downloadWord = downloadWord;
		this.printExcel = printExcel;
		this.downloadExcel = downloadExcel;
		this.printPDF = printPDF;
		this.downloadPDF = downloadPDF;
	}
	
	public DepartACLModel(String siteId, String departId, String siteName,
			String departName, boolean printWord, boolean downloadWord,
			boolean printExcel, boolean downloadExcel, boolean printPDF,
			boolean downloadPDF, boolean bomSearch, boolean materialSearch,
			boolean parentSearch) {
		super();
		this.siteId = siteId;
		this.departId = departId;
		this.siteName = siteName;
		this.departName = departName;
		this.printWord = printWord;
		this.downloadWord = downloadWord;
		this.printExcel = printExcel;
		this.downloadExcel = downloadExcel;
		this.printPDF = printPDF;
		this.downloadPDF = downloadPDF;
		this.bomSearch = bomSearch;
		this.materialSearch = materialSearch;
		this.parentSearch = parentSearch;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public boolean isPrintWord() {
		return printWord;
	}
	public void setPrintWord(boolean printWord) {
		this.printWord = printWord;
	}
	public boolean isDownloadWord() {
		return downloadWord;
	}
	public void setDownloadWord(boolean downloadWord) {
		this.downloadWord = downloadWord;
	}
	public boolean isPrintExcel() {
		return printExcel;
	}
	public void setPrintExcel(boolean printExcel) {
		this.printExcel = printExcel;
	}
	public boolean isDownloadExcel() {
		return downloadExcel;
	}
	public void setDownloadExcel(boolean downloadExcel) {
		this.downloadExcel = downloadExcel;
	}
	public boolean isPrintPDF() {
		return printPDF;
	}
	public void setPrintPDF(boolean printPDF) {
		this.printPDF = printPDF;
	}
	public boolean isDownloadPDF() {
		return downloadPDF;
	}
	public void setDownloadPDF(boolean downloadPDF) {
		this.downloadPDF = downloadPDF;
	}
	@Override
	public String toString() {
		return "DepartACLModel [siteId=" + siteId + ", departId=" + departId
				+ ", siteName=" + siteName + ", departName=" + departName
				+ ", printWord=" + printWord + ", downloadWord=" + downloadWord
				+ ", printExcel=" + printExcel + ", downloadExcel="
				+ downloadExcel + ", printPDF=" + printPDF + ", downloadPDF="
				+ downloadPDF + ", bomSearch=" + bomSearch
				+ ", materialSearch=" + materialSearch + ", parentSearch="
				+ parentSearch + "]";
	}
	public boolean isBomSearch() {
		return bomSearch;
	}
	public void setBomSearch(boolean bomSearch) {
		this.bomSearch = bomSearch;
	}
	public boolean isMaterialSearch() {
		return materialSearch;
	}
	public void setMaterialSearch(boolean materialSearch) {
		this.materialSearch = materialSearch;
	}
	public boolean isParentSearch() {
		return parentSearch;
	}
	public void setParentSearch(boolean parentSearch) {
		this.parentSearch = parentSearch;
	}
	
	
	
}
