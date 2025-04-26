package com.counters.www.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Items {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private Long id;
	private String name;
	private int quantity;
	private double price;
	private boolean status=true;
	

	@ManyToOne
	@JoinColumn(name="category_id")
	@JsonBackReference
	private Category category;
	

	
}
