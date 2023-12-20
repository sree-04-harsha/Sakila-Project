package com.cg.sakila.controllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.sakila.controllers.RentalController;
import com.cg.sakila.enitites.Customer;
import com.cg.sakila.enitites.Film;
import com.cg.sakila.enitites.Inventory;
import com.cg.sakila.enitites.Rental;
import com.cg.sakila.enitites.Staff;
import com.cg.sakila.service.RentalService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RentalControllerTest {

	@Mock
	private RentalService rentalService;

	@InjectMocks
	private RentalController rentalController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllRentalsTest() {
		// Prepare test data
		List<Rental> rentals = new ArrayList<>();
		Rental rental1 = new Rental();
		rental1.setRentalId(1);
		rental1.setRentalDate(Timestamp.valueOf(LocalDateTime.now()));
		rental1.setInventory(new Inventory());
		rental1.setCustomer(new Customer());
		rental1.setReturnDate(Timestamp.valueOf(LocalDateTime.now()));
		rental1.setStaff(new Staff());
		rental1.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
		rentals.add(rental1);
		// Add more rentals to the list if needed

		// Mock the service method
		when(rentalService.getAllRentals()).thenReturn(rentals);

		// Call the controller method
		ResponseEntity<List<Rental>> responseEntity = rentalController.getAllRentals();

		// Verify the response
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(rentals, responseEntity.getBody());
		verify(rentalService, times(1)).getAllRentals();
	}

	@Test
	void getTopTenRentedFilmsTest() {
		// Prepare test data
		List<Film> films = new ArrayList<>();
		Film film1 = new Film();
		film1.setTitle("Film 1");
		film1.setDescription("Description 1");
		films.add(film1);
		// Add more films to the list if needed

		// Mock the service method
		when(rentalService.getTopTenRentedFilms()).thenReturn(films);

		// Call the controller method
		ResponseEntity<List<Film>> responseEntity = rentalController.getTopTenRentedFilms();

		// Verify the response
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(films, responseEntity.getBody());
		verify(rentalService, times(1)).getTopTenRentedFilms();
	}

	@Test
	void getFilmsRentedByCustomerTest() {
		// Prepare test data
		Long customerId = 1L;
		List<Film> films = new ArrayList<>();
		Film film1 = new Film();
		film1.setTitle("Film 1");
		film1.setDescription("Description 1");
		films.add(film1);
		// Add more films to the list if needed

		// Mock the service method
		when(rentalService.getFilmsRentedByCustomer(customerId)).thenReturn(films);

		// Call the controller method
		ResponseEntity<List<Film>> responseEntity = rentalController.getFilmsRentedByCustomer(customerId);

		// Verify the response
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(films, responseEntity.getBody());
		verify(rentalService, times(1)).getFilmsRentedByCustomer(customerId);
	}

	@Test
	void getTopTenRentedFilmsByStoreTest() {
		// Prepare test data
		Long storeId = 1L;
		List<Film> films = new ArrayList<>();
		Film film1 = new Film();
		film1.setTitle("Film 1");
		film1.setDescription("Description 1");
		films.add(film1);
		// Add more films to the list if needed

		// Mock the service method
		when(rentalService.getTopTenRentedFilmsByStore(storeId)).thenReturn(films);

		// Call the controller method
		ResponseEntity<List<Film>> responseEntity = rentalController.getTopTenRentedFilmsByStore(storeId);

		// Verify the response
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(films, responseEntity.getBody());
		verify(rentalService, times(1)).getTopTenRentedFilmsByStore(storeId);
	}

	@Test
	void getCustomersWithPendingReturnsTest() {
		// Prepare test data
		Long storeId = 1L;
		List<Customer> customers = new ArrayList<>();
		Customer customer1 = new Customer();
		customer1.setFirstName("John Doe");
		customers.add(customer1);
		// Add more customers to the list if needed

		// Mock the service method
		when(rentalService.getCustomersWithPendingReturns(storeId)).thenReturn(customers);

		// Call the controller method
		ResponseEntity<List<Customer>> responseEntity = rentalController.getCustomersWithPendingReturns(storeId);

		// Verify the response
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(customers, responseEntity.getBody());
		verify(rentalService, times(1)).getCustomersWithPendingReturns(storeId);
	}

	@Test
	void updateReturnDateTest() {
		// Prepare test data
		Integer rentalId = 1;
		Rental rental = new Rental();
		rental.setRentalId(rentalId);
		rental.setRentalDate(Timestamp.valueOf(LocalDateTime.now()));
		rental.setInventory(new Inventory());
		rental.setCustomer(new Customer());
		rental.setReturnDate(Timestamp.valueOf(LocalDateTime.now()));
		rental.setStaff(new Staff());
		rental.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

		// Mock the service method
		when(rentalService.updateReturnDate(rentalId)).thenReturn(rental);

		// Call the controller method
		ResponseEntity<Rental> responseEntity = rentalController.updateReturnDate(rentalId);

		// Verify the response
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(rental, responseEntity.getBody());
		verify(rentalService, times(1)).updateReturnDate(rentalId);
	}

}
