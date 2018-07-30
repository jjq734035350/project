package com.jt.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcraft.jsch.JSchException;
import com.jt.common.util.ConToLinuxUtil;
import com.jt.common.vo.FileNode;
import com.jt.common.vo.JsonResult;

@Controller
@RequestMapping("/showZTree/")
public class SysShowZTreeController {
	/**
	 * 该方法用来返回zTree所需数据
	 * @return
	 * @throws JSchException 
	 */
	 @RequestMapping("zTreeUI")
	 @ResponseBody
	 public JsonResult zTreeUI(String parentPath) throws JSchException{
		 System.out.println("我点击的节点是"+parentPath);
		 // 新建一个连接linux工具类对象
		 ConToLinuxUtil conToLinuxUtil = new ConToLinuxUtil();
		 // 新建封装返回数据节点的集合
		 List<FileNode> list = new ArrayList<>();
		 if(!StringUtils.isEmpty(parentPath)){
			// 执行命令，fileAllList集合是为了判断目录是文件还是文件夹
			List<String> fileAllList = conToLinuxUtil.execCmd("ls -l "+parentPath, ConToLinuxController.username, ConToLinuxController.password, ConToLinuxController.ip);
			// 用户获取目录名
			List<String> fileList = conToLinuxUtil.execCmd("ls "+parentPath, ConToLinuxController.username, ConToLinuxController.password, ConToLinuxController.ip);
			// 遍历文件
			for(int i=1;i<fileAllList.size();i++){
				FileNode fileNode = new FileNode();
				String fName = "";
				// 如果是文件夹
				if(fileAllList.get(i).startsWith("d")){
					fileNode.setIsParent("true");
				}else{
					// 是文件
					fileNode.setIsParent("false");
				}
				// 给每个节点设置属性
				fName = fileList.get(i-1);
				fileNode.setName(fName);
				fileNode.setParentPath(parentPath);
				fileNode.setFilePath(parentPath+"/"+fName);
				list.add(fileNode);
			}
			JsonResult jsonResult = new JsonResult();
			jsonResult.setData(list);
			// 返回
			return jsonResult;
		 }else {
			 return new JsonResult();
		 }
	 	}
}
