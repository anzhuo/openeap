package com.openeap.modules.cms.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openeap.common.persistence.Page;
import com.openeap.common.service.BaseService;
import com.openeap.modules.cms.dao.CommentDao;
import com.openeap.modules.cms.entity.Comment;

/**
 * 评论Service
 * @author lcw
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class CommentService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CommentService.class);
	
	@Autowired
	private CommentDao commentDao;
	
	public Comment get(Long id) {
		return commentDao.findOne(id);
	}
	
	public Page<Comment> find(Page<Comment> page, Comment comment) {
		DetachedCriteria dc = commentDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(comment.getModule())){
			dc.add(Restrictions.eq("module", comment.getModule()));
		}
		if (comment.getContentId()!=null && comment.getContentId()>0){
			dc.add(Restrictions.eq("contentId", comment.getContentId()));
		}
		if (StringUtils.isNotEmpty(comment.getTitle())){
			dc.add(Restrictions.like("title", "%"+comment.getTitle()+"%"));
		}
		dc.add(Restrictions.eq("status", comment.getStatus()));
		dc.addOrder(Order.desc("id"));
		return commentDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save(Comment comment) {
		commentDao.save(comment);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id, Boolean isRe) {
		commentDao.updateStatus(id, isRe!=null&&isRe?Comment.STATUS_AUDIT:Comment.STATUS_DELETE);
	}
	
}
