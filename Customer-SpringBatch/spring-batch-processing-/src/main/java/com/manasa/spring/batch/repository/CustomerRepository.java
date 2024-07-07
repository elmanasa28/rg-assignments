package com.manasa.spring.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.manasa.spring.batch.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
