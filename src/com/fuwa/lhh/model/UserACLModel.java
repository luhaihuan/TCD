package com.fuwa.lhh.model;

public class UserACLModel {

	String userId;
	String userName;
	boolean printWord;
	boolean downloadWord;
	boolean printExcel;
	boolean downloadExcel;
	boolean printPDF;
	boolean downloadPDF;
	
	boolean bomSearch;
	boolean materialSearch;
	boolean parentSearch;
	
	public UserACLModel() {
		// TODO Auto-generated constructor stub
	}
	public UserACLModel(String userId, String userName, boolean printWord,
			boolean downloadWord, boolean printExcel, boolean downloadExcel,
			boolean printPDF, boolean downloadPDF) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.printWord = printWord;
		this.downloadWord = downloadWord;
		this.printExcel = printExcel;
		this.downloadExcel = downloadExcel;
		this.printPDF = printPDF;
		this.downloadPDF = downloadPDF;
	}
	
	public UserACLModel(String userId, String userName, boolean printWord,
			boolean downloadWord, boolean printExcel, boolean downloadExcel,
			boolean printPDF, boolean downloadPDF, boolean bomSearch,
			boolean materialSearch, boolean parentSearch) {
		super();
		this.userId = userId;
		this.userName = userName;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
		return "UserACLModel [userId=" + userId + ", userName=" + userName
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
