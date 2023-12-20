package com.cg.sakila.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.enitites.Payment;
import com.cg.sakila.exception.CustomException;
import com.cg.sakila.repositories.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	private PaymentRepository paymentRepository;

	@Autowired
	public PaymentServiceImpl(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@Override
	public List<Map<String, Object>> getRevenueByDate() throws CustomException {
		List<Map<String, Object>> list = paymentRepository.getRevenueByDate();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Revenue by date are not found.. ");
		}
	}

	@Override
	public List<Map<String, Object>> getRevenueByDateAndStore(int storeId) throws CustomException {
		List<Map<String, Object>> list = paymentRepository.getRevenueByDateAndStore(storeId);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Revenue by date and store are not found.. ");
		}
	}

	@Override
	public List<Map<String, Object>> getRevenueByFilm() throws CustomException {
		List<Map<String, Object>> list = paymentRepository.getRevenueByFilm();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Revenue by film are not found.. ");
		}
	}

	@Override
	public List<Object[]> calculateRevenueByFilmStoreWise(int filmId) throws CustomException {
		List<Object[]> list = paymentRepository.calculateRevenueByFilmStoreWise(filmId);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Revenue by film and store are not found.. ");
		}
	}

	@Override
	public List<Map<String, Object>> getRevenueByFilmAndStoreId(int storeId) throws CustomException {
		List<Map<String, Object>> list = paymentRepository.getRevenueByFilmAndStoreId(storeId);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Revenue by film and storeId are not found.. ");
		}
	}

	@Override
	public String addPayment(Payment payment) throws CustomException {
		if (payment == null) {
			throw new CustomException("Payment object is null");
		}

		paymentRepository.save(payment);
		return "Record Created Successfully";
	}

	@Override
	public List<Payment> getAllPayments() throws CustomException {
		List<Payment> list = paymentRepository.findAll();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("All Payments are not found.. ");
		}
	}

	@Override
	public List<Payment> getLatest100Payments() throws CustomException {
		List<Payment> list = paymentRepository.findTop100ByOrderByPaymentDateDesc();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Payments are not found.. ");
		}
	}

}