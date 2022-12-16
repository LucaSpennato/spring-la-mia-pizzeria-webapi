package org.generation.italy.demo.pojo;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Table
@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Size(min=4, max=20)
	@Column(length=20)
	private String name;
	
	@Lob
	@Nullable
	@Size(max=10000)
	@Column(length=10000)
	private String description;
	
	@NotNull(message="Price cannot be null")
	@Min(value=1)
	@Column
	private int price;
	
	public Pizza() {}
	
	public Pizza(String name, String description, int price) {
		setName(name);
		setDescription(description);
		setPrice(price);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getPrice() {
		return price;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
