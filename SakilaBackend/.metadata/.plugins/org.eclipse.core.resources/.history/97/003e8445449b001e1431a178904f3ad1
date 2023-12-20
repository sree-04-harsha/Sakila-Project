package com.cg.sakila.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.sakila.enitites.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	List<Customer> findByLastName(String lastName);

	List<Customer> findByFirstName(String firstName);

	Customer findByEmail(String email);

	List<Customer> findByActive(int active);

	List<Customer> findByAddress_City_City(String city);

	List<Customer> findByAddress_City_Country_Country(String country);

	Customer findByAddressPhone(String phone);

}
