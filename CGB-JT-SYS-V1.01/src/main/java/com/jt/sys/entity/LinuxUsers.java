package com.jt.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jt.common.util.DateFormatConverter;

public class LinuxUsers implements Serializable{
	private static final long serialVersionUID = -9157142097960737596L;
	private Integer id;
	private String username;
	private String password;
	private String ip;
	private String status;
	private Date createdTime;
	private Date modifiedTime;
	private String operation;
	public LinuxUsers(){}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@JsonSerialize(using=DateFormatConverter.class)
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	@JsonSerialize(using=DateFormatConverter.class)
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	@Override
	public String toString() {
		return "LinuxUsers [id=" + id + ", username=" + username
				+ ", password=" + password + ", ip=" + ip + ", status="
				+ status + ", createdTime=" + createdTime + ", modifiedTime="
				+ modifiedTime + ", operation=" + operation + "]";
	}
	
}
