package com.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_tab")
public class Product {
	
	@Id
	@GeneratedValue
	@Column(name = "prod_id_col")
	private Integer prodId;

	@Column(name = "prod_code_col")
	private String prodCode;

	@Column(name = "prod_cost_col")
	private Double prodCost;

	@Column(name = "prod_vendor_col")
	private String prodVendor;

	@Column(name = "prod_gst_col")
	private Double prodGst;

	@Column(name = "prod_disc_col")
	private Double prodDisc;
}
