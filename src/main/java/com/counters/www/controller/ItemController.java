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

import com.counters.www.dto.ItemsDto;
import com.counters.www.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	private ItemService service;
	
	@GetMapping("/check")
	private ResponseEntity<String> successFullyChecked(){
		return ResponseEntity.ok("successfully checked");
	}
	
	@GetMapping("/allitems")
	private ResponseEntity<?> get(){
		return service.get();
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveitem(@RequestBody ItemsDto itemsdto )
	{
		return service.saveitem(itemsdto);
	}
	
	@GetMapping("/getitem")
	private ResponseEntity<?> getitemdetails(@RequestParam Long id)//getitem?id=1
	{
		return service.getitemdetails(id);
	}
	
	@PutMapping("/updateitem")
	private ResponseEntity<?>updateitem(@RequestParam Long id,@RequestBody ItemsDto itemsdto)
	{
		return service.updateitem(id,itemsdto);
	}
	
	@DeleteMapping("/deleteitem/{id}")
	private ResponseEntity<?>deleteitem(@PathVariable Long id)
	{
		return service.deleteitem(id);
	}
	
	@PutMapping("updateStatus/{id}") //updates item status based on id
	public ResponseEntity<String> updateStatusById(@PathVariable int id,@RequestParam boolean status){//3?status=false
		if(service.updateStatusById(id,status)) {
			return ResponseEntity.ok("item status updated Successfully ! ");
		} else {
			return ResponseEntity.status(401).body("item not exists with id : "+id);
		}
	}

}
