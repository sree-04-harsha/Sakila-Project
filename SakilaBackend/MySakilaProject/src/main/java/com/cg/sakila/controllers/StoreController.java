package com.cg.sakila.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.sakila.enitites.Address;
import com.cg.sakila.enitites.Customer;
import com.cg.sakila.enitites.Staff;
import com.cg.sakila.enitites.Store;
import com.cg.sakila.service.StoreService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/store")
@CrossOrigin(origins = "http://localhost:4200")
public class StoreController {
	private final StoreService storeService;

	@Autowired
	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}
    
	@GetMapping("/all")
	public Map<Object, Object> stores() {
		return storeService.findAllOfMyStore();
	}

	@GetMapping("/address")
	public List<Address> fetchAllAddress() {
		return this.storeService.fetchAllAddress();
	}

	@PutMapping("/{id}/address")
	public ResponseEntity<Store> assignAddressToStore(@PathVariable("id") int storeId, @RequestBody Address address) {
		storeService.assignAddressToStore(storeId, address);
		Store store = storeService.getStoreById(storeId);
		return ResponseEntity.ok(store);
	}

	// working fine
	@GetMapping("/city/{city}")
	public ResponseEntity<List<Store>> getStoresByCity(@PathVariable("city") String city) {
		List<Store> stores = storeService.getStoresByCity(city);
		return ResponseEntity.ok(stores);
	}

	// working fine
	@GetMapping("/country/{country}")
	public ResponseEntity<List<Store>> getStoresByCountry(@PathVariable("country") String country) {
		List<Store> stores = storeService.getStoresByCountry(country);
		return ResponseEntity.ok(stores);
	}

	// working fine

	@GetMapping("/phone/{phone}")
	public ResponseEntity<Store> getStoreByPhoneNumber(@PathVariable("phone") String phone) {
		Store store = storeService.getStoreByPhoneNumber(phone);
		return ResponseEntity.ok(store);
	}

	// working fine
	@PutMapping("/update/phone/{id}")
	public ResponseEntity<Store> updateStorePhoneNumber(@PathVariable("id") int storeId, @RequestBody String phone) {
		storeService.updateStorePhoneNumber(storeId, phone);
		Store store = storeService.getStoreById(storeId);
		return ResponseEntity.ok(store);
	}

	// working fine
	@PutMapping("/{id}/manager")
	public ResponseEntity<Store> assignManagerToStore(@PathVariable("id") int managerStaffId,
			@RequestBody Staff manager) {
		storeService.assignManagerToStore(managerStaffId, manager);
		Store store = storeService.findStoreByManagerStaffId(managerStaffId);
		return ResponseEntity.ok(store);
	}

	// working fine
	@GetMapping("/staff/{id}")
	public ResponseEntity<List<Staff>> getAllStaffByStoreId(@PathVariable("id") int storeId) {
		List<Staff> staffList = storeService.getAllStaffByStoreId(storeId);
		return ResponseEntity.ok(staffList);
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<List<Customer>> getCustomersByStoreId(@PathVariable("id") int storeId) {
		List<Customer> customers = storeService.getCustomersByStoreId(storeId);
		return ResponseEntity.ok(customers);
	}

//working fine
	@GetMapping("/manager/{id}")
	public ResponseEntity<List<Staff>> getManagerDetailsByStoreId(@PathVariable("id") int storeId) {
		List<Staff> managers = storeService.getManagerDetailsByStoreId(storeId);
		return ResponseEntity.ok(managers);
	}

//working fine
	@GetMapping("/managers")
	public ResponseEntity<List<Staff>> getAllManagers() {
		List<Staff> managers = storeService.getAllManagers();
		return ResponseEntity.ok(managers);
	}
}