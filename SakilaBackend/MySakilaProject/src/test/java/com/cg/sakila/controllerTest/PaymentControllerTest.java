package com.cg.sakila.controllerTest;

import com.cg.sakila.controllers.PaymentController;
import com.cg.sakila.enitites.Payment;
import com.cg.sakila.service.PaymentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentControllerTest {

	@Mock
	private PaymentService paymentService;

	@InjectMocks
	private PaymentController paymentController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getRevenueByDateTest() {
		// Prepare test data
		List<Map<String, Object>> revenueList = new ArrayList<>();
		// Add some revenue data to the list
		Map<String, Object> revenueData = new HashMap<>();
		revenueData.put("paymentDate", Timestamp.valueOf("2023-07-01 10:30:00"));
		revenueData.put("amount", BigDecimal.valueOf(1000));
		revenueList.add(revenueData);

		// Mock the service method
		when(paymentService.getRevenueByDate()).thenReturn(revenueList);

		// Call the controller method
		List<Map<String, Object>> result = paymentController.getRevenueByDate();

		// Verify the result
		assertEquals(revenueList, result);
		verify(paymentService, times(1)).getRevenueByDate();
	}

	@Test
	void getRevenueByDateAndStoreTest() {
		// Prepare test data
		int storeId = 1;
		List<Map<String, Object>> revenueList = new ArrayList<>();
		// Add some revenue data to the list
		Map<String, Object> revenueData = new HashMap<>();
		revenueData.put("paymentDate", Timestamp.valueOf("2023-07-01 10:30:00"));
		revenueData.put("amount", BigDecimal.valueOf(1000));
		revenueList.add(revenueData);

		// Mock the service method
		when(paymentService.getRevenueByDateAndStore(storeId)).thenReturn(revenueList);

		// Call the controller method
		List<Map<String, Object>> result = paymentController.getRevenueByDateAndStore(storeId);

		// Verify the result
		assertEquals(revenueList, result);
		verify(paymentService, times(1)).getRevenueByDateAndStore(storeId);
	}

	@Test
	void getRevenueByFilmTest() {
		// Prepare test data
		List<Map<String, Object>> revenueList = new ArrayList<>();
		// Add some revenue data to the list
		Map<String, Object> revenueData = new HashMap<>();
		revenueData.put("filmTitle", "Film A");
		revenueData.put("amount", BigDecimal.valueOf(500));
		revenueList.add(revenueData);

		// Mock the service method
		when(paymentService.getRevenueByFilm()).thenReturn(revenueList);

		// Call the controller method
		List<Map<String, Object>> result = paymentController.getRevenueByFilm();

		// Verify the result
		assertEquals(revenueList, result);
		verify(paymentService, times(1)).getRevenueByFilm();
	}

	@Test
	void getRevenueByFilmAndStoreIdTest() {
		// Prepare test data
		int storeId = 1;
		List<Map<String, Object>> revenueList = new ArrayList<>();
		// Add some revenue data to the list
		Map<String, Object> revenueData = new HashMap<>();
		revenueData.put("filmTitle", "Film A");
		revenueData.put("amount", BigDecimal.valueOf(500));
		revenueList.add(revenueData);

		// Mock the service method
		when(paymentService.getRevenueByFilmAndStoreId(storeId)).thenReturn(revenueList);

		// Call the controller method
		List<Map<String, Object>> result = paymentController.getRevenueByFilmAndStoreId(storeId);

		// Verify the result
		assertEquals(revenueList, result);
		verify(paymentService, times(1)).getRevenueByFilmAndStoreId(storeId);
	}

	@Test
	void addPaymentTest() {
		// Prepare test data
		Payment payment = new Payment();
		// Set payment details
		payment.setPaymentId(1);
		payment.setAmount(BigDecimal.valueOf(100));
		payment.setPaymentDate(Timestamp.valueOf("2023-07-01 10:30:00"));

		// Mock the service method
		when(paymentService.addPayment(payment)).thenReturn("Record Created Successfully");

		// Call the controller method
		ResponseEntity<String> responseEntity = paymentController.addPayment(payment);

		// Verify the result
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Record Created Successfully", responseEntity.getBody());
		verify(paymentService, times(1)).addPayment(payment);
	}

	@Test
	void getAllPaymentsTest() {
		// Prepare test data
		List<Payment> paymentList = new ArrayList<>();
		// Add some payments to the list
		Payment payment1 = new Payment();
		payment1.setPaymentId(1);
		payment1.setAmount(BigDecimal.valueOf(100));
		payment1.setPaymentDate(Timestamp.valueOf("2023-07-01 10:30:00"));
		paymentList.add(payment1);

		Payment payment2 = new Payment();
		payment2.setPaymentId(2);
		payment2.setAmount(BigDecimal.valueOf(200));
		payment2.setPaymentDate(Timestamp.valueOf("2023-07-02 11:00:00"));
		paymentList.add(payment2);

		// Mock the service method
		when(paymentService.getAllPayments()).thenReturn(paymentList);

		// Call the controller method
		List<Payment> result = paymentController.getAllPayments();

		// Verify the result
		assertEquals(paymentList, result);
		verify(paymentService, times(1)).getAllPayments();
	}
}
