package com.cg.sakila.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.sakila.controllers.CustomerController;
import com.cg.sakila.enitites.Address;
import com.cg.sakila.enitites.Customer;
import com.cg.sakila.service.CustomerServiceImpl;

class CustomerControllerTest {

	@Mock
	private CustomerServiceImpl customerService;

	@InjectMocks
	private CustomerController customerController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId((int) 1);
		customer.setStoreId((int) 1);
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setEmail("john.doe@example.com");
		Address address = new Address();
		address.setAddressId((int) 1);
		customer.setAddress(address);
		customer.setActive(1);
		customer.setCreateDate(new Timestamp(System.currentTimeMillis()));
		customer.setLastUpdate(new Timestamp(System.currentTimeMillis()));

		when(customerService.addCustomer(any(Customer.class))).thenReturn(customer);

		ResponseEntity<String> response = customerController.addCustomer(customer);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("Record Created Successfully");
		verify(customerService, times(1)).addCustomer(any(Customer.class));
	}

	@Test
	void testGetAllCustomers() {
		Customer customer1 = new Customer();
		customer1.setCustomerId((int) 1);
		customer1.setStoreId((int) 1);
		customer1.setFirstName("John");
		customer1.setLastName("Doe");
		customer1.setEmail("john.doe@example.com");
		Address address1 = new Address();
		address1.setAddressId((int) 1);
		customer1.setAddress(address1);
		customer1.setActive(1);
		customer1.setCreateDate(new Timestamp(System.currentTimeMillis()));
		customer1.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		Customer customer2 = new Customer();
		customer2.setCustomerId((int) 2);
		customer2.setStoreId((int) 1);
		customer2.setFirstName("Jane");
		customer2.setLastName("Smith");
		customer2.setEmail("jane.smith@example.com");
		Address address2 = new Address();
		address2.setAddressId((int) 2);
		customer2.setAddress(address2);
		customer2.setActive(1);
		customer2.setCreateDate(new Timestamp(System.currentTimeMillis()));
		customer2.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		List<Customer> customerList = Arrays.asList(customer1, customer2);

		when(customerService.findAllCustomers()).thenReturn(customerList);

		ResponseEntity<?> response = customerController.getAllCustomers();

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(customerList);
		verify(customerService, times(1)).findAllCustomers();
	}

	@Test
	void testSearchCustomersByLastName() {
		String lastName = "Doe";
		Customer customer1 = new Customer();
		customer1.setCustomerId((int) 1);
		customer1.setStoreId((int) 1);
		customer1.setFirstName("John");
		customer1.setLastName("Doe");
		customer1.setEmail("john.doe@example.com");
		Address address1 = new Address();
		address1.setAddressId((int) 1);
		customer1.setAddress(address1);
		customer1.setActive(1);
		customer1.setCreateDate(new Timestamp(System.currentTimeMillis()));
		customer1.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		Customer customer2 = new Customer();
		customer2.setCustomerId((int) 2);
		customer2.setStoreId((int) 1);
		customer2.setFirstName("Jane");
		customer2.setLastName("Doe");
		customer2.setEmail("jane.doe@example.com");
		Address address2 = new Address();
		address2.setAddressId((int) 2);
		customer2.setAddress(address2);
		customer2.setActive(1);
		customer2.setCreateDate(new Timestamp(System.currentTimeMillis()));
		customer2.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		List<Customer> customerList = Arrays.asList(customer1, customer2);

		when(customerService.findCustomersByLastName(lastName)).thenReturn(customerList);

		ResponseEntity<List<Customer>> response = customerController.searchCustomersByLastName(lastName);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(customerList);
		verify(customerService, times(1)).findCustomersByLastName(lastName);
	}

	@Test
	void testSearchCustomersByFirstName() {
		String firstName = "John";
		Customer customer1 = new Customer();
		customer1.setCustomerId((int) 1);
		customer1.setStoreId((int) 1);
		customer1.setFirstName("John");
		customer1.setLastName("Doe");
		customer1.setEmail("john.doe@example.com");
		Address address1 = new Address();
		address1.setAddressId((int) 1);
		customer1.setAddress(address1);
		customer1.setActive(1);
		customer1.setCreateDate(new Timestamp(System.currentTimeMillis()));
		customer1.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		Customer customer2 = new Customer();
		customer2.setCustomerId((int) 2);
		customer2.setStoreId((int) 1);
		customer2.setFirstName("John");
		customer2.setLastName("Smith");
		customer2.setEmail("john.smith@example.com");
		Address address2 = new Address();
		address2.setAddressId((int) 2);
		customer2.setAddress(address2);
		customer2.setActive(1);
		customer2.setCreateDate(new Timestamp(System.currentTimeMillis()));
		customer2.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		List<Customer> customerList = Arrays.asList(customer1, customer2);

		when(customerService.findCustomersByFirstName(firstName)).thenReturn(customerList);

		ResponseEntity<List<Customer>> response = customerController.searchCustomersByFirstName(firstName);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(customerList);
		verify(customerService, times(1)).findCustomersByFirstName(firstName);
	}

	@Test
	void testSearchCustomerByEmail() {
		String email = "john.doe@example.com";
		Customer customer = new Customer();
		customer.setCustomerId((int) 1);
		customer.setStoreId((int) 1);
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setEmail(email);
		Address address = new Address();
		address.setAddressId((int) 1);
		customer.setAddress(address);
		customer.setActive(1);
		customer.setCreateDate(new Timestamp(System.currentTimeMillis()));
		customer.setLastUpdate(new Timestamp(System.currentTimeMillis()));

		when(customerService.findCustomerByEmail(email)).thenReturn(customer);

		ResponseEntity<Customer> response = customerController.searchCustomerByEmail(email);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(customer);
		verify(customerService, times(1)).findCustomerByEmail(email);
	}
}
