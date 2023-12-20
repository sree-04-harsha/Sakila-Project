package com.cg.sakila.service;

import java.util.List;
import java.util.Optional;

import com.cg.sakila.enitites.Address;
import com.cg.sakila.enitites.Customer;



public interface CustomerService {

	List<Customer> findAllCustomers();

	List<Customer> findCustomersByLastName(String lastName);

	List<Customer> findCustomersByFirstName(String firstName);

	Customer findCustomerByEmail(String email);

	List<Customer> findActiveCustomers();
	
	List<Customer> findInactiveCustomers();

	List<Customer> getCustomersByCity(String city);

	List<Customer> getCustomersByCountry(String country);

	Customer assignAddressToCustomer(short customerId, Address address);
	
	Optional<Customer> assignStoreToCustomer(Short id, Byte storeId);

	Customer findCustomerByPhone(String phone);

	Customer updateCustomerFirstName(Short id, String firstName);

	Customer updateCustomerLastName(Short id, String lastName);

	Customer updateCustomerEmail(Short id, String email);	

	Customer updateCustomerPhone(Short customerId, String phone);

	Customer addCustomer(Customer customer);

	Customer getCustomerById(short id);

}
