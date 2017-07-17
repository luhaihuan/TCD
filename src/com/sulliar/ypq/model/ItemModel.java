package com.sulliar.ypq.model;

public class ItemModel {
	String puid;
	String searchType;
	String item_id;
	String item_id_new;
	String item_id_old;
	String item_name;
	String object_code;
	String item_revision;
	String dataset_name;
	String dataset_type;
	String child_id;
	String referenceId;
	String remark;
	
	
	
	
	
	
	String filePath;
	String nodePath;
	boolean canRead;
	boolean canPrint;
	boolean canDownload;
	
	
	
	public ItemModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	@Override
	public String toString() {
		return "ItemModel [puid=" + puid + ", searchType=" + searchType
				+ ", item_id=" + item_id + ", item_id_new=" + item_id_new
				+ ", item_id_old=" + item_id_old + ", item_name=" + item_name
				+ ", object_code=" + object_code + ", item_revision="
				+ item_revision + ", dataset_name=" + dataset_name
				+ ", dataset_type=" + dataset_type + ", child_id=" + child_id
				+ ", referenceId=" + referenceId + ", remark=" + remark
				+ ", filePath=" + filePath + ", nodePath=" + nodePath
				+ ", canRead=" + canRead + ", canPrint=" + canPrint
				+ ", canDownload=" + canDownload + ", child_rev_id="
				+ child_rev_id + "]";
	}



	public String getPuid() {
		return puid;
	}

	public void setPuid(String puid) {
		this.puid = puid;
	}

	public String getItem_id_new() {
		return item_id_new;
	}
	public void setItem_id_new(String item_id_new) {
		this.item_id_new = item_id_new;
	}
	public String getItem_id_old() {
		return item_id_old;
	}
	public void setItem_id_old(String item_id_old) {
		this.item_id_old = item_id_old;
	}
	public String getObject_code() {
		return object_code;
	}
	public void setObject_code(String object_code) {
		this.object_code = object_code;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public String getChild_id() {
		return child_id;
	}
	public void setChild_id(String child_id) {
		this.child_id = child_id;
	}
	String child_rev_id;
	public String getChild_rev_id() {
		return child_rev_id;
	}
	public void setChild_rev_id(String child_rev_id) {
		this.child_rev_id = child_rev_id;
	}
	
	public String getNodePath() {
		return nodePath;
	}
	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_revision() {
		return item_revision;
	}
	public void setItem_revision(String item_revision) {
		this.item_revision = item_revision;
	}
	public String getDataset_name() {
		return dataset_name;
	}
	public void setDataset_name(String dataset_name) {
		this.dataset_name = dataset_name;
	}
	public String getDataset_type() {
		return dataset_type;
	}
	public void setDataset_type(String dataset_type) {
		this.dataset_type = dataset_type;
	}
	public boolean getCanRead() {
		return canRead;
	}
	public void setCanRead(boolean canRead) {
		this.canRead = canRead;
	}
	public boolean getCanPrint() {
		return canPrint;
	}
	public void setCanPrint(boolean canPrint) {
		this.canPrint = canPrint;
	}
	public boolean getCanDownload() {
		return canDownload;
	}
	public void setCanDownload(boolean canDownload) {
		this.canDownload = canDownload;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	

	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
}
