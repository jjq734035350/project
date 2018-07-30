package com.jt.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.common.exception.ServiceException;
import com.jt.sys.dao.SysUserDao;
import com.jt.sys.entity.SysUser;
import com.jt.sys.service.SysUserService;
@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Override
	public int doRegister(String username, String password) {
		int value = sysUserDao.doRegister(username, password);
		if(value==1){
			return 1;
		}else{
			return 0;
		}
	}
	
	 @Override
	 public void login(String username,String password) {
		 System.out.println("==login==");
		 SysUser sysUser = sysUserDao.findObjectByUserName(username);
		 if(sysUser==null){
			 throw new ServiceException("用户名不存在");
		 }else{
			 if(!sysUser.getPassword().equals(password)){
				 throw new ServiceException("密码错误！");
			 }
		 }
	 }
	
	@Override
	public String doSearchPassword(String username) {
		// 判断用户是否为空
		if(StringUtils.isEmpty(username)){
			throw new ServiceException("查询的用户名不能为空");
		}else{
			String password = sysUserDao.doSearchPassword(username);
			if(StringUtils.isEmpty(password)){
				return "无此用户";
			}else{
				return password;
			}
		}
	}

	@Override
	public int updatePassword(String username, String password) {
		int i = 0;
		// 判断用户名和密码是否为空
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
			throw new ServiceException("用户名和密码不能为空");
		}else{
			SysUser sysUser = sysUserDao.findObjectByUserName(username);
			if(sysUser==null){
				throw new ServiceException("无此用户");
			}else{
				i = sysUserDao.updatePassword(username, password);
			}
		}
		return i;
	}

}
