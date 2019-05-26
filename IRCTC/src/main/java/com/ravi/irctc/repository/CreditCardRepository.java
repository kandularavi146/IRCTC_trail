package com.ravi.irctc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ravi.irctc.entity.CreditCardEntity;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity,String> {

	/**
	 * @param cardNumber
	 * @param fare
	 */
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update CreditCardEntity set totalBill+:fare where cardNumber=:cardNumber")
	void updateSeatCount(@Param("cardNumber") String cardNumber, @Param("fare") String fare);	
}
