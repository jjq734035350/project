package com.jt.sys.entity;

import java.io.Serializable;

import com.jt.common.entity.BaseEntity;

public class LinuxUser extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1718755520532096777L;
	private Integer id;
	private String username;
	private String password;
	private String ip;
	private Integer port;
	private String protocol;
	private Integer flag;
	private String anothername;
	public LinuxUser(){}
	public LinuxUser(Integer id, String username, String password, String ip,
			Integer port, String protocol, Integer flag, String anothername) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.ip = ip;
		this.port = port;
		this.protocol = protocol;
		this.flag = flag;
		this.anothername = anothername;
	}
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
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getAnothername() {
		return anothername;
	}
	public void setAnothername(String anothername) {
		this.anothername = anothername;
	}
	@Override
	public String toString() {
		return "LinuxUser [id=" + id + ", username=" + username + ", password="
				+ password + ", ip=" + ip + ", port=" + port + ", protocol="
				+ protocol + ", flag=" + flag + ", anothername=" + anothername
				+ "]";
	}
	

}
