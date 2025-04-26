package com.counters.www.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemsDto {
	private Long id;
	private String name;
	private int quantity;
	private double price;
	private boolean status;
	private Long categoryId;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	@JsonBackReference
	private CategoryDto category;
}
