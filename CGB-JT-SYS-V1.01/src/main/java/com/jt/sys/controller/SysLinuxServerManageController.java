package com.jt.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageRecords;
import com.jt.sys.entity.LinuxUser;
import com.jt.sys.service.SysLinuxServerManageService;

@Controller
@RequestMapping("/linuxServerManage/")
public class SysLinuxServerManageController {
	@Autowired
	private SysLinuxServerManageService sysLinuxServerManageService;
	// 添加Linux服务器
	@RequestMapping("addLinux")
	public String editUI(){
		return "sys/linuxServers/linux-add";
	}
	// 修改服务器信息
	@RequestMapping("updateLinux")
	public String updateLinux(){
		return "sys/linuxServers/linux-update";
	}
	// 查找服务器信息
	@RequestMapping("doFindLinuxs")
	@ResponseBody
	public JsonResult doFindLinuxs(){
		List<LinuxUser> list=sysLinuxServerManageService.findLinuxs();
		JsonResult jsonResult = new JsonResult(list);
		return jsonResult;
	}
	// 获取每一页显示的linux服务器信息
	@RequestMapping("doFindPageLinuxs")
	@ResponseBody
	public JsonResult doFindPageLinuxs(Integer pageCurrent){
		PageRecords<LinuxUser> list=sysLinuxServerManageService.findPageLinuxs(pageCurrent);
		JsonResult jsonResult = new JsonResult(list);
		return jsonResult;
	}
	// 根据别名查找服务器信息
	@RequestMapping("doFindLinuxByAnotherUsername")
	@ResponseBody
	public JsonResult doFindLinuxByUsername(String anothername){
		LinuxUser linuxUser = sysLinuxServerManageService.findLinuxByAnothername(anothername);
		JsonResult jsonResult = new JsonResult(linuxUser);
		return jsonResult;
	}
	// 根据id查找服务器信息
	@RequestMapping("doFindLinuxUserById")
	@ResponseBody
	public JsonResult findLinuxUserById(Integer id){
		System.out.println(id);
		LinuxUser linuxUser = sysLinuxServerManageService.findLinuxUserById(id);
		JsonResult jsonResult = new JsonResult(linuxUser);
		return jsonResult;
	}
	// 添加服务器信息
	@RequestMapping("doAddLinuxUser")
	@ResponseBody
	public JsonResult doAddlinuxUser(LinuxUser linuxUser){
		linuxUser.setCreatedUser(SysLoginController.currentUsername);
		int i = sysLinuxServerManageService.doAddLinuxUser(linuxUser);
		JsonResult jsonResult = new JsonResult();
		if(i>0){
			jsonResult.setMessage("添加成功");
		}else{
			jsonResult.setMessage("添加失败");
		}
		return jsonResult;
	}
	// 更改服务器信息
	@RequestMapping("doUpdateLinuxUser")
	@ResponseBody
	public JsonResult doUpdateLinuxUser(LinuxUser linuxUser){
		System.out.println(linuxUser);
		int i = sysLinuxServerManageService.doUpdateLinuxUser(linuxUser);
		return new JsonResult(i) ;
	}
	// 删除服务器信息
	@RequestMapping("doDelLinuxUser")
	@ResponseBody
	public JsonResult doDelLinuxUser(Integer id){
		sysLinuxServerManageService.doDelLinuxUser(id);
		return new JsonResult() ;
	}
	// 验证密码是否正确
	@RequestMapping("identifyPassword")
	@ResponseBody
	public JsonResult identifyPassword(String username,String password){
		LinuxUser linuxUser = sysLinuxServerManageService.identifyPassword(username, password);
		return new JsonResult(linuxUser) ;
	}
	// 根据ip或者别名查询linux服务器信息
	@RequestMapping("findByAnothername")
	@ResponseBody
	public JsonResult findLinuxByAnothername(String anothername){
		LinuxUser linuxUser = sysLinuxServerManageService.findLinuxByAnothername(anothername);
		System.out.println(linuxUser);
		return new JsonResult(linuxUser);
	}
}
