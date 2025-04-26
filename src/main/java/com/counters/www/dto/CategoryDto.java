package com.counters.www.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	
	private Long id;
	private String name;
	private boolean Status;
	private String mobilenumber;
	
	@OneToMany(mappedBy = "category")
	@JsonManagedReference
    private List<ItemsDto> items;	
	
	@ManyToOne
	@JoinColumn(name = "counters_id")
	@JsonBackReference
	private CountersDto counters;
	
	
	
}
