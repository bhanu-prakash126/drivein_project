package com.counters.www.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.counters.www.model.Category;


@Repository
public interface CategoryRepo extends JpaRepository<Category,Long>{

	Optional<Category> findById(int id);

	boolean existsById(int id);



	
}
