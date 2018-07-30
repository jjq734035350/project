package com.jt.sys.service;
import java.util.List;
import com.jt.common.vo.PageRecords;
import com.jt.sys.entity.TarRecord;

public interface SysTarRecordService {
	// 查询每一页显示的打包记录内容
	PageRecords<TarRecord> findRecords(Integer pageCurrent);
	// 插入打包记录
	int insertRecord(TarRecord record);
	// 根据id从记录表中查找一条记录
	TarRecord findRecordById(Integer id);
	// 根据日期查找打包记录
	List<TarRecord> findRecordByDate(String startTime,String endTime);
}
