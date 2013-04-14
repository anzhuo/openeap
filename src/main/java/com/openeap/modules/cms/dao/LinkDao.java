package com.openeap.modules.cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openeap.common.persistence.BaseDao;
import com.openeap.common.persistence.BaseDaoImpl;
import com.openeap.modules.cms.entity.Link;

/**
 * 链接DAO接口
 * @author lcw
 * @version 2013-01-15
 */
public interface LinkDao extends LinkDaoCustom, CrudRepository<Link, Long> {

	@Modifying
	@Query("update Link set status=?2 where id = ?1")
	public int updateStatus(Long id, String status);
	
	public List<Link> findByIdIn(Long[] ids);
	
}

/**
 * DAO自定义接口
 * @author lcw
 */
interface LinkDaoCustom extends BaseDao<Link> {

}

/**
 * DAO自定义接口实现
 * @author lcw
 */
@Repository
class LinkDaoImpl extends BaseDaoImpl<Link> implements LinkDaoCustom {

}
