package com.cg.sakila.service;

import java.util.List;
import java.util.Map;

import com.cg.sakila.enitites.Address;
import com.cg.sakila.enitites.Customer;
import com.cg.sakila.enitites.Staff;
import com.cg.sakila.enitites.Store;

public interface StoreService {

	void updateStore(Store store);

	Store getStoreById(int id);

	List<Store> getStoresByCity(String city);

	List<Store> getStoresByCountry(String country);

	Store getStoreByPhoneNumber(String phone);

	void assignAddressToStore(int storeId, Address address);

	void updateStorePhoneNumber(int storeId, String phone);

	void assignManagerToStore(int storeId, Staff manager);

	List<Staff> getAllStaffByStoreId(int storeId);

	List<Staff> getManagerDetailsByStoreId(int storeId);

	List<Staff> getAllManagers();

	List<Store> fetchAllStore();

	Store findStoreByManagerStaffId(int managerStaffId);

	List<Staff> findAllStaff();

	List<Address> fetchAllAddress();

	List<Store> findAllStore();

	Map<Object, Object> findAllOfMyStore();

	List<Customer> getCustomersByStoreId(int storeId);
	
}
