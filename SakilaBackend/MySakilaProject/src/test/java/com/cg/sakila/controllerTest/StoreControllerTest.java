package com.cg.sakila.controllerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.sakila.controllers.StoreController;
import com.cg.sakila.enitites.Address;
import com.cg.sakila.enitites.Staff;
import com.cg.sakila.enitites.Store;
import com.cg.sakila.service.StoreService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreControllerTest {

	@Mock
	private StoreService storeService;

	@InjectMocks
	private StoreController storeController;

	@Test
	void testFetchAllAddress() {
		List<Address> addresses = new ArrayList<>();
		// Add addresses to the list
		Address address1 = new Address();
		// Set address fields
		addresses.add(address1);

		when(storeService.fetchAllAddress()).thenReturn(addresses);

		List<Address> fetchedAddresses = storeController.fetchAllAddress();

		assertNotNull(fetchedAddresses);
		assertEquals(addresses, fetchedAddresses);

		verify(storeService, times(1)).fetchAllAddress();
	}

	@Test
	void testAssignAddressToStore() {
		int storeId = 1;
		Address address = new Address();
		Store store = new Store();
		store.setStoreId(storeId);

		when(storeService.getStoreById(storeId)).thenReturn(store);

		ResponseEntity<Store> response = storeController.assignAddressToStore(storeId, address);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(store, response.getBody());

		verify(storeService, times(1)).assignAddressToStore(storeId, address);
		verify(storeService, times(1)).getStoreById(storeId);
	}

	@Test
	void testGetStoresByCity() {
		String city = "New York";

		List<Store> stores = new ArrayList<>();
		// Add stores to the list
		Store store1 = new Store();
		store1.setStoreId((int) 1);
		store1.setStaff(new Staff());
		store1.setAddress(new Address());
		store1.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		stores.add(store1);

		// Create Address objects and add them to the list
		List<Address> addresses = new ArrayList<>();
		// Add addresses to the list
		Address address1 = new Address();
		// Set address fields
		addresses.add(address1);

		when(storeService.getStoresByCity(city)).thenReturn(stores);

		ResponseEntity<List<Store>> response = storeController.getStoresByCity(city);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(stores, response.getBody());

		verify(storeService, times(1)).getStoresByCity(city);
	}

	@Test
	void testGetStoresByCountry() {
		String country = "USA";

		List<Store> stores = new ArrayList<>();
		// Add stores to the list
		Store store1 = new Store();
		store1.setStoreId((int) 1);
		store1.setStaff(new Staff());
		store1.setAddress(new Address());
		store1.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		stores.add(store1);

		// Create Address objects and add them to the list
		List<Address> addresses = new ArrayList<>();
		// Add addresses to the list
		Address address1 = new Address();
		// Set address fields
		addresses.add(address1);

		when(storeService.getStoresByCountry(country)).thenReturn(stores);

		ResponseEntity<List<Store>> response = storeController.getStoresByCountry(country);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(stores, response.getBody());

		verify(storeService, times(1)).getStoresByCountry(country);
	}

	@Test
	void testGetStoreByPhoneNumber() {
		String phone = "1234567890";

		Store store = new Store();

		when(storeService.getStoreByPhoneNumber(phone)).thenReturn(store);

		ResponseEntity<Store> response = storeController.getStoreByPhoneNumber(phone);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(store, response.getBody());

		verify(storeService, times(1)).getStoreByPhoneNumber(phone);
	}

	@Test
	void testUpdateStorePhoneNumber() {
		int storeId = 1;
		String phone = "1234567890";
		Store store = new Store();
		store.setStoreId(storeId);

		when(storeService.getStoreById(storeId)).thenReturn(store);

		ResponseEntity<Store> response = storeController.updateStorePhoneNumber(storeId, phone);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(store, response.getBody());

		verify(storeService, times(1)).updateStorePhoneNumber(storeId, phone);
		verify(storeService, times(1)).getStoreById(storeId);
	}
}
