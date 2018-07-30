package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entity.SysUser;

public interface SysUserDao {
	/**
	 * 注册
	 */
	int doRegister(@Param("username")String username,@Param("password")String password);
	
	/**根据用户名查找用户信息*/
	SysUser findObjectByUserName(String username);
	
	/**
	 * 找回密码
	 * @param username
	 * @return
	 */
	String doSearchPassword(String username);
	/**
	 * 修改密码
	 */
	int updatePassword(@Param("username")String username,@Param("password")String password);
}
