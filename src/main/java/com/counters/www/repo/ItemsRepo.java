package com.counters.www.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.counters.www.model.Items;
@Repository
public interface ItemsRepo extends JpaRepository<Items, Long>{

	boolean existsById(int id);

	Optional<Items> findById(int id);

}
