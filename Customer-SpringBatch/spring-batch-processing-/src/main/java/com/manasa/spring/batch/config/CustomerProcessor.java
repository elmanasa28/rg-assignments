package com.manasa.spring.batch.config;

import org.springframework.batch.item.ItemProcessor;

import com.manasa.spring.batch.entity.Customer;

public class CustomerProcessor implements ItemProcessor<Customer,Customer> {

	@Override
	public Customer process(Customer customer) throws Exception {
		return customer;
	}

}
