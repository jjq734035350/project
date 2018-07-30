package com.jt.sys.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.jt.sys.entity.TarRecord;
public interface SysTarRecordDao {
	// 查询记录表中所有记录
	List<TarRecord> findRecords();
	// 查询总记录数
	int getAllRecords(String username);
	// 查询当前页数据
	List<TarRecord> findPageRecords(@Param("startIndex")int startIndex,@Param("pageSize")int pageSize,@Param("operator")String username);
	// 向记录表中增加一条记录
	int insertRecord(TarRecord record);
	// 根据id在记录表中查找记录
	TarRecord findRecordById(Integer id);
	// 根据日期查找打包记录
	List<TarRecord> findRecordByDate(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("username")String username);
}
