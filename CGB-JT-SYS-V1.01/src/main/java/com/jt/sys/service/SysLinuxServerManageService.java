package com.jt.sys.service;

import java.util.List;
import com.jt.common.vo.PageRecords;
import com.jt.sys.entity.LinuxUser;

public interface SysLinuxServerManageService {
	// 查询所有linux服务器信息
	List<LinuxUser> findLinuxs();
	// 查询每一页显示的服务器信息
	PageRecords<LinuxUser> findPageLinuxs(Integer pageCurrent);
	// 根据别名查询服务器信息
	LinuxUser findLinuxByAnothername(String anotherName);
	// 根据id查找服务器信息
	LinuxUser findLinuxUserById(Integer id);
	// 增加服务器
	int doAddLinuxUser(LinuxUser linuxUser);
	// 更改服务器
	int doUpdateLinuxUser(LinuxUser linuxUser);
	// 验证用户密码是否正确
	LinuxUser identifyPassword(String username,String password);
	// 根据id删除linux服务器信息
	void doDelLinuxUser(Integer id);
}
