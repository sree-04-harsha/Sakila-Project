package com.cg.sakila.serviceImplTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.sakila.enitites.Address;
import com.cg.sakila.enitites.Staff;
import com.cg.sakila.enitites.Store;
import com.cg.sakila.repositories.AddressRepository;
import com.cg.sakila.repositories.StoreRepository;
import com.cg.sakila.service.StoreServiceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreServiceImplTest {

	@Mock
	private StoreRepository storeRepository;

	@Mock
	private AddressRepository addressRepository;

	@InjectMocks
	private StoreServiceImpl storeService;

	@Test
	void testUpdateStorePhoneNumber() {
		int storeId = 1;
		String phone = "1234567890";

		Store store = new Store();
		store.setStoreId(storeId);
		Address address = new Address();
		address.setPhone("9876543210");
		store.setAddress(address);

		when(storeRepository.findById(storeId)).thenReturn(Optional.of(store));

		storeService.updateStorePhoneNumber(storeId, phone);

		assertEquals(phone, store.getAddress().getPhone());

		verify(storeRepository, times(1)).findById(storeId);
		verify(storeRepository, times(1)).save(store);
	}

	@Test
	void testGetStoreByPhoneNumber() {
		String phone = "1234567890";

		Store store = new Store();
		store.setAddress(new Address());
		store.getAddress().setPhone(phone);

		when(storeRepository.findByAddressPhone(phone)).thenReturn(store);

		Store retrievedStore = storeService.getStoreByPhoneNumber(phone);

		assertNotNull(retrievedStore);
		assertEquals(store, retrievedStore);

		verify(storeRepository, times(1)).findByAddressPhone(phone);
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

		when(storeRepository.findByAddressCityCountryCountry(country)).thenReturn(stores);

		List<Store> retrievedStores = storeService.getStoresByCountry(country);

		assertNotNull(retrievedStores);
		assertEquals(stores, retrievedStores);

		verify(storeRepository, times(1)).findByAddressCityCountryCountry(country);
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

		when(storeRepository.findByAddressCityCity(city)).thenReturn(stores);

		List<Store> retrievedStores = storeService.getStoresByCity(city);

		assertNotNull(retrievedStores);
		assertEquals(stores, retrievedStores);

		verify(storeRepository, times(1)).findByAddressCityCity(city);
	}

	@Test
	void testAssignAddressToStore() {
		int storeId = 1;
		Address address = new Address();

		Store store = new Store();
		store.setStoreId(storeId);

		when(storeRepository.findByStoreId(storeId)).thenReturn(store);

		storeService.assignAddressToStore(storeId, address);

		assertEquals(address, store.getAddress());

		verify(storeRepository, times(1)).findByStoreId(storeId);
		verify(addressRepository, times(1)).save(address);
	}

	@Test
	void testFetchAllAddress() {
		List<Address> addresses = new ArrayList<>();
		// Add addresses to the list
		Address address1 = new Address();
		// Set address fields
		addresses.add(address1);

		when(addressRepository.findAll()).thenReturn(addresses);

		List<Address> retrievedAddresses = storeService.fetchAllAddress();

		assertNotNull(retrievedAddresses);
		assertEquals(addresses, retrievedAddresses);

		verify(addressRepository, times(1)).findAll();
	}

}
