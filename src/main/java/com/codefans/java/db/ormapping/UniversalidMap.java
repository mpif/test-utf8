package com.codefans.java.db.ormapping;

public class UniversalidMap {
	public int id;
	public String uidkey;
	public String value;
	public String catalog;
	public String submittime;
	public char appended;
	public String appendtime;
	public String companyId;
	public String repositoryId;
	public String folderId;
	public String nodeId;
	public String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUidkey() {
		return uidkey;
	}

	public void setUidkey(String uidkey) {
		this.uidkey = uidkey;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getSubmittime() {
		return submittime;
	}

	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}

	// public char getAppended() {
	// return appended;
	// }
	// public void setAppended(char appended) {
	// this.appended = appended;
	// }
	public String getAppendtime() {
		return appendtime;
	}

	public void setAppendtime(String appendtime) {
		this.appendtime = appendtime;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getRepositoryId() {
		return repositoryId;
	}

	public void setRepositoryId(String repositoryId) {
		this.repositoryId = repositoryId;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
