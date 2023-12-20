
package com.cg.sakila.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.sakila.enitites.Address;
import com.cg.sakila.enitites.Customer;
import com.cg.sakila.exception.CustomException;
import com.cg.sakila.repositories.CustomerRepository;

@Service

public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> findAllCustomers()throws CustomException {
		List<Customer> list = customerRepository.findAll();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Customers are not found.. ");
		}

	}

	@Override
	public List<Customer> findCustomersByLastName(String lastName)throws CustomException {
		List<Customer> list = customerRepository.findByLastName(lastName);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Customer is not found for this lastname ");
		}
	}

	@Override
	public List<Customer> findCustomersByFirstName(String firstName)throws CustomException {
		List<Customer> list = customerRepository.findByFirstName(firstName);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Customer is not found for this firstname");
		}
	}

	@Override
	public Customer findCustomerByEmail(String email)throws CustomException {
		Customer customer = customerRepository.findByEmail(email);
		if (customer == null) {
			throw new CustomException("Customer not found for email: " + email);
		}
		return customer;
	}

	@Override
	public List<Customer> getCustomersByCity(String city)throws CustomException {
		List<Customer> list = customerRepository.findByAddress_City_City(city);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Customer is not found for this city");
		}
	}

	@Override
	public List<Customer> getCustomersByCountry(String country)throws CustomException {
		List<Customer> list = customerRepository.findByAddress_City_Country_Country(country);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Customer is not found for this country ");
		}
	}

	@Override
	public List<Customer> findActiveCustomers()throws CustomException {
		List<Customer> list = customerRepository.findByActive(1);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Active customers are not found ");
		}
	}

	@Override
	public Customer assignAddressToCustomer(int customerId, Address address)throws CustomException {
		Customer customer = customerRepository.findById(customerId).orElse(null);
		if (customer == null) {
			throw new CustomException("Customer not found for ID: " + customerId);
		} else {
			customer.setAddress(address);
			return customerRepository.save(customer);
		}
	}


	@Override
	public List<Customer> findInactiveCustomers()throws CustomException {
		List<Customer> list = customerRepository.findByActive(0);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Active customers not found ");
		}
	}

	@Override
	public Customer findCustomerByPhone(String phone)throws CustomException {
		Customer customer = customerRepository.findByAddressPhone(phone);
		if (customer == null) {
			throw new CustomException("Customer not found for phone number: " + phone);
		}
		return customer;
	}

	@Override
	public Customer updateCustomerFirstName(int id, String firstName)throws CustomException {
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if (optionalCustomer.isEmpty()) {
			throw new CustomException("Customer not found for ID: " + id);
		}
		Customer customer = optionalCustomer.get();
		customer.setFirstName(firstName);
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomerLastName(int id, String lastName)throws CustomException {
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if (optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			customer.setLastName(lastName);
			return customerRepository.save(customer);
		}
		else {
			throw new CustomException("Customer not found for ID: " + id);
		}
	}

	@Override
	public Customer updateCustomerEmail(int id, String email)throws CustomException {
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if (optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			customer.setEmail(email);
			return customerRepository.save(customer);
		}
		else {
			throw new CustomException("Customer not found for ID: " + id);
		}
	}

	@Override
	public Optional<Customer> assignStoreToCustomer(int id, int storeId)throws CustomException {
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if (optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			customer.setStoreId(storeId);
			Customer updatedCustomer = customerRepository.save(customer);
			return Optional.of(updatedCustomer);
		}
		else {
			throw new CustomException("Customer not found for ID: " + id);
		}
	}

	@Override
	public Customer updateCustomerPhone(int customerId, String phone)throws CustomException {
		Customer customer = customerRepository.findById(customerId).orElse(null);
		if (customer != null) {
			Address address = customer.getAddress();
			if (address != null) {
				address.setPhone(phone);
				return customerRepository.save(customer);
			}
			else {
				throw new CustomException("Address not found for Customer ID: " + customerId);
			}
		} else {
			throw new CustomException("Customer not found for ID: " + customerId);
		}
	}

	/////////// ****************POST REMAINING******/////////////////////////////

	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

    @Override
    public Customer getCustomerById(int id) {
    	Optional<Customer> optionalCustomer =  customerRepository.findById(id);
    	if(optionalCustomer.isEmpty()) {
    		throw new CustomException("Customer Id Not Found");
    	}
        return optionalCustomer.get();
    }

}
