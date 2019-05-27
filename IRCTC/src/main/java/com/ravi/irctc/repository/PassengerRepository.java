package com.ravi.irctc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.irctc.entity.PassengerEntity;
@Repository
public interface PassengerRepository extends JpaRepository<PassengerEntity,Integer> {

}
