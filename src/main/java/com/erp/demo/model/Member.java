package com.erp.demo.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Member implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mid;
	@Column(length = 255, unique = false)
	private String username;
	@Column(length = 255)
	private String password;
	@Column(length = 45)
	private String name;
	@Column(length = 1)
	private String sex;
	@Column(name = "Nationalld", columnDefinition = "char(10)")
	private String nationalId;
	private Date birthDate;
	private String cellphone;
	@Column(length = 4)
	private String landlineprefix;
	@Column(length = 8)
	private String landline;
	@Column(length = 255)
	private String email;
	private Integer postalCode;
	private String address;
	private String footnote;
	@CreatedDate
	private Instant createTime;
	@LastModifiedDate
	private Timestamp updateTime;
	@LastModifiedBy
	@Column(length = 45)
	private String updateName;

}
