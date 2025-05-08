package com.counters.www.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.counters.www.dto.CountersDto;
import com.counters.www.model.Counters;
import com.counters.www.repo.CountersRepo;

@Service
public class CounterService {

	@Autowired
	private CountersRepo repo;

	@Autowired
	private ModelMapper mp;
	
	@Value("${project.image}")
	private String path;
	
	public ResponseEntity<List<Counters>> get() {
	     List<Counters> counters = repo.findAll();// finds all the data present
		 return ResponseEntity.ok(counters);
		    }

	public ResponseEntity<String> savecounter(CountersDto countersdto, MultipartFile image) throws IllegalStateException, IOException {
//		Counters counters = mp.map(countersdto, Counters.class);//saves the data at a time
//        repo.save(counters);
//        return ResponseEntity.ok("Data saved successfully");
		Optional <Counters> counters=repo.findByMobilenumberOrEmail(countersdto.getMobilenumber(),countersdto.getEmail());
		
		 if (counters.isPresent()) {
			 return ResponseEntity.status(HttpStatus.CONFLICT).body("User with given email or mobile already exists");//409-->HttpStatutus.CONFLICT
		 }else {
			 
			 File fs = new File(path);
				// creating directory if not exists
				if(!fs.exists()) {
					fs.mkdir();
				}
				
				// creating image as a directory
				String image_Filename = image.getOriginalFilename();
				File Storeimage = new File(fs,image_Filename);
				Storeimage.getAbsolutePath();
				// image storing
				image.transferTo(Storeimage); 
				//repo.save(counters1);
			 
				System.out.println("Image stored at: " +Storeimage.getAbsolutePath());
			
	            Counters counters1 = new Counters();
	            counters1.setCounterName(countersdto.getCounterName());
	            counters1.setOwner(countersdto.getOwner());
	            counters1.setEmail(countersdto.getEmail());
	            counters1.setMobilenumber(countersdto.getMobilenumber());
//	            counters1.setImage(countersdto.getImage());
	            counters1.setStatus(countersdto.isStatus());
	            counters1.setImage(Storeimage.getAbsolutePath());
	            repo.save(counters1);
	            return ResponseEntity.status(HttpStatus.CREATED).body("User inserted successfully");//201 CREATED
	        }
		 
		}
		 
	public ResponseEntity<?> getcounterdetails(Long id) {
		Optional <Counters> counters=repo.findById(id);//get counter details using id
		 if (counters.isPresent()) {
		return ResponseEntity.status(200).body(counters);
		 } else {
	            return ResponseEntity.status(404).body("Counter not found");
		 }
	}

	public ResponseEntity<?> updatecounter(Long id, CountersDto countersdto) {
		Optional<Counters> optionalcounters = repo.findById(id);//updates the counters using id

        if (optionalcounters.isPresent()) {
            Counters counters = optionalcounters.get();
           
            // for mobile number
             if(countersdto.getMobilenumber().equals(counters.getMobilenumber()))
            		 {
            	         counters.setMobilenumber(countersdto.getMobilenumber());
            		 }
             else {
            			 Optional<Counters> optionalcounters1 =repo.findByMobilenumber(countersdto.getMobilenumber());
            		 
            		 if(optionalcounters1.isEmpty()) {
            			 counters.setMobilenumber(countersdto.getMobilenumber());
            		 }else {
            			 return ResponseEntity.status(HttpStatus.CONFLICT).body("User with given mobile number already exists");
            		 }
             }
             //for updating email id
             if(countersdto.getEmail().equals(counters.getEmail()))
    		 {
    	         counters.setEmail(countersdto.getEmail());
    		 }
     else {
    			 Optional<Counters> optionalcounters1 =repo.findByEmail(countersdto.getEmail());
    		 
    		 if(optionalcounters1.isEmpty()) {
    			 counters.setEmail(countersdto.getEmail());
    		 }else {
    			 return ResponseEntity.status(HttpStatus.CONFLICT).body("User with given email already exists");
    		 }
     }
             // for remaining things 
             counters.setCounterName(countersdto.getCounterName());
             counters.setOwner(countersdto.getOwner());
//             counters.setImage(countersdto.getImage());
             repo.save(counters);
             return ResponseEntity.ok("Record Updated Successfully");
        }
        
        else {
        	return ResponseEntity.status(404).body("Counter not found");
        }
	//	return null;
		
	}
//            counters.setCounterName(countersdto.getCounterName());//updates only the req details like countername
////            mp.map(countersdto, counters); // Map only fields without overriding the ID
//            repo.save(counters);
//            return ResponseEntity.ok("Record Updated Successfully");
//        } else {
//            return ResponseEntity.status(404).body("Counter not found");
//        }
//	}

	public ResponseEntity<?> deletecounter(Long id) {
		Optional <Counters> counter=repo.findById(id);//finds data counter details based on id 
		 if (counter.isPresent()) {
	        Counters bhanu = counter.get();//getting the data from the counters table
	        repo.delete(bhanu); //deleting the data
	        return ResponseEntity.ok("Record Deleted Successfully");
	    } else {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Counter not found");
	    }
	}

	public boolean updateStatusById(int id, boolean status) {
		if(repo.existsById(id)) {
			Optional<Counters> counter = repo.findById(id);
			Counters updateCounter = counter.get();
			updateCounter.setStatus(status);	
			repo.save(updateCounter);
			return true;
		} else {
			return false;
		}
	}

	public ResponseEntity<?> uploadimage(MultipartFile image) throws IllegalStateException, IOException {
		
		File fs = new File(path);
		// creating directory if not exists
		if(!fs.exists()) {
			fs.mkdir();
		}
		// creating image as a directory
		String image_Filename = image.getOriginalFilename();
		File Storeimage = new File(fs,image_Filename);
		Storeimage.getAbsolutePath();
		// image storing
		image.transferTo(Storeimage); 
		
		System.out.println("Image stored at: " +Storeimage.getAbsolutePath());
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Image was stored"+ Storeimage.getAbsolutePath());
	}
}
