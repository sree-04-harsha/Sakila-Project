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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;  // Assuming PostgreSQL uses SERIAL for auto-incrementing primary keys

    @Column(name = "address", nullable = false)
    @NotEmpty(message = "Address is required")
	private String address;

    @Column(name = "address2")
    private String address2;

    @Column(name = "district", nullable = false)
    @NotEmpty(message = "District is required")
    private String district;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @NotNull(message = "City is required")
    private City city;

    @Column(name = "postal_code")
    @NotEmpty(message = "Postal code is required")
    @Size(max = 10, message = "Postal code cannot exceed 10 characters")
    private String postalCode;

    @Column(name = "phone", nullable = false)
    @NotEmpty(message = "Phone number is required")
    @Size(max = 20, message = "Phone number cannot exceed 20 characters")
    private String phone;

    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    
    public Address() {
		super();
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

    
}
