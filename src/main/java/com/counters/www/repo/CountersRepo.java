package com.counters.www.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.counters.www.model.Counters;

@Repository
public interface CountersRepo extends JpaRepository<Counters, Long> {

	Optional<Counters> findByMobilenumber(String mobilenumber);

	boolean existsById(int id);

	Optional<Counters> findById(int id);

	Optional<Counters> findByMobilenumberOrEmail(String mobilenumber, String email);

	void deleteById(int id);

	Optional<Counters> findByEmail(String email);









}
