package com.jt.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jt.common.util.DateFormatConverter;

public class TarRecord implements Serializable{
	private static final long serialVersionUID = 2882543465168871631L;
	private Integer id;
	private Date tarTime;
	private String addFilesName;
	private String addContent;
	private String updateFilesName;
	private String updateContent;
	private String deleteFile;
	private String filePath;
	private String operator;
	public TarRecord(){
	}
	public TarRecord(Integer id, Date tarTime, String addFilesName,
			String addContent, String updateFilesName, String updateContent,
			String deleteFile, String filePath, String operator) {
		super();
		this.id = id;
		this.tarTime = tarTime;
		this.addFilesName = addFilesName;
		this.addContent = addContent;
		this.updateFilesName = updateFilesName;
		this.updateContent = updateContent;
		this.deleteFile = deleteFile;
		this.filePath = filePath;
		this.operator = operator;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@JsonSerialize(using=DateFormatConverter.class)
	public Date getTarTime() {
		return tarTime;
	}
	public void setTarTime(Date tarTime) {
		this.tarTime = tarTime;
	}
	public String getAddFilesName() {
		return addFilesName;
	}
	public void setAddFilesName(String addFilesName) {
		this.addFilesName = addFilesName;
	}
	public String getAddContent() {
		return addContent;
	}
	public void setAddContent(String addContent) {
		this.addContent = addContent;
	}
	public String getUpdateFilesName() {
		return updateFilesName;
	}
	public void setUpdateFilesName(String updateFilesName) {
		this.updateFilesName = updateFilesName;
	}
	public String getUpdateContent() {
		return updateContent;
	}
	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}
	public String getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(String deleteFile) {
		this.deleteFile = deleteFile;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Override
	public String toString() {
		return "TarRecord [id=" + id + ", tarTime=" + tarTime
				+ ", addFilesName=" + addFilesName + ", addContent="
				+ addContent + ", updateFilesName=" + updateFilesName
				+ ", updateContent=" + updateContent + ", deleteFile="
				+ deleteFile + ", filePath=" + filePath + ", operator="
				+ operator + "]";
	}
}
