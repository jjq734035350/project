package com.jt.sys.service;

public interface SysUserService {
	  /**根据用户名和密码进行登录认证*/
			void login(String username,String password);
	   /**
	    * 注册
	    */
	   int doRegister(String username,String password);
	   	
	   /**
	    * 找回密码
	    */
	   String doSearchPassword(String username);
	   int updatePassword(String username,String password);
}
