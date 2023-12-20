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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Entity
@Table(name = "staff")
public class Staff{

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_sequence")
    @jakarta.persistence.SequenceGenerator(name = "staff_sequence", sequenceName = "staff_sequence", allocationSize = 1)
    @Column(name = "staff_id", updatable = false)
    private int staffId;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull(message = "Address is required")
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Store is required")
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @NotNull(message = "Active status is required")
    @Column(name = "active", nullable = false)
    private int active;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @Column(name = "picture")
    private String picture;

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getActive() {
        return active;
    }

	public void setActive(int active) {
        this.active = active;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Staff() {
        super();
    }

    @Override
    public String toString() {
        return "Staff [staffId=" + staffId + ", firstName=" + firstName + ", lastName=" + lastName + ", address="
                + address + ", email=" + email + ", store=" + store + ", active=" + active + ", lastUpdate="
                + lastUpdate + ", picture=" + picture + "]";
    }
}
