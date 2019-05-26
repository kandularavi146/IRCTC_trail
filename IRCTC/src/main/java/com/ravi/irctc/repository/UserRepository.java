package com.ravi.irctc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravi.irctc.entity.CreditCardEntity;

public interface UserRepository extends JpaRepository<CreditCardEntity,String> {

}
