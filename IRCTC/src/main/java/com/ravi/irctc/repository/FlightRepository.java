package com.ravi.irctc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ravi.irctc.entity.FlightEntity;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity,String> {

	
}
