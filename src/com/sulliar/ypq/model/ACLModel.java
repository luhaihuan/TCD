package com.sulliar.ypq.model;

public class ACLModel {
	int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	String typeStr;
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	String username ;
	boolean readWord;
	boolean printWord;
	boolean downloadWord;
	boolean readExcel;
	boolean printExcel;
	boolean downloadExcel;
	boolean readPDF;
	boolean printPDF;
	boolean downloadPDF;
	boolean readForm;
	boolean printForm;
	boolean downloadForm;
	
	boolean bomsearch;
	boolean materialSearch;
	boolean parentSearch;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isReadWord() {
		return readWord;
	}
	public void setReadWord(boolean readWord) {
		this.readWord = readWord;
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
	public boolean isReadExcel() {
		return readExcel;
	}
	public void setReadExcel(boolean readExcel) {
		this.readExcel = readExcel;
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
	public boolean isReadPDF() {
		return readPDF;
	}
	public void setReadPDF(boolean readPDF) {
		this.readPDF = readPDF;
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
	public boolean isReadForm() {
		return readForm;
	}
	public void setReadForm(boolean readForm) {
		this.readForm = readForm;
	}
	public boolean isPrintForm() {
		return printForm;
	}
	public void setPrintForm(boolean printForm) {
		this.printForm = printForm;
	}
	public boolean isDownloadForm() {
		return downloadForm;
	}
	public void setDownloadForm(boolean downloadForm) {
		this.downloadForm = downloadForm;
	}
	public boolean isBomsearch() {
		return bomsearch;
	}
	public void setBomsearch(boolean bomsearch) {
		this.bomsearch = bomsearch;
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
