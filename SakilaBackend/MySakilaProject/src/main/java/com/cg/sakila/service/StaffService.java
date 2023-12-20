package com.cg.sakila.service;

import java.util.List;

import com.cg.sakila.enitites.Address;
import com.cg.sakila.enitites.Staff;
import com.cg.sakila.enitites.Store;

public interface StaffService {
	
	Staff createStaff(Staff staff);

	List<Staff> searchStaffByLastName(String lastName);

	List<Staff> searchStaffByFirstName(String firstName);

	Staff searchStaffByEmail(String email);

	Staff assignAddressToStaff(int id, Address address);

	List<Staff> searchStaffByCity(String city);

	List<Staff> searchStaffByCountry(String country);

	Staff searchStaffByPhone(String phone);

	Staff updateStaffFirstName(int id, String firstName);

	Staff updateStaffLastName(int id, String lastName);

	Staff updateStaffEmail(int id, String email);

	Staff assignStoreToStaff(int id, Store store);

	Staff updateStaffPhone(int id, String phone);

	void deleteStaff(int id);

	Staff searchStaffByAddress(Address address);

	List<Staff> fetchAll();

	Staff getStaffById(int staffId);

	Staff updateStaff(Staff staff);
	// -------------------------------------

	Staff addStaff(Staff staff);
}
