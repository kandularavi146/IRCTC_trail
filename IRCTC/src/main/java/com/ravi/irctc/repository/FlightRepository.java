package com.ravi.irctc.repository;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ravi.irctc.entity.FlightEntity;


@Repository
public interface FlightRepository extends JpaRepository<FlightEntity,String> {

	@Query("select f from FlightEntity f where f.source=:source and f.destination=:destination and f.flightAvailableDate=:jdate and f.seatCount>0")
	List<FlightEntity> findFlightDetails(@Param ("source") String source, @Param("destination") String destination, @Param("jdate") Calendar date);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update FlightEntity set sesatCount=seatCount-:noOfSeats where flightId=:flightId")
	void updateSeatsDetails(@Param ("flightId") String flightId, @Param("noOfSeats") int noOfSeats);
	
	@Query("select distinct(f.source) from FlightEntity f")
	List<String> findFlightSources();
	
	@Query("select distinct(f.destination) from FlightEntity f")
	List<String> findFlightDestiantions();
}
