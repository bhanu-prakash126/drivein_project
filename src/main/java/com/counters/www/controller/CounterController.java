package com.counters.www.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.counters.www.dto.CountersDto;
import com.counters.www.service.CounterService;

@RestController
@RequestMapping("/api")
public class CounterController {

	@Autowired
	private CounterService service;

	@GetMapping("/check")
	private ResponseEntity<String> successFullyChecked() {
		return ResponseEntity.ok("successfully checked");
	}

	@GetMapping("/allcounters")
	private ResponseEntity<?> get() {
		return service.get();
	}

//	@PostMapping("/save")
//	public ResponseEntity<String> savecounter(@RequestBody CountersDto countersdto) {
//		return service.savecounter(countersdto);
//	}
	@PostMapping(value="/save",consumes="multipart/form-data")
	public ResponseEntity<String> savecounter(@ModelAttribute CountersDto countersdto,@RequestParam MultipartFile image) throws IllegalStateException, IOException {
		return service.savecounter(countersdto,image);
	}

	@GetMapping("/getcounter")
	private ResponseEntity<?> getcounterdetails(@RequestParam Long id)// getcounter?id=1
	{
		return service.getcounterdetails(id);
	}

	@PutMapping("/updatecounter")
	private ResponseEntity<?> updatecounter(@RequestParam Long id, @RequestBody CountersDto countersdto) {
		return service.updatecounter(id, countersdto);
	}

	@DeleteMapping("/deletecounter/{id}")
	private ResponseEntity<?> deletecounter(@PathVariable Long id) {
		return service.deletecounter(id);
	}

	@PutMapping("updateStatus/{id}") // updates status based on id
	public ResponseEntity<String> updateStatusById(@PathVariable int id, @RequestParam boolean status) {// 3?status=false
		if (service.updateStatusById(id, status)) {
			return ResponseEntity.ok("counter status updated Successfully ! ");
		} else {
			return ResponseEntity.status(401).body("counter not exists with id : " + id);
		}
	}

	// api for uploading image for the counter profile
	@PostMapping("/upload")
	public ResponseEntity<?> fileUpload(@RequestPart("image") MultipartFile image)
			throws IllegalStateException, IOException {
		return service.uploadimage(image);
	}
	
}
