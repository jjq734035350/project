package com.jt.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jt.common.vo.JsonResult;
import com.jt.sys.service.SysUserService;

@RequestMapping("/user/")
@Controller
public class SysUserController {
	  @Autowired
	  private SysUserService sysUserService;
	  /**
	   * 用户注册
	   * @param username
	   * @param password
	   * @return
	   */
	  @RequestMapping("doRegister")
	  @ResponseBody
	  public JsonResult doRegister(String username,String password){
		  JsonResult jsonResult = new JsonResult();
		  int value = sysUserService.doRegister(username, password);
		  System.out.println(value);
		  if(value!=0){
			  jsonResult.setMessage("注册成功");
		  }else{
			  jsonResult.setMessage("注册失败");
		  }
		  return jsonResult;
	  }
	  /**
	   * 更改密码
	   */
	  @RequestMapping("doUpdatePassword")
	  @ResponseBody
	  public JsonResult doUpdatePassword(String username,String password){
		  int i = sysUserService.updatePassword(username, password);
		  return new JsonResult(i);
	  }
	  
	  /**
	   * 找回密码
	   */
	  @RequestMapping("doSearchPassword")
	  @ResponseBody
	  public JsonResult doSearchPassword(String username){
		  String password = sysUserService.doSearchPassword(username);
		  return new JsonResult(password);
	  }
}
