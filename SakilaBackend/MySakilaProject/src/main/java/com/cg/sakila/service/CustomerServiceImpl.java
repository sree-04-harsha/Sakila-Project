
package com.cg.sakila.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.enitites.Address;
import com.cg.sakila.enitites.Customer;
import com.cg.sakila.exception.ResourceNotFound;
import com.cg.sakila.repositories.CustomerRepository;



@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	// Add Customer
	@Override
	public Customer addCustomer(Customer customer) {

		return customerRepository.save(customer);

	}

	// Display all customers
	@Override
	public List<Customer> findAllCustomers() throws ResourceNotFound {

		List<Customer> list = customerRepository.findAll();

		if (!list.isEmpty()) {
			return list;
		} else {
			throw new ResourceNotFound("Customers are not found.. ");
		}

	}

	// find customer By Id
	@Override
	public List<Customer> findCustomersByLastName(String lastName) throws ResourceNotFound {

		List<Customer> list = customerRepository.findByLastName(lastName);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new ResourceNotFound("Customer is not found for this lastname ");

		}
	}

	// find customer by First Name
	@Override
	public List<Customer> findCustomersByFirstName(String firstName) throws ResourceNotFound {

		List<Customer> list = customerRepository.findByFirstName(firstName);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new ResourceNotFound("Customer is not found for this firstname");
		}
	}

	// find customer by Email
	@Override
	public Customer findCustomerByEmail(String email) throws ResourceNotFound {

		Customer customer = customerRepository.findByEmail(email);

		if (customer == null) {

			throw new ResourceNotFound("Customer not found for email: " + email);

		}

		return customer;

	}

	// find customer by City
	@Override
	public List<Customer> getCustomersByCity(String city) throws ResourceNotFound {

		List<Customer> list = customerRepository.findByAddress_City_City(city);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new ResourceNotFound("Customer is not found for this city"+city);

		}
	}

	// Search Customers by Country
	@Override
	public List<Customer> getCustomersByCountry(String country) throws ResourceNotFound {

		List<Customer> list = customerRepository.findByAddress_City_Country_Country(country);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new ResourceNotFound("Customer is not found for this country ");

		}
	}

	// Search Active Users
	@Override
	public List<Customer> findActiveCustomers() throws ResourceNotFound {

		List<Customer> list = customerRepository.findByActive(1);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new ResourceNotFound("Active customers are not found ");
		}
	}

	// Assign Address to Customer
	@Override
	public Customer assignAddressToCustomer(short customerId, Address address) throws ResourceNotFound {

		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFound("Customer not found for ID: " + customerId);

		}
		Customer customer = optionalCustomer.get();

		customer.setAddress(address);
		return customerRepository.save(customer);

	}

	// Search Inactive Customers
	@Override
	public List<Customer> findInactiveCustomers() throws ResourceNotFound {

		List<Customer> list = customerRepository.findByActive(0);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new ResourceNotFound("Active customers not found ");

		}
	}

	// Search customer by phone number
	@Override
	public Customer findCustomerByPhone(String phone) throws ResourceNotFound {

		Customer customer = customerRepository.findByAddressPhone(phone);

		if (customer == null) {

			throw new ResourceNotFound("Customer not found for phone number: " + phone);

		}

		return customer;
	}

	// Update First Name
	@Override
	public Customer updateCustomerFirstName(Short id, String firstName) throws ResourceNotFound {

		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFound("Customer not found for ID: " + id);

		}
		Customer customer = optionalCustomer.get();

		customer.setFirstName(firstName);

		return customerRepository.save(customer);

	}

	// Update Last Name
	@Override
	public Customer updateCustomerLastName(Short id, String lastName) throws ResourceNotFound {

		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFound("Customer not found for ID: " + id);

		}
		Customer customer = optionalCustomer.get();

		customer.setLastName(lastName);

		return customerRepository.save(customer);

	}

	// Update Email
	@Override
	public Customer updateCustomerEmail(Short id, String email) throws ResourceNotFound {

		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFound("Customer not found for ID: " + id);

		}
		Customer customer = optionalCustomer.get();

		customer.setEmail(email);

		return customerRepository.save(customer);
	}

	// Assign Store to Customer
	@Override
	public Optional<Customer> assignStoreToCustomer(Short id, Byte storeId) throws ResourceNotFound {

		Optional<Customer> optionalCustomer = customerRepository.findById(id);

		if (optionalCustomer.isPresent()) {

			Customer customer = optionalCustomer.get();

			customer.setStoreId(storeId);

			Customer updatedCustomer = customerRepository.save(customer);

			return Optional.of(updatedCustomer);

		}

		else {

			throw new ResourceNotFound("Customer not found for ID: " + id);

		}

	}

	// Update Phone number
	@Override
	public Customer updateCustomerPhone(Short customerId, String phone) throws ResourceNotFound {

		Customer customer = customerRepository.findById(customerId).orElse(null);

		if (customer != null) {

			Address address = customer.getAddress();

			if (address != null) {

				address.setPhone(phone);

				return customerRepository.save(customer);

			}

			else {

				throw new ResourceNotFound("Address not found for Customer ID: " + customerId);

			}

		} else {

			throw new ResourceNotFound("Customer not found for ID: " + customerId);

		}

	}

	// Find Customer By Id
	@Override
	public Customer getCustomerById(short id) throws ResourceNotFound {

		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFound("Customer not found for ID: " + id);

		}
		Customer customer = optionalCustomer.get();

		return customer;
	}
}
