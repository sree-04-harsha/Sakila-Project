package com.cg.sakila.service;

import java.util.List;

import com.cg.sakila.enitites.Customer;
import com.cg.sakila.enitites.Film;
import com.cg.sakila.enitites.Rental;

public interface RentalService {
	
	List<Rental> getAllRentals();

	List<Film> getFilmsRentedByCustomer(Long customerId);

	List<Film> getTopTenRentedFilms();

	List<Film> getTopTenRentedFilmsByStore(Long storeId);

	List<Customer> getCustomersWithPendingReturns(Long storeId);

	Rental rentFilm(Rental rental);

	Rental updateReturnDate(Integer rentalId);

	Rental addRental(Rental rental);

}
