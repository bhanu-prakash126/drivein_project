package com.counters.www.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountersDto {
	
    private Long id;
	private String counterName;
	private String owner;
	private String email;
	private String mobilenumber;
//	private String image;
	private boolean status;
	
	@OneToMany(mappedBy ="counters")
	@JsonManagedReference
	private List<CategoryDto> categories;

}
