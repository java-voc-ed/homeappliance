package com.erp.demo.model.nonphysical;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

/**
 * 參考對象：org.springframework.data.jpa.domain.AbstractAuditable
 */

@Data
@MappedSuperclass
public abstract class CustomAbstractAuditable {
	
	/**
	 * Creation Metadata
	 */
	@CreatedDate
	@Column(name = "CreateTime")
	private LocalDateTime createdDate;
//	@CreatedBy
//	@Column(name = "CreateName" length=45)
//	private String createdBy;
	
	/**
	 * Modification Metadata
	 */	
	@LastModifiedDate
	@Column(name = "UpdateTime")
	private LocalDateTime lastModifiedDate;
	@LastModifiedBy
	@Column(name = "UpdateName", length = 45)
	private String lastModifiedBy;

}
