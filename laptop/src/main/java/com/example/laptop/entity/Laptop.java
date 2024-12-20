package com.example.laptop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="laptops")
public class Laptop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
    @Column
    @NotNull
	private String laptopBrand;
	
	@Column
    @NotNull
	private String modelName;
	
	@Column
    @NotNull
	private String laptopTag;
	
	
	public Laptop() {
		
	}

	public Laptop(String laptopBrand, String modelName, String laptopTag) {
		super();
		this.laptopBrand = laptopBrand;
		this.modelName = modelName;
		this.laptopTag = laptopTag;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLaptopBrand() {
		return laptopBrand;
	}
	public void setLaptopBrand(String laptopBrand) {
		this.laptopBrand = laptopBrand;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getLaptopTag() {
		return laptopTag;
	}
	public void setLaptopTag(String laptopTag) {
		this.laptopTag = laptopTag;
	}
	
}