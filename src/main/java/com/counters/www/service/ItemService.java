package com.counters.www.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.counters.www.dto.ItemsDto;
import com.counters.www.model.Category;
//import com.counters.www.model.Counters;
import com.counters.www.model.Items;
import com.counters.www.repo.CategoryRepo;
import com.counters.www.repo.ItemsRepo;

@Service
public class ItemService {
	
@Autowired
private ItemsRepo itemrepo;

@Autowired
private CategoryRepo categoryrepo;

@Autowired
private ModelMapper mp;

public ResponseEntity<?> get() {
	List<Items> items = itemrepo.findAll();// finds all the data present
	 return ResponseEntity.ok(items);
}

public ResponseEntity<String> saveitem(ItemsDto itemsdto) {
//	Items items = mp.map(itemsdto, Items.class);//saves the data at a time
//    itemrepo.save(items);
//    return ResponseEntity.ok("Data saved successfully");
	Optional<Category> categorySample = categoryrepo.findById(itemsdto.getCategoryId());
	if(categorySample.isPresent()) {             //finds the mobile number from counterrepo and saves in the categoryrepo
		Category category = categorySample.get();
		Items item = new Items();
//		category.setId(null);
		item.setName(itemsdto.getName());
		item.setPrice(itemsdto.getPrice());
		item.setQuantity(itemsdto.getQuantity());
		item.setStatus(itemsdto.isStatus());
		item.setCategory(category);
		itemrepo.save(item);
		return ResponseEntity.ok("Data saved successfully");
	} else {
		throw new RuntimeException("Item not found with particular category_id ");
	}
}


public ResponseEntity<?> getitemdetails(Long id) {
	Optional <Items> items=itemrepo.findById(id);//get item details using id
	 if (items.isPresent()) {
	return ResponseEntity.status(200).body(items);
}else {
    return ResponseEntity.status(404).body("Item not found");
}
}

public ResponseEntity<?> updateitem(Long id, ItemsDto itemsdto) {
	Optional<Items> optionalitems = itemrepo.findById(id);//updates the item using id

    if (optionalitems.isPresent()) {
        Items items = optionalitems.get();
        items.setName(itemsdto.getName());//updates only the req details like itemname
        items.setPrice(itemsdto.getPrice());
        //items.setQuantity(itemsdto.getQuantity());
//        mp.map(countersdto, counters); // Map only fields without overriding the ID
        itemrepo.save(items);
        return ResponseEntity.ok("Record Updated Successfully");
    } else {
        return ResponseEntity.status(404).body("Item not found");
    }
}

public ResponseEntity<?> deleteitem(Long id) {
	Optional <Items> items=itemrepo.findById(id);//finds data item details based on id 
	 if (items.isPresent()) {
       Items bhanu = items.get();//getting the data from the items table
       itemrepo.delete(bhanu); //deleting the data
       return ResponseEntity.ok("Record Deleted Successfully");
   } else {
       return ResponseEntity.status(404).body("Item not found");
   } 
}

public boolean updateStatusById(int id, boolean status) {
	if(itemrepo.existsById(id)) {
		Optional<Items> item = itemrepo.findById(id);
		Items updateItem = item.get();
		updateItem.setStatus(status);
		itemrepo.save(updateItem);
		return true;
	} else {
		return false;
	}
}

}
