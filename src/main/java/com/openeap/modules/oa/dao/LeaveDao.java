package com.openeap.modules.oa.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.openeap.common.persistence.BaseDao;
import com.openeap.common.persistence.BaseDaoImpl;
import com.openeap.modules.oa.entity.Leave;

/**
 * 请假DAO接口
 * @author liuj
 * @version 2013-04-05
 */
public interface LeaveDao extends LeaveDaoCustom, CrudRepository<Leave, Long> {

	@Modifying
	@Query("update Leave set delFlag='" + Leave.DEL_FLAG_DELETE + "' where id = ?1")
	public int deleteById(Long id);
	
	@Modifying
	@Query("update Leave set processInstanceId=?2 where id = ?1")
	public int updateProcessInstanceId(Long id,String processInstanceId);
}

/**
 * DAO自定义接口
 * @author liuj
 */
interface LeaveDaoCustom extends BaseDao<Leave> {

}

/**
 * DAO自定义接口实现
 * @author liuj
 */
@Component
class LeaveDaoImpl extends BaseDaoImpl<Leave> implements LeaveDaoCustom {

}
