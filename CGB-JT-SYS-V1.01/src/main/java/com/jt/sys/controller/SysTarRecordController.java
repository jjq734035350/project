package com.jt.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageRecords;
import com.jt.sys.entity.TarRecord;
import com.jt.sys.service.SysTarRecordService;

@Controller
@RequestMapping("/tarRecord/")
public class SysTarRecordController {
	@Autowired
	private SysTarRecordService sysTarRecordService;
	 /**
	  * 找出所有打包记录
	  */
	 @RequestMapping("findPageRecords")
	 @ResponseBody
	 public JsonResult findPageRecords(Integer pageCurrent){
		 JsonResult jsonResult = new JsonResult();
		 PageRecords<TarRecord> pageRecords = sysTarRecordService.findRecords(pageCurrent);
		 jsonResult.setData(pageRecords);
		 return jsonResult;
	 }
		/**
		 * 根据日期范围查找记录
		 */
		@RequestMapping("doSearchPageRecords")
		@ResponseBody
		public JsonResult doSearch(String startTime,String endTime){
			List<TarRecord> list = null;
			list = sysTarRecordService.findRecordByDate(startTime, endTime);
			JsonResult jsonResult = new JsonResult();
			jsonResult.setData(list);
			return jsonResult;
		}
}
