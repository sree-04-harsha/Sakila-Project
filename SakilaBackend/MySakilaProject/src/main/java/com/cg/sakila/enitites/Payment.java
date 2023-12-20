package com.cg.sakila.enitites;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_sequence")
	@jakarta.persistence.SequenceGenerator(name = "payment_sequence", sequenceName = "payment_sequence", allocationSize = 1)
	@Column(name = "payment_id", updatable = false)
	private int paymentId;

	@NotNull(message = "Customer is required")
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customer customer;

	@NotNull(message = "Staff is required")
	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Staff staff;

	@NotNull(message = "Rental is required")
	@ManyToOne
	@JoinColumn(name = "rental_id")
	private Rental rental;

	@NotNull(message = "Amount is required")
	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@NotNull(message = "Payment date is required")
	@Column(name = "payment_date", nullable = false)
	private Timestamp paymentDate;

	@NotNull(message = "Last update is required")
	@Column(name = "last_update", nullable = false)
	private Timestamp lastUpdate;

	public Payment(int paymentId, Customer customer, Staff staff, Rental rental, BigDecimal amount,
			Timestamp paymentDate, Timestamp lastUpdate) {
		super();
		this.paymentId = paymentId;
		this.customer = customer;
		this.staff = staff;
		this.rental = rental;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.lastUpdate = lastUpdate;
	}

	public Payment() {
		super();
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", customer=" + customer + ", staff=" + staff + ", rental=" + rental
				+ ", amount=" + amount + ", paymentDate=" + paymentDate + ", lastUpdate=" + lastUpdate + "]";
	}

	// Getters and setters
}
