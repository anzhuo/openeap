package com.openeap.modules.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openeap.common.persistence.BaseDao;
import com.openeap.common.persistence.BaseDaoImpl;
import com.openeap.modules.sys.entity.Menu;
import com.openeap.modules.sys.entity.Role;
import com.openeap.modules.sys.entity.User;

/**
 * 菜单DAO接口
 * @author lcw
 * @version 2013-01-15
 */
public interface MenuDao extends MenuDaoCustom, CrudRepository<Menu, Long> {

	@Modifying
	@Query("update Menu set delFlag='" + Menu.DEL_FLAG_DELETE + "' where id = ?1 or parentIds like ?2")
	public int deleteById(Long id, String likeParentIds);
	
	public List<Menu> findByParentIdsLike(String parentIds);

	@Query("from Menu where delFlag='" + Menu.DEL_FLAG_NORMAL + "' order by sort")
	public List<Menu> findAllList();
	
	@Query("select distinct m from Menu m, Role r, User u where m in elements (r.menuList) and r in elements (u.roleList)" +
			" and m.delFlag='" + Menu.DEL_FLAG_NORMAL + "' and r.delFlag='" + Role.DEL_FLAG_NORMAL + 
			"' and u.delFlag='" + User.DEL_FLAG_NORMAL + "' and u.id=?1 or (m.user.id=?1  and m.delFlag='" + Menu.DEL_FLAG_NORMAL + 
			"') order by m.sort")
	public List<Menu> findByUserId(Long userId);
}

/**
 * DAO自定义接口
 * @author lcw
 */
interface MenuDaoCustom extends BaseDao<Menu> {

}

/**
 * DAO自定义接口实现
 * @author lcw
 */
@Repository
class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDaoCustom {

}
