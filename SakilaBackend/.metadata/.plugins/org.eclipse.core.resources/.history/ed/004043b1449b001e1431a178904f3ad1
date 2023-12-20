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

	List<Customer> getCustomersByCity(String city);

	List<Customer> getCustomersByCountry(String country);

	Customer assignAddressToCustomer(int customerId, Address address);

	List<Customer> findInactiveCustomers();

	Customer findCustomerByPhone(String phone);

	Customer updateCustomerFirstName(int id, String firstName);

	Customer updateCustomerLastName(int id, String lastName);

	Customer updateCustomerEmail(int id, String email);

	Optional<Customer> assignStoreToCustomer(int id, int storeId);

	Customer updateCustomerPhone(int customerId, String phone);

	Customer addCustomer(Customer customer);

	Customer saveCustomer(Customer customer);

	Customer getCustomerById(int id);

}
