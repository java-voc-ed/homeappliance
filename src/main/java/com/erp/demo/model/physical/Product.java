package com.erp.demo.model.physical;

import jakarta.persistence.Entity;
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
	private Integer pid;
	private String sku;
	private String name;
	private Integer stock;
	private Integer price;
}
