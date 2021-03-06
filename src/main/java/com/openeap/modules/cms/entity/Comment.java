package com.openeap.modules.cms.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.openeap.common.persistence.BaseEntity;
import com.openeap.modules.sys.entity.User;

/**
 * 评论Entity
 * @author lcw
 * @version 2013-05-15
 */
@Entity
@Table(name = "cms_comment")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Comment extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Long id;		// 编号
	private Category category;// 分类编号
	private Long contentId;	// 归属分类内容的编号（Article.id、Photo.id、Download.id）
	private String title;	// 归属分类内容的标题（Article.title、Photo.title、Download.title）
	private String content; // 评论内容
	private String name; 	// 评论姓名
	private String ip; 		// 评论IP
	private Date createDate;// 评论时间
	private User auditUser; // 审核人
	private Date auditDate;	// 审核时间
	private String delFlag;	// 删除标记删除标记（0：正常；1：删除；2：审核）

	public Comment() {
		super();
		this.delFlag = DEL_FLAG_AUDIT;
	}
	
	public Comment(Long id){
		this();
		this.id = id;
	}
	
	public Comment(Category category){
		this();
		this.category = category;
	}
	
	@PrePersist
	public void prePersist(){
		this.createDate = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cms_comment")
//	@SequenceGenerator(name = "seq_cms_comment", sequenceName = "seq_cms_comment")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="category_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@NotNull
	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	@Length(min=1, max=255)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=1, max=255)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=1, max=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name="audit_user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(User auditUser) {
		this.auditUser = auditUser;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@NotNull
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Length(min=1, max=1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}