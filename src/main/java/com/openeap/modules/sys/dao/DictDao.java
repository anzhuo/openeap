package com.openeap.modules.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openeap.common.persistence.BaseDao;
import com.openeap.common.persistence.BaseDaoImpl;
import com.openeap.modules.sys.entity.Dict;

/**
 * 字典DAO接口
 * @author lcw
 * @version 2013-01-15
 */
public interface DictDao extends DictDaoCustom, CrudRepository<Dict, Long> {
	
	@Modifying
	@Query("update Dict set delFlag='" + Dict.DEL_FLAG_DELETE + "' where id = ?1")
	public int deleteById(Long id);

	@Query("from Dict where delFlag='" + Dict.DEL_FLAG_NORMAL + "' order by sort")
	public List<Dict> findAllList();

	@Query("select type from Dict where delFlag='" + Dict.DEL_FLAG_NORMAL + "' group by type")
	public List<String> findTypeList();
	
//	@Query("from Dict where delFlag='" + Dict.DEL_FLAG_NORMAL + "' and type=?1 order by sort")
//	public List<Dict> findByType(String type);
//	
//	@Query("select label from Dict where delFlag='" + Dict.DEL_FLAG_NORMAL + "' and value=?1 and type=?2")
//	public List<String> findValueByValueAndType(String value, String type);
	
}

/**
 * DAO自定义接口
 * @author lcw
 */
interface DictDaoCustom extends BaseDao<Dict> {

}

/**
 * DAO自定义接口实现
 * @author lcw
 */
@Repository
class DictDaoImpl extends BaseDaoImpl<Dict> implements DictDaoCustom {

}
