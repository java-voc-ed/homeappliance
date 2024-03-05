package com.erp.demo.model;
// Generated 2024�~3��2�� �U��7:02:46 by Hibernate Tools 6.3.1.Final

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Employee generated by hbm2java
 */

@Entity
//對應資料庫表名稱
@Table(schema = "dbo", name = "Employee")
public class Employee {

	// 主鍵由數據庫自動維護(AUTO_INCREMENT)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "Id")
	private Integer id;
	
	@Column(name = "EID")
	private String EID;

	private String username;
	private String password;
	private String name;
	private String departmentCode;
	private String roleCode;
	private String sex;

	@Column(name = "Nationalld", columnDefinition = "char(10)")
	private String nationalld;

	private Date birthDate;
	private String cellphone;
	private String landlineprefix;
	private String landline;
	private String email;
	private byte postalCode;
	private String address;
	private String footnote;

	// 自動創建時間
	@CreatedDate()
	private Timestamp createTime;

	// 修改時自動創建時間
	@LastModifiedDate
	private Timestamp updateTime;

	@LastModifiedBy
	private String updateName;

	public Employee() {
	}

	public Employee(Integer id, String eId, String username, String password, String name, String departmentCode,
			String roleCode, String sex, String nationalld, Date birthDate, String cellphone, String landlineprefix,
			String landline, String email, byte postalCode, String address, String footnote, Timestamp createTime,
			Timestamp updateTime, String updateName) {
		this.id = id;
		this.EID = eId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.departmentCode = departmentCode;
		this.roleCode = roleCode;
		this.sex = sex;
		this.nationalld = nationalld;
		this.birthDate = birthDate;
		this.cellphone = cellphone;
		this.landlineprefix = landlineprefix;
		this.landline = landline;
		this.email = email;
		this.postalCode = postalCode;
		this.address = address;
		this.footnote = footnote;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.updateName = updateName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentCode() {
		return this.departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNationalld() {
		return this.nationalld;
	}

	public void setNationalld(String nationalld) {
		this.nationalld = nationalld;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getLandlineprefix() {
		return this.landlineprefix;
	}

	public void setLandlineprefix(String landlineprefix) {
		this.landlineprefix = landlineprefix;
	}

	public String getLandline() {
		return this.landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(byte postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFootnote() {
		return this.footnote;
	}

	public void setFootnote(String footnote) {
		this.footnote = footnote;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateName() {
		return this.updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getEID() {
		return EID;
	}

	public void setEID(String eID) {
		EID = eID;
	}

}
