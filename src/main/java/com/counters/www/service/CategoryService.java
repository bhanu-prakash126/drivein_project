package com.counters.www.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.counters.www.dto.CategoryDto;
//import com.counters.www.dto.CountersDto;
import com.counters.www.model.Category;
import com.counters.www.model.Counters;
import com.counters.www.repo.CategoryRepo;
import com.counters.www.repo.CountersRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryrepo;
	
	@Autowired
	private CountersRepo repo;

	@Autowired
	private ModelMapper mp;
	
	public ResponseEntity<String> savecategory(CategoryDto categorydto) {
//		Category category = mp.map(categorydto, Category.class);//saves the data at a time using postmapping
//      categoryrepo.save(category);
//      return ResponseEntity.ok("Data saved successfully");
		Optional<Counters> counterSample = repo.findByMobilenumber(categorydto.getMobilenumber());
		if(counterSample.isPresent()) {             //finds the mobile number from counterrepo and saves in the categoryrepo
			Counters counter = counterSample.get();
			Category category = new Category();
//			category.setId(null);
			category.setName(categorydto.getName());
			category.setStatus(categorydto.isStatus());
			category.setCounters(counter);
			categoryrepo.save(category);
			return ResponseEntity.ok("Data saved successfully");
		} else {
			throw new RuntimeException("counter not found particular mobile number");
		}
	}

	public ResponseEntity<?> getcategory(Long id) {
		Optional<Category> category = (Optional<Category>) categoryrepo.findById(id);//get counter details using id
		  if (category.isPresent()) {
		return ResponseEntity.status(200).body(category);
		  }else {
	            return ResponseEntity.status(404).body("Category not found");
	        }
	}

	public ResponseEntity<?> get() {
		 List<Category> category = categoryrepo.findAll();// finds all the data present
		 return ResponseEntity.ok(category);
	}

	public ResponseEntity<?> updatecategory(Long id, CategoryDto categorydto) {
		Optional<Category> optionalcategory = categoryrepo.findById(id);//updates the category using id

        if (optionalcategory.isPresent()) {
            Category category = optionalcategory.get();
            category.setName(categorydto.getName());//updates only the req details like categoryname
//            mp.map(categorydto, category); // Map only fields without overriding the ID
            categoryrepo.save(category);
            return ResponseEntity.ok("Record Updated Successfully");
        } else {
            return ResponseEntity.status(404).body("Category not found");
        }
	}

	public ResponseEntity<?> deletecategory(Long id) {
		Optional <Category> category=categoryrepo.findById(id);//finds data counter details based on id 
		 if (category.isPresent()) {
	        Category bhanu = category.get();//getting the data from the counters table
	        
	        categoryrepo.delete(bhanu); //deleting the data
	        return ResponseEntity.ok("Record Deleted Successfully");
	    } else {
	        return ResponseEntity.status(404).body("Category not found");
	    }
	}

//	public Category addCategory(CategoryDto categorydto) {
//		Optional<Counters> counterSample = repo.findByMobilenumber(categorydto.getMobilenumber());
//		if(counterSample.isPresent()) {             //finds the mobile number from counterrepo and saves in the categoryrepo
//			
//			Counters counter = counterSample.get();
//			Category category = new Category();
//			category.setId(null);
//			category.setName(categorydto.getName());
//			category.setStatus(categorydto.isStatus());
//			category.setCounters(counter);
//			return categoryrepo.save(category);
//		} else {
//			throw new RuntimeException("counter not found particular mobile number");
//		}
//	}

	public boolean updateStatusById(int id, boolean status) {
		if(categoryrepo.existsById(id)) {
			Optional<Category> category = categoryrepo.findById(id);
			Category updateCategory = category.get();
			updateCategory.setStatus(status);
			categoryrepo.save(updateCategory);
			return true;
		} else {
			return false;
		}
	}
}
