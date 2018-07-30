package com.jt.sys.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageRecords;
import com.jt.sys.controller.SysLoginController;
import com.jt.sys.dao.SysLinuxServerManageDao;
import com.jt.sys.entity.LinuxUser;
import com.jt.sys.service.SysLinuxServerManageService;
@Service
public class SysLinuxServerMagageImpl implements SysLinuxServerManageService{
	@Autowired
	private SysLinuxServerManageDao sysLinuxServerManageDao;
	@Override
	public List<LinuxUser> findLinuxs() {
		if(StringUtils.isEmpty(SysLoginController.currentUsername)){
			throw new ServiceException("当前无用户登录，不允许新建linux服务器");
		}
		List<LinuxUser> list = sysLinuxServerManageDao.findLinuxs(SysLoginController.currentUsername);
		return list;
	}
	@Override
	public PageRecords<LinuxUser> findPageLinuxs(Integer pageCurrent) {
		// 获取总页数
		int rowCount = sysLinuxServerManageDao.getLinuxCounts(SysLoginController.currentUsername);
		if(rowCount==0)throw new ServiceException("没有记录");
		// 定义当前页大小
		int pageSize = 5;
		// 计算当前位置
		if(pageCurrent==null||pageCurrent<0)
			throw new ServiceException("页面不合法");
		int startIndex = (pageCurrent-1)*pageSize;
		// 查询当前页数据
		List<LinuxUser> list = sysLinuxServerManageDao.findPageLinuxs(startIndex, pageSize,SysLoginController.currentUsername);
		//3.封装数据
		PageRecords<LinuxUser> pageObject
		=new PageRecords<>();
		pageObject.setRecords(list);
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setPageCurrent(pageCurrent);
		return pageObject;
	}

	@Override
	public LinuxUser findLinuxByAnothername(String anotherName) {
			// 判断username是否为空
				if(StringUtils.isEmpty(anotherName)){
					throw new ServiceException("请选择一个账号");
				}else if("请选择服务器帐号".equals(anotherName)){
					LinuxUser linuxUser = new LinuxUser();
					linuxUser.setUsername("提示语");
					return linuxUser;
				}else{
					LinuxUser linuxUser= sysLinuxServerManageDao.findLinuxByAnothername(anotherName,SysLoginController.currentUsername);
					return linuxUser;
				}
	}

	@Override
	public LinuxUser findLinuxUserById(Integer id) {
		if(id==null){
			throw new ServiceException("请选择一个用户");
		}else {
			LinuxUser linuxUser = sysLinuxServerManageDao.findLinuxUserById(id);
			return linuxUser;
		}
	}

	@Override
	public int doAddLinuxUser(LinuxUser linuxUser) {
		int i=0;
		if(StringUtils.isEmpty(linuxUser.getUsername())){
			throw new ServiceException("用户名不能为空");
		}else if(StringUtils.isEmpty(linuxUser.getPassword())){
			throw new ServiceException("密码不能为空");
		}else if(StringUtils.isEmpty(linuxUser.getIp())){
			throw new ServiceException("ip不能为空");
		}else if(StringUtils.isEmpty(linuxUser.getPort())){
			throw new ServiceException("端口号不能为空");
		}else if(StringUtils.isEmpty(linuxUser.getProtocol())){
			throw new ServiceException("协议不能为空");
		}else{
			i = sysLinuxServerManageDao.doAddLinuxUser(linuxUser);
		}
		return i;

	}

	@Override
	public int doUpdateLinuxUser(LinuxUser linuxUser) {
		int i=0;
		if(linuxUser==null){
			throw new ServiceException("用户信息不能为空");
		}else{
			i = sysLinuxServerManageDao.doUpdateLinuxUser(linuxUser);
			return i;
		}
	}

	@Override
	public LinuxUser identifyPassword(String username, String password) {
		// 判断密码是否为空
		if(StringUtils.isEmpty(password)){
			throw new ServiceException("请输入密码");
		}else{
			LinuxUser linuxUser = sysLinuxServerManageDao.identifyPassword(username, password);
			if(password.equals(linuxUser.getPassword())){
				return linuxUser;
			}else{
				throw new ServiceException("密码不正确");
			}
		}
	}

	@Override
	public void doDelLinuxUser(Integer id) {
		// 判断id是否为空
		if(StringUtils.isEmpty(id)){
			throw new ServiceException("请选择要删除id");
		}else{
			sysLinuxServerManageDao.doDelLinuxUser(id);
		}
	}

}
