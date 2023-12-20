package com.cg.sakila.enitites;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer{
	/*
	 * @GeneratedValue(strategy = GenerationType.IDENTITY, generator =
	 * "customer_id_generator")
	 * 
	 * @SequenceGenerator(name = "customer_id_generator", sequenceName =
	 * "customer_customer_id_seq", initialValue = 600)
	 */
	@Id
	@Column(name = "customer_id")
	private int customerId; // Assuming PostgreSQL uses SERIAL for auto-incrementing primary keys

	@Column(name = "first_name", nullable = false)
	@NotBlank(message = "First name is required")
	@Size(max = 50, message = "First name cannot exceed 50 characters")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid name format")
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@NotEmpty(message = "Last name is required")
	@Size(max = 50, message = "Last name cannot exceed 50 characters")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid name format")
	private String lastName;

	@Column(name = "email")
	@Email(message = "Invalid email format")
	@Size(max = 30, message = "Email cannot exceed 100 characters")
	private String email;

	@Column(name = "active", nullable = false)
	private Integer active;

	@Column(name = "create_date", nullable = false)
	@NotNull(message = "Create date is required")
	private Timestamp createDate;

	@Column(name = "last_update", nullable = false)
	@NotNull(message = "Last update timestamp is required")
	private Timestamp lastUpdate;

	@Column(name = "store_id", nullable = false)
	@NotNull(message = "Store ID is required")
	private Integer storeId;

	@ManyToOne
	@JoinColumn(name = "address_id")
	@NotNull(message = "Address is required")
	private Address address;



	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
