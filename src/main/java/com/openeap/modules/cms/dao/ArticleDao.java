package com.openeap.modules.cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openeap.common.persistence.BaseDao;
import com.openeap.common.persistence.BaseDaoImpl;
import com.openeap.modules.cms.entity.Article;

/**
 * 文章DAO接口
 * @author lcw
 * @version 2013-01-15
 */
public interface ArticleDao extends ArticleDaoCustom, CrudRepository<Article, Long> {

	@Modifying
	@Query("update Article set status=?2 where id = ?1")
	public int updateStatus(Long id, String status);
	
	public List<Article> findByIdIn(Long[] ids);
	
	@Modifying
	@Query("update Article set hits=hits+1 where id = ?1")
	public int updateHitsAddOne(Long id);
}

/**
 * DAO自定义接口
 * @author lcw
 */
interface ArticleDaoCustom extends BaseDao<Article> {

}

/**
 * DAO自定义接口实现
 * @author lcw
 */
@Repository
class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDaoCustom {

}
