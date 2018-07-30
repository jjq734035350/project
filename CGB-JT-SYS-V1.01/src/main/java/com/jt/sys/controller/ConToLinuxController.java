package com.jt.sys.controller;

/**
 * 该类用于执行一系列的打包操作
 */
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcraft.jsch.JSchException;
import com.jt.common.util.ConToLinuxUtil;
import com.jt.common.vo.FileNode;
import com.jt.common.vo.JsonResult;
import com.jt.sys.entity.TarRecord;
import com.jt.sys.service.SysTarRecordService;
import com.sun.tools.javah.resources.l10n;
@RequestMapping("/linux/")
@Controller
public class ConToLinuxController {
	@Autowired
	private SysTarRecordService sysTarRecordService;
	public ConToLinuxUtil conToLinuxUtil = new ConToLinuxUtil();
	private TarRecord tarRecord = new TarRecord();
	public String fileSavePath;
	public static String ip;
	public static Integer protInteger =22;
	public static String username;
	public static String password;
	public static Integer port;
	/**
	 * 设置文件存放路径
	 */
	@RequestMapping("setFileSavePath")
	@ResponseBody
	public JsonResult doSetFileSavePath(String fileSavePath){
		this.fileSavePath = fileSavePath;
		return new JsonResult();
	}
	/**
	 * 加载要替换的文件页面
	 */
	@RequestMapping("replaceFiles")
	public String replaceFiles(){
	return "sys/file_replace";
	}
	/**
	 * 加载要删除文件页面
	 * @return
	 */
	@RequestMapping("deleteFilesTar")
	public String deleteFilesTar(){
		return "sys/file_delete";
	}
	/**
	 * 连接linux服务
	 * @param ip
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("conToLinux")
	@ResponseBody
	public JsonResult conToLinux(String ip,Integer port,String username,String password){
		this.username = username;
		this.password = password;
		this.ip = ip;
		this.port = port;
		ConToLinuxUtil conToLinuxUtil = new ConToLinuxUtil();
		JsonResult jsonResult = new JsonResult();
		try {
			conToLinuxUtil.connect(username,password,ip);
			jsonResult.setMessage("连接成功");
		} catch (JSchException e) {
			System.out.println("连接失败");;
			jsonResult.setState(0);
			jsonResult.setMessage("连接失败");
		}
		return jsonResult;
	}
	/**
	 * 设置回显给确认页面的数据
	 */
	@RequestMapping("returnData")
	@ResponseBody
	public JsonResult returnData(@RequestBody FileNode[] files){
		JsonResult jsonResult = new JsonResult();
		String filesContent = "";
		for(int i=0;i<files.length;i++){
			// 获取文件的相对路径
			filesContent+=files[i].getFilePath()+";\r\n";
			}
	 jsonResult.setData(filesContent);
	 return jsonResult;
	}
	/**
	 * 给要新增的文件打包
	 * @throws 
	 */
	@RequestMapping("fileTar")
	@ResponseBody
	public JsonResult FileTar(@RequestBody FileNode[] files) throws InterruptedException{
		String filesAddContent = "";
		String command1 = "";
		String filePathString = "";// 要打包文件所在的相对位置
		String filePath="";
		String command = "cd "+fileSavePath;
		int index = 0;
		if(files.length>0){
			for(int i=0;i<files.length;i++){
				// 获取文件的相对路径
				//  获取文件绝对路径
				// 遍历字符串，与存储路径对比，得到另字符串的相同部分
				for(int j=0;j<fileSavePath.length();j++){
					if(fileSavePath.charAt(j)==files[i].getFilePath().charAt(j)){
						// 如果两个字符相同，继续下次比较
						index=j;
						continue;
					}else{
						// 跳出循环
						break;
					}
				}
				// 截取字符串
				if(files[i].getFilePath().length()>index){
					filePathString += files[i].getFilePath().substring(index+2)+" ";
				}else{
					filePathString += files[i].getFilePath()+" ";
				}
				filePath = fileSavePath;
				filesAddContent+=files[i].getFilePath()+";\r\n";
			}
		}else{
			filePath = fileSavePath;
			filePathString = Math.random()+".txt";
		}
		try {
			command = command.replace("\\", "/");
			command1 = "tar cvpf "+filePath+"/add.tar "+ filePathString;
			System.out.println(command+";"+command1);
			conToLinuxUtil.execCmd(command+";"+command1, username, password, ip);
		} catch (JSchException e) {
			e.printStackTrace();
		}
		System.out.println("执行成功");
		tarRecord.setAddFilesName("add.tar");
		tarRecord.setFilePath(fileSavePath);
		tarRecord.setAddContent(filesAddContent);
		JsonResult jsonResult = new JsonResult();
			tarRecord.setFilePath(fileSavePath);
			jsonResult.setData(filesAddContent);
			return jsonResult;
	}
	/**
	 * 给要替换的文件打包
	 */
	@RequestMapping("fileReplaceTar")
	@ResponseBody
	public JsonResult fileReplaceTar(@RequestBody FileNode[] files){
		String filesUpdateContent = "";
		String command1 = "";
		String filePathString = "";
		String filePath="";
		int index = 0;
		String command = "cd "+fileSavePath;
		if(files.length>0){
			for(int i=0;i<files.length;i++){
				// 获取文件的相对路径
				//  获取文件绝对路径
				// 遍历字符串，与存储路径对比，得到另字符串的相同部分
				for(int j=0;j<fileSavePath.length();j++){
					if(fileSavePath.charAt(j)==files[i].getFilePath().charAt(j)){
						// 如果两个字符相同，继续下次比较
						index=j;
						continue;
					}else{
						// 跳出循环
						break;
					}
				}
				// 截取字符串
				if(files[i].getFilePath().length()>index){
					filePathString += files[i].getFilePath().substring(index+2)+" ";
				}else{
					filePathString += files[i].getFilePath()+" ";
				}
				filePath = fileSavePath;
				filesUpdateContent+=files[i].getFilePath()+";\r\n";
			}
		}else{
			filePath = fileSavePath;
			filePathString = Math.random()+".txt";
		}
		try {
			command = command.replace("\\", "/");
			command1 = "tar cvpf "+filePath+"/replace.tar "+ filePathString;
			System.out.println(command+";"+command1);
			conToLinuxUtil.execCmd(command+";"+command1, username, password, ip);
		} catch (JSchException e) {
			e.printStackTrace();
		}
		System.out.println("执行成功");
		tarRecord.setUpdateFilesName("update.tar");
		System.out.println(filesUpdateContent);
		tarRecord.setUpdateContent(filesUpdateContent);
		JsonResult jsonResult = new JsonResult();
			tarRecord.setFilePath(fileSavePath);
			return jsonResult;
	}
	/**
	 * 给要删除的文件打包
	 * @throws IOException 
	 */
	@RequestMapping("fileDeleteTar")
	@ResponseBody
	public JsonResult fileDeleteTar(@RequestBody FileNode[] files) throws IOException{
		String deleteString = "";		
		String deletePath = "";
		String filePathString = fileSavePath.replace("\\", "/");
		for(int i=0; i<files.length;i++){
			deletePath += files[i].getFilePath()+";\r\n";
			deleteString+=files[i].getFilePath()+"'\n'";
			try {
				// 执行删除文件
				conToLinuxUtil.execCmd("rm -rf "+files[i].getFilePath(), username, password, ip);
			} catch (JSchException e) {
				System.out.println("执行删除文件时候出错");;
			}
		}
		try {
			// 向Linux中写
			System.out.println("向linux中应该写的内容"+deleteString);
			conToLinuxUtil.execCmd("cd "+fileSavePath+";"+"touch delete.txt"+";"+"echo -e "+deleteString+" >delete.txt", username, password, ip);
		} catch (JSchException e) {
			System.out.println("执行向delete.txt文件写入内容时出错");
		}
		System.out.println("要向linux写入的字符串是："+deleteString);
		tarRecord.setDeleteFile(deletePath);
		tarRecord.setOperator(SysLoginController.currentUsername);
		sysTarRecordService.insertRecord(tarRecord);
		JsonResult jsonResult = new JsonResult();
		return jsonResult;
	}
	/**
	 * 删除文件(输入框模式)
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteFile")
	@ResponseBody
	public JsonResult doDeleteFile(String deleteFilePath){
		JsonResult jsonResult = new JsonResult();
		try {
			conToLinuxUtil.execCmd("rm -rf "+deleteFilePath, username, password, ip);
		} catch (JSchException e) {
			e.printStackTrace();
		}
		try {
			// 向Linux中写
			conToLinuxUtil.execCmd("cd "+fileSavePath+";"+"touch delete.txt"+";"+"echo -e "+deleteFilePath+" >delete.txt", username, password, ip);
		} catch (JSchException e) {
			System.out.println("执行向delete.txt文件写入内容时出错");
		}
		tarRecord.setDeleteFile(deleteFilePath);
		tarRecord.setOperator(SysLoginController.currentUsername);
		sysTarRecordService.insertRecord(tarRecord);
		return jsonResult;
	}
	@RequestMapping("doListFiles")
	@ResponseBody
	public JsonResult doListFiles(Integer id){
		TarRecord tarRecord = sysTarRecordService.findRecordById(id);
		JsonResult jsonResult = new JsonResult();
		jsonResult.setData(tarRecord);
		return jsonResult;
	}
}
