package com.erp.demo.model;


import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member implements java.io.Serializable {

	@Id
	private String mid;
	private Integer id;
	private String username;
	private String password;
	private String name;
	private String sex;
	private String nationalId;
	private Date birthDate;
	private String cellphone;
	private String landlineprefix;
	private String landline;
	private String email;
	private Integer postalCode;
	private String address;
	private String footnote;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String updateName;

}
