package com.erp.demo.model.physical;
// Generated 2024�~3��2�� �U��7:02:46 by Hibernate Tools 6.3.1.Final

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.erp.demo.model.nonphysical.AbstractAuditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Employee generated by hbm2java
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Employee extends AbstractAuditable implements java.io.Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EID")
	private Integer eid;
	
	private String username;
	private String password;
	private String name;
	private String departmentCode;
	private String roleCode;
	private String sex;

	@Column(name = "NationalId", columnDefinition = "char(10)")
	private String nationalId;

	private Date birthDate;
	private String cellphone;
	private String landlineprefix;
	private String landline;
	private String email;
	private Byte postalCode;
	private String address;
	private String footnote;
	
}
