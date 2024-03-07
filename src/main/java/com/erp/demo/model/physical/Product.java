package com.erp.demo.model.physical;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Make this one Auditable after Spring Security is implemented.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	private String sku;
	private String name;
	private String SubCategory;
	private Boolean Enabled;
	private Integer price;
	private Integer Inventory;
	private String ShippingMethod;
	private Timestamp StartTime;
	private Timestamp EndTime;
	private String Specs;
	private String Desc;
}
