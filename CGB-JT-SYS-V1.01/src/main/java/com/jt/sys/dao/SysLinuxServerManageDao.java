package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entity.LinuxUser;

public interface SysLinuxServerManageDao {
		// 查询所有Linux服务器信息 
		List<LinuxUser> findLinuxs(String currentUsername);
		// 查询linux服务器总记录数
		int getLinuxCounts(String username);
		// 查询每一页的linux服务器的记录
		List<LinuxUser> findPageLinuxs(@Param("startIndex")int startIndex,@Param("pageSize")int pageSize,@Param("username")String username);
		// 根据别名查找linux服务器信息
		LinuxUser findLinuxByAnothername(@Param("anothername")String anothername,@Param("currentUsername")String currentUsername);
		// 添加linux服务器
		int doAddLinuxUser(LinuxUser linuxUser);
		// 更改服务器信息
		int doUpdateLinuxUser(LinuxUser linuxUser);
		// 根据id查找服务器信息
		LinuxUser findLinuxUserById(Integer id);
		// 根据id删除linux服务器信息
		void doDelLinuxUser(Integer id);
		// 验证用户密码是否正确
		LinuxUser identifyPassword(@Param("username")String username,@Param("password")String password);
}
