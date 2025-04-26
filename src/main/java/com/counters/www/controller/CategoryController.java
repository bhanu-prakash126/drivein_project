package com.counters.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.counters.www.dto.CategoryDto;
import com.counters.www.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
@Autowired
private CategoryService service;

@GetMapping("/check")
private ResponseEntity<String> successFullyChecked(){
	return ResponseEntity.ok("successfully checked");
}
@GetMapping("/all") //get all category
	private ResponseEntity<?> get(){
	return service.get();
}
@PostMapping("/save")    //finds the mobile number from counterrepo and saves in the categoryrepo
public ResponseEntity<String> savecategory(@RequestBody CategoryDto categorydto )
{
	return service.savecategory(categorydto);
}

//@PostMapping("/add")
//public Category addCategory(@RequestBody CategoryDto categorydto) {
//	return service.addCategory(categorydto);
//}

@GetMapping("/getcategory")
private ResponseEntity<?> getcategory(@RequestParam Long id)//getcategory?id=1
{
	return service.getcategory(id);
}

@PutMapping("/updatecategory")
private ResponseEntity<?>updatecategory(@RequestParam Long id,@RequestBody CategoryDto categorydto)
{
	return service.updatecategory(id,categorydto);
}

@DeleteMapping("/deletecategory/{id}")
private ResponseEntity<?>deletecategory(@PathVariable Long id)
{
	return service.deletecategory(id);
}

@PutMapping("updateStatus/{id}") //updates status based on id
public ResponseEntity<String> updateStatusById(@PathVariable int id,@RequestParam boolean status){//3?status=false
	if(service.updateStatusById(id,status)) {    
		return ResponseEntity.ok("category status updated Successfully ! ");
	} else {
		return ResponseEntity.status(401).body("category not exists with id : "+id);
	}
}
}
