package com.ravi.irctc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.irctc.entity.TicketEntity;
@Repository
public interface TicketRepository extends JpaRepository<TicketEntity,String> {

}
