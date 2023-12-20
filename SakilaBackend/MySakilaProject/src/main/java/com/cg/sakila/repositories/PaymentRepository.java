package com.cg.sakila.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.sakila.enitites.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	@Query("SELECT new map(p.paymentDate as paymentDate, p.amount as amount) FROM Payment p")
	// @Query("SELECT p.paymentDate AS paymentDate, SUM(p.amount) AS amount FROM
	// Payment p GROUP BY p.paymentDate")
	List<Map<String, Object>> getRevenueByDate();

	// @Query("SELECT new map(p.paymentDate as paymentDate, p.amount as amount) FROM
	// Payment p WHERE p.staff.store.storeId = :storeId")
	@Query("SELECT p.paymentDate AS paymentDate, SUM(p.amount) AS amount " + "FROM Payment p "
			+ "WHERE p.staff.store.storeId = :storeId " + "GROUP BY p.paymentDate " + "ORDER BY p.paymentDate DESC")
	List<Map<String, Object>> getRevenueByDateAndStore(int storeId);

	@Query("SELECT p.rental.inventory.film.title AS filmTitle, SUM(p.amount) AS amount FROM Payment p GROUP BY p.rental.inventory.film.title")
	List<Map<String, Object>> getRevenueByFilm();

	@Query("SELECT p.rental.inventory.store.address.address, SUM(p.amount) " + "FROM Payment p "
			+ "WHERE p.rental.inventory.film.filmId = :filmId " + "GROUP BY p.rental.inventory.store.address.address")
	List<Object[]> calculateRevenueByFilmStoreWise(@Param("filmId") int filmId);

	@Query("SELECT p.rental.inventory.film.title AS filmTitle, SUM(p.amount) AS amount FROM Payment p WHERE p.rental.inventory.store.storeId = :storeId GROUP BY p.rental.inventory.film.title")
	List<Map<String, Object>> getRevenueByFilmAndStoreId(int storeId);

	List<Payment> findTop100ByOrderByPaymentDateDesc();

}
