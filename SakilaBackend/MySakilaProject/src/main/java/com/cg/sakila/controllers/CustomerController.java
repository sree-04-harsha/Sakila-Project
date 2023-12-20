package com.cg.sakila.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sakila.enitites.Address;
import com.cg.sakila.enitites.Customer;
import com.cg.sakila.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customer")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
	private CustomerService customerservice;

	//Add customer
	@PostMapping("/post")
	public ResponseEntity<String> addCustomer(@Valid @RequestBody Customer customer) {
		customerservice.addCustomer(customer);
		return new ResponseEntity<>("Record Created Successfully", HttpStatus.OK);
	}

	//Get all
	@GetMapping("/getAll")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customerList=customerservice.findAllCustomers();
				return new ResponseEntity<>(customerList,HttpStatus.OK);
	}

	// get by lastname
	@GetMapping("/lastname/{ln}")
	public ResponseEntity<List<Customer>> searchCustomersByLastName(@PathVariable("ln") String lastName) {
		List<Customer> customers = customerservice.findCustomersByLastName(lastName);
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	// get by first Name
	@GetMapping("/firstname/{fn}")
	public ResponseEntity<List<Customer>> searchCustomersByFirstName(@PathVariable("fn") String firstName) {
		List<Customer> customers = customerservice.findCustomersByFirstName(firstName);
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	//Get by Email
	@GetMapping("/email/{email}")
	public ResponseEntity<Customer> searchCustomerByEmail(@PathVariable("email") String email) {
		Customer customer = customerservice.findCustomerByEmail(email);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	//Get by City
	@GetMapping("/city/{city}")
	public ResponseEntity<List<Customer>> getCustomersByCity(@PathVariable("city") String city) {
		List<Customer> customerList=customerservice.getCustomersByCity(city);
		return new ResponseEntity<>(customerList,HttpStatus.OK);
	}

	//get by Country
	@GetMapping("/country/{country}")
	public ResponseEntity<List<Customer>> getCustomersByCountry(@PathVariable("country") String country) {
		List<Customer> customerList=customerservice.getCustomersByCountry(country);
		return new ResponseEntity<>(customerList,HttpStatus.OK);
	}

	//Assign Address to Customer
	@PutMapping("/{id}/address")
	public ResponseEntity<Customer> assignAddressToCustomer(@Valid @PathVariable("id") short customerId,@RequestBody Address address) {
		Customer customer = customerservice.assignAddressToCustomer(customerId, address);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	//Get Active Customers
	@GetMapping("/active")
	public ResponseEntity<List<Customer>> searchActiveCustomers() {
		List<Customer> customers = customerservice.findActiveCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	//Get Inactive Customers
	@GetMapping("/inactive")
	public ResponseEntity<List<Customer>> searchInactiveCustomers() {
		List<Customer> customers = customerservice.findInactiveCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	//Get Phone number
	@GetMapping("/phone/{phone}")
	public ResponseEntity<Customer> searchCustomerByPhone(@PathVariable("phone") String phone) {
		Customer customer = customerservice.findCustomerByPhone(phone);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	//Update First name
	@PutMapping("/update/fn/{id}")
	public ResponseEntity<Customer> updateCustomerFirstName(@Valid @PathVariable("id") Short id, String firstName) {
		Customer updatedCustomer = customerservice.updateCustomerFirstName(id, firstName);
		return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
	}

	//Update Last name
	@PutMapping("/update/ln/{id}")
	public ResponseEntity<Customer> updateCustomerLastName(@Valid @PathVariable("id") Short id, String lastname) {

		Customer updatedCustomer = customerservice.updateCustomerLastName(id, lastname);
		return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
	}

	//Update Email
	@PutMapping("/update/email/{id}")
	public ResponseEntity<Customer> updateCustomerEmail(@Valid @PathVariable("id") Short id, String email) {
		Customer updatedCustomer = customerservice.updateCustomerEmail(id, email);
		return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
	}

	//Assign Store
	@PutMapping("/update/store/{id}")
	public ResponseEntity<Customer> assignStoreToCustomer(@Valid @PathVariable("id") Short id, Byte storeId) {
		Optional<Customer> updatedCustomer = customerservice.assignStoreToCustomer(id, storeId);
		return new ResponseEntity<>(updatedCustomer.get(), HttpStatus.OK);
	}

	//update Phone number
	@PutMapping("/update/phone/{id}")
	public ResponseEntity<Customer> updateCustomerPhone(@Valid @PathVariable("id") Short customerId, String phone) {
		Customer updatedCustomer = customerservice.updateCustomerPhone(customerId, phone);
		return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
	}

	//get by Id
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable short id) {
		Customer customer=customerservice.getCustomerById(id);
		
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
}
