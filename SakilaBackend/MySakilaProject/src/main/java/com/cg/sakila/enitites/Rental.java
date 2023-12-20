package com.cg.sakila.enitites;

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
@Table(name = "rental")
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_sequence")
	@jakarta.persistence.SequenceGenerator(name = "rental_sequence", sequenceName = "rental_sequence", allocationSize = 1)
	@Column(name = "rental_id", updatable = false)
	private int rentalId;

	@NotNull(message = "Rental date is required")
	@Column(name = "rental_date", nullable = false)
	private Timestamp rentalDate;

	@NotNull(message = "Inventory is required")
	@ManyToOne
	@JoinColumn(name = "inventory_id")
	private Inventory inventory;

	@NotNull(message = "Customer is required")
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "return_date")
	private Timestamp returnDate;

	@NotNull(message = "Staff is required")
	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Staff staff;

	@NotNull(message = "Last update is required")
	@Column(name = "last_update", nullable = false)
	private Timestamp lastUpdate;

	public Rental(int rentalId, Timestamp rentalDate, Inventory inventory, Customer customer, Timestamp returnDate,
			Staff staff, Timestamp lastUpdate) {
		super();
		this.rentalId = rentalId;
		this.rentalDate = rentalDate;
		this.inventory = inventory;
		this.customer = customer;
		this.returnDate = returnDate;
		this.staff = staff;
		this.lastUpdate = lastUpdate;
	}

	public Rental() {
		super();
	}

	public int getRentalId() {
		return rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public Timestamp getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Timestamp rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Timestamp getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Timestamp returnDate) {
		this.returnDate = returnDate;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Rental [rentalId=" + rentalId + ", rentalDate=" + rentalDate + ", inventory=" + inventory
				+ ", customer=" + customer + ", returnDate=" + returnDate + ", staff=" + staff + ", lastUpdate="
				+ lastUpdate + "]";
	}
}
