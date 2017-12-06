package com.sym.cms.domain;

import java.util.Date;

/**
 * Base entity class representing all the base fields of entities
 * @author ranveer
 *
 */
public abstract class BaseEntity {
	
	private Long id;
	
	private Boolean isDeleted = Boolean.FALSE;
	
	private Date createdDate;
	
	private Date lastUpdatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

}
