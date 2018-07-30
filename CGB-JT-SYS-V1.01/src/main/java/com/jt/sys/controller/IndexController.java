package com.jt.sys.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class IndexController {
	  @RequestMapping(value={"loginUI","logoutUI"})
	  public String loginUI(){
		  return "login";
	  } 
		@RequestMapping("pageUI")
			 public String pageUI(){
			 return "common/page";
		}
	 @RequestMapping("indexUI")
	 public String indexUI(){
		 return "index";
	 }
	 @RequestMapping("searchPassword")
	 public String searchPassword(){
		 return "sys/user/searchPassword";
	 }
	 @RequestMapping("tarRecordUI")
	 public String tarRecordUI(){
		 return "sys/tar-record";
	 }
	 @RequestMapping("registerUI")
	 public String registerUI(){
		 return "sys/user/register";
	 }
	 @RequestMapping("updatePassword")
	 public String updatePassword(){
		 return "sys/user/update_password";
	 }
	 @RequestMapping("linuxList")
	 public String linuxServer(){
		 return "sys/linuxServers/linux-list";
	 }
	 @RequestMapping("fileTar")
	 public String doFielTar(){
		 return "sys/file-tar";
	 }
	 @RequestMapping("fileReplace")
	 public String doFileReplace(){
		 return "sys/file-replace";
	 }
	 @RequestMapping("fileDelete")
	 public String doFileDelete(){
		 return "sys/file-delete";
	 }
}
