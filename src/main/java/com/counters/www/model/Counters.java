package com.counters.www.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Counters {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private Long id;
	private String counterName;
	private String owner;
	private String email;
	private String mobilenumber;
	private String image;
	private boolean status = true;
	

	@OneToMany(mappedBy ="counters")
	@JsonManagedReference
	private List<Category> categories;
	


	
}