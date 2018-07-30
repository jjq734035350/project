package com.jt.common.vo;

import java.io.Serializable;

public class FileNode implements Serializable{
	private static final long serialVersionUID = 3029563357378810120L;
	private String parentPath; // 父文件绝对路径
	private String filePath; // 文件绝对路径
	private String name;
	private String isParent; // 是否为父节点
	public FileNode(){
	}
	public FileNode(String parentPath, String filePath, String name,
			String isParent) {
		this.parentPath = parentPath;
		this.filePath = filePath;
		this.name = name;
		this.isParent = isParent;
	}
	public String getParentPath() {
		return parentPath;
	}
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	
}
