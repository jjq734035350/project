package com.jt.sys.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageRecords;
import com.jt.sys.controller.SysLoginController;
import com.jt.sys.dao.SysTarRecordDao;
import com.jt.sys.entity.TarRecord;
import com.jt.sys.service.SysTarRecordService;
/**负责菜单业务处理*/
@Service
public class SysTarRecordImpl 
      implements SysTarRecordService {
	@Autowired
	private SysTarRecordDao sysMenuDao;
	
	/**
	 * 查询每一页的打包记录
	 */
	@Override
	public PageRecords<TarRecord> findRecords(Integer pageCurrent) {
		// 查询总记录数
		int rowCount = sysMenuDao.getAllRecords(SysLoginController.currentUsername);
		if(rowCount==0){
			throw new ServiceException("没有记录");
		}
		// 查询当前页记录
		// 定义页面大小
		int pageSize = 5;
		// 计算当前位置
		if(pageCurrent==null||pageCurrent<0){
			throw new ServiceException("当前页面不合法");
		}
		int startIndex = (pageCurrent-1)*pageSize;
		// 查询当前页数据
		List<TarRecord> list = sysMenuDao.findPageRecords(startIndex, pageSize,SysLoginController.currentUsername);
		// 封装数据
		PageRecords<TarRecord> pageRecords = new PageRecords<>();
		pageRecords.setRecords(list);
		pageRecords.setRowCount(rowCount);
		pageRecords.setPageSize(pageSize);
		pageRecords.setPageCurrent(pageCurrent);
		return pageRecords;
	}
	@Override
	public int insertRecord(TarRecord record) {
		int i = sysMenuDao.insertRecord(record);
		return i;
	}
	/**
	 * 根据id查找打包记录
	 */
	@Override
	public TarRecord findRecordById(Integer id) {
		if(StringUtils.isEmpty(id)){
			throw new ServiceException("请选择要列举的添加文件的tar包");
		}else{
			TarRecord tarRecord = sysMenuDao.findRecordById(id);
			return tarRecord;
		}
	}
	/**
	 * 根据日期查找打包记录
	 */
	@Override
	public List<TarRecord> findRecordByDate(String startTime, String endTime) {
		List<TarRecord> list = null;
		if(StringUtils.isEmpty(startTime)||StringUtils.isEmpty(endTime)){
			throw new ServiceException("请填写完成日期范围");
		}else{
			list = sysMenuDao.findRecordByDate(startTime, endTime,SysLoginController.currentUsername);
			return list;
		}
	}
}






