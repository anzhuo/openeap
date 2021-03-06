package com.openeap.modules.cms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openeap.common.persistence.BaseDao;
import com.openeap.common.persistence.BaseDaoImpl;
import com.openeap.modules.cms.entity.Category;

/**
 * 栏目DAO接口
 * @author lcw
 * @version 2013-05-15
 */
public interface CategoryDao extends CategoryDaoCustom, CrudRepository<Category, Long> {

	@Modifying
	@Query("update Category set delFlag='" + Category.DEL_FLAG_DELETE + "' where id = ?1 or parentIds like ?2")
	public int deleteById(Long id, String likeParentIds);
	
	public List<Category> findByParentIdsLike(String parentIds);

//	@Query("from Category where delFlag='" + Category.DEL_FLAG_NORMAL + "' order by site.id, sort")
//	public List<Category> findAllList();

	@Query("from Category where delFlag='" + Category.DEL_FLAG_NORMAL + "' and (module='' or module=?1) order by site.id, sort")
	public List<Category> findByModule(String module);

	@Query("from Category where delFlag='" + Category.DEL_FLAG_NORMAL + "' and parent.id=?1 and site.id=?2 order by site.id, sort")
	public List<Category> findByParentId(Long parentId, Long siteId);
	
	@Query("from Category where delFlag='" + Category.DEL_FLAG_NORMAL + "' and parent.id=:parentId order by site.id, sort")
	public Page<Category> findByParentId(@Param("parentId") Long parentId, Pageable pageable);
	
	@Query("from Category where delFlag='" + Category.DEL_FLAG_NORMAL + "' and parent.id=?1 and inMenu=?2 order by site.id, sort")
	public List<Category> findByParentId(Long parentId, String isMenu);
	
//	@Query("select distinct c from Category c, Role r, User u where c in elements (r.categoryList) and r in elements (u.roleList)" +
//			" and c.delFlag='" + Category.DEL_FLAG_NORMAL + "' and r.delFlag='" + Role.DEL_FLAG_NORMAL + 
//			"' and u.delFlag='" + User.DEL_FLAG_NORMAL + "' and u.id=?1 or (c.user.id=?1 and c.delFlag='" + Category.DEL_FLAG_NORMAL +
//			"') order by c.site.id, c.sort")
//	public List<Category> findByUserId(Long userId);
	
	public List<Category> findByIdIn(Long[] ids);
	
}

/**
 * DAO自定义接口
 * @author lcw
 */
interface CategoryDaoCustom extends BaseDao<Category> {

}

/**
 * DAO自定义接口实现
 * @author lcw
 */
@Repository
class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDaoCustom {

}
