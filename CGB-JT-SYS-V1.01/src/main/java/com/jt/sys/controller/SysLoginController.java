package com.jt.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.sys.service.SysUserService;
//访问http://localhost:8080/CGB-JT-SYS-V1.02/loginUI.do
@Controller
@RequestMapping("/")
public class SysLoginController {
	 public static String currentUsername = "";
	  @Autowired
	  private SysUserService sysUserService;
	  /**
	   * 获取用户名
	   * @return
	   */
	  @RequestMapping("getUsername")
	  @ResponseBody
	  public JsonResult getUsername(){
		  JsonResult jsonResult = new JsonResult();
		  jsonResult.setData(currentUsername);
		  return jsonResult;
	  }
	  /**
	   * 用户登录
	   * @param username
	   * @param password
	   * @return
	   */
	  @RequestMapping("doLogin")
	  @ResponseBody
	  public JsonResult doLogin(String username,String password){
		  System.out.println(username+" "+password);
		  currentUsername = username;
		  sysUserService.login(username, password);
		  JsonResult jsonResult = new JsonResult(1);
		  jsonResult.setMessage("ok");
		  return jsonResult;
	  }
}






