package com.erp.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// Generated 2024�~3��2�� �U��7:02:46 by Hibernate Tools 6.3.1.Final

/**
 * PaymentMethod generated by hbm2java
 */
@Entity
public class PaymentMethod implements java.io.Serializable {
	@Id
	@Column(length=50)
	private String paymentCode;
	@Column(length=255)
	private String paymentMethod;
	@Column(length=10)
	private String providerCode;
	@Column(length=255)
	private String providerName;
	private char enabled;

	public PaymentMethod() {
	}

	public PaymentMethod(String paymentCode, String paymentMethod, String providerCode, String providerName,
			char enabled) {
		this.paymentCode = paymentCode;
		this.paymentMethod = paymentMethod;
		this.providerCode = providerCode;
		this.providerName = providerName;
		this.enabled = enabled;
	}

	public String getPaymentCode() {
		return this.paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getProviderCode() {
		return this.providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public String getProviderName() {
		return this.providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public char getEnabled() {
		return this.enabled;
	}

	public void setEnabled(char enabled) {
		this.enabled = enabled;
	}

}
