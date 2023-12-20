package com.cg.sakila.serviceImplTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.sakila.enitites.Customer;
import com.cg.sakila.enitites.Film;
import com.cg.sakila.enitites.Inventory;
import com.cg.sakila.enitites.Rental;
import com.cg.sakila.enitites.Staff;
import com.cg.sakila.repositories.RentalRepository;
import com.cg.sakila.service.RentalServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RentalServiceImplTest {

	@Mock
	private RentalRepository rentalRepository;

	@InjectMocks
	private RentalServiceImpl rentalService;

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

		// Mock the repository method
		when(rentalRepository.findAll()).thenReturn(rentals);

		// Call the service method
		List<Rental> result = rentalService.getAllRentals();

		// Verify the result
		assertEquals(rentals, result);
		verify(rentalRepository, times(1)).findAll();
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

		// Mock the repository method
		when(rentalRepository.findTop10RentedFilms()).thenReturn(films);

		// Call the service method
		List<Film> result = rentalService.getTopTenRentedFilms();

		// Verify the result
		assertEquals(films, result);
		verify(rentalRepository, times(1)).findTop10RentedFilms();
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

		// Mock the repository method
		when(rentalRepository.findFilmsRentedByCustomer(customerId)).thenReturn(films);

		// Call the service method
		List<Film> result = rentalService.getFilmsRentedByCustomer(customerId);

		// Verify the result
		assertEquals(films, result);
		verify(rentalRepository, times(1)).findFilmsRentedByCustomer(customerId);
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

		// Mock the repository method
		when(rentalRepository.findTop10RentedFilmsByStore(storeId)).thenReturn(films);

		// Call the service method
		List<Film> result = rentalService.getTopTenRentedFilmsByStore(storeId);

		// Verify the result
		assertEquals(films, result);
		verify(rentalRepository, times(1)).findTop10RentedFilmsByStore(storeId);
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

		// Mock the repository method
		when(rentalRepository.findCustomersWithPendingReturns(storeId)).thenReturn(customers);

		// Call the service method
		List<Customer> result = rentalService.getCustomersWithPendingReturns(storeId);

		// Verify the result
		assertEquals(customers, result);
		verify(rentalRepository, times(1)).findCustomersWithPendingReturns(storeId);
	}

	@Test
	void rentFilmTest() {
		// Prepare test data
		Rental rental = new Rental();
		rental.setRentalId(1);
		rental.setRentalDate(Timestamp.valueOf(LocalDateTime.now()));
		rental.setInventory(new Inventory());
		rental.setCustomer(new Customer());
		rental.setReturnDate(Timestamp.valueOf(LocalDateTime.now()));
		rental.setStaff(new Staff());
		rental.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

		// Mock the repository method
		when(rentalRepository.save(rental)).thenReturn(rental);

		// Call the service method
		Rental result = rentalService.rentFilm(rental);

		// Verify the result
		assertEquals(rental, result);
		verify(rentalRepository, times(1)).save(rental);
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

		// Mock the repository methods
		when(rentalRepository.findByRentalId(rentalId)).thenReturn(rental);
		when(rentalRepository.save(rental)).thenReturn(rental);

		// Call the service method
		Rental result = rentalService.updateReturnDate(rentalId);

		// Verify the result
		assertEquals(rental, result);
		verify(rentalRepository, times(1)).findByRentalId(rentalId);
		verify(rentalRepository, times(1)).save(rental);
	}

}
