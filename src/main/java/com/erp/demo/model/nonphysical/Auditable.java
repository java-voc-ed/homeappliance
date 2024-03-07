package com.erp.demo.model.nonphysical;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * TODO:
 * 
 * IMPORTANT!
 * 
 * This class should only be inherited by other abstract classes of the same package for consistency and ease of management.
 * 
 * Access control of this class is thus set to default, i.e., only accessible for other classes in the same "modle.nonphysical" package. 
 */

@MappedSuperclass
abstract class Auditable {
	
	@CreatedDate()
	private Timestamp creationTime;
	@LastModifiedDate
	private Timestamp updateTime;
	
	@Column(length=45)
	@CreatedBy
	private String createdBy;
		
	@Column(length=45)
	@LastModifiedBy
	private String updateBy;	

}
