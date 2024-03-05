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

/**
 * Member generated by hbm2java
 */
@Entity
public class Member implements java.io.Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mid;
	@Column(length=255,unique=false)
	private String userName;
	@Column(length=255)
	private String password;
	@Column(length=45)
	private String name;
	@Column(length=1)
	private String sex;
	private String nationalld;
	private Date birthDate;

	private String cellphone;
	@Column(length=4)
	private String landlineprefix;
	@Column(length=8)
	private String landline;
	@Column(length=255)
	private String email;
	private byte postalCode;
	private String address;
	private String footnote;
	@CreatedDate()
	private Timestamp createTime;
	// 修改時自動創建時間
	@LastModifiedDate
	private Timestamp updateTime;
	@LastModifiedBy
	@Column(length=45)
	private String updateName;

	public Member() {
	}

	public Member(Integer mid,String userName, String password, String name, String sex, String nationalld, Date birthDate,
			String cellphone, String landlineprefix, String landline, String email, byte postalCode, String address,
			String footnote, Timestamp createTime, Timestamp updateTime, String updateName) {
		this.mid = mid;
		this.userName = userName;
		this.password = password;
		this.name = name;
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

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

}
