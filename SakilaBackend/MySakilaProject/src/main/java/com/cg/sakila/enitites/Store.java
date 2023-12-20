package com.cg.sakila.enitites;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "store")
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_sequence")
	@jakarta.persistence.SequenceGenerator(name = "store_sequence", sequenceName = "store_sequence", allocationSize = 1)
	@Column(name = "store_id", updatable = false)
	private int storeId;

	@JsonIgnore
	@NotNull(message = "Staff is required")
	@ManyToOne
	@JoinColumn(name = "manager_staff_id")
	private Staff staff;

	@NotNull(message = "Address is required")
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@Column(name = "last_update", nullable = false)
	private Timestamp lastUpdate;

	public Store() {
		super();
	}

	public Store(int storeId, Staff staff, Address address, Timestamp lastUpdate) {
		super();
		this.storeId = storeId;
		this.staff = staff;
		this.address = address;
		this.lastUpdate = lastUpdate;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", staff=" + staff + ", address=" + address + ", lastUpdate=" + lastUpdate
				+ "]";
	}
}
