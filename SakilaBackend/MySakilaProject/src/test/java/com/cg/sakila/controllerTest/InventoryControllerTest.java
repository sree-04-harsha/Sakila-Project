package com.cg.sakila.controllerTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.sakila.controllers.InventoryController;
import com.cg.sakila.enitites.Film;
import com.cg.sakila.enitites.Inventory;
import com.cg.sakila.enitites.Store;
import com.cg.sakila.service.InventoryService;

class InventoryControllerTest {

	@Mock
	private InventoryService inventoryService;

	@InjectMocks
	private InventoryController inventoryController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllInventoryOfFilms() {
		// Prepare test data
		List<Inventory> inventoryList = new ArrayList<>();

		// Create and add inventory1
		Inventory inventory1 = new Inventory();
		inventory1.setInventoryId(1);
		inventory1.setFilm(new Film()); // Set the film object according to your domain model
		inventory1.setStore(new Store()); // Set the store object according to your domain model
		inventory1.setLastUpdate(Timestamp.valueOf(LocalDateTime.now())); // Set the last update timestamp
		inventoryList.add(inventory1);

		// Create and add inventory2
		Inventory inventory2 = new Inventory();
		inventory2.setInventoryId(2);
		inventory2.setFilm(new Film()); // Set the film object according to your domain model
		inventory2.setStore(new Store()); // Set the store object according to your domain model
		inventory2.setLastUpdate(Timestamp.valueOf(LocalDateTime.now())); // Set the last update timestamp
		inventoryList.add(inventory2);

		// Create and set the film object
		Film film = new Film();
		film.setFilmId((int) 1); // Set the ID of the film

		// Create and set the store object
		Store store = new Store();
		store.setStoreId((int) 1); // Set the ID of the store

		// Mock the service method
		Mockito.when(inventoryService.getAllInventory()).thenReturn(inventoryList);
		Mockito.when(inventoryService.getInventoryCountForFilm(Mockito.any(Film.class))).thenReturn(2);

		// Call the controller method
		List<Map<String, Object>> response = inventoryController.getAllInventoryOfFilms();

		// Verify the result
		assertNotNull(response);
		assertEquals(2, response.size());
		// Assert other expected values in the response data
	}

	@Test
	void getInventoryByStoreIdTest() {
		// Prepare test data
		int storeId = 1;
		List<Map<String, Object>> inventoryData = new ArrayList<>();
		// Add inventory data to the list if needed
		Inventory inventory = new Inventory();
		inventory.setInventoryId(1);
		inventory.setFilm(new Film());
		inventory.setStore(new Store());
		inventory.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

		// Create and set the film object
		Film film = new Film();
		film.setFilmId((int) 1); // Set the ID of the film

		// Create and set the store object
		Store store = new Store();
		store.setStoreId((int) 1); // Set the ID of the store

		// Mock the service method
		when(inventoryService.getInventoryByStoreId(storeId)).thenReturn(inventoryData);

		// Call the controller method
		ResponseEntity<List<Map<String, Object>>> responseEntity = inventoryController.getInventoryByStoreId(storeId);

		// Verify the response
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(inventoryData, responseEntity.getBody());
		verify(inventoryService, times(1)).getInventoryByStoreId(storeId);
	}

	@Test
	void getInventoryCountByFilmIdWithStoreAddressTest() {
		// Prepare test data
		int filmId = 1;
		List<Map<String, Object>> inventoryData = new ArrayList<>();
		// Add inventory data to the list if needed
		Inventory inventory = new Inventory();
		inventory.setInventoryId(1);
		inventory.setFilm(new Film());
		inventory.setStore(new Store());
		inventory.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

		// Create and set the film object
		Film film = new Film();
		film.setFilmId((int) 1); // Set the ID of the film

		// Create and set the store object
		Store store = new Store();
		store.setStoreId((int) 1); // Set the ID of the store

		// Mock the service method
		when(inventoryService.getInventoryCountByFilmIdWithStoreAddress(filmId)).thenReturn(inventoryData);

		// Call the controller method
		ResponseEntity<List<Map<String, Object>>> responseEntity = inventoryController
				.getInventoryCountByFilmIdWithStoreAddress(filmId);

		// Verify the response
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(inventoryData, responseEntity.getBody());
		verify(inventoryService, times(1)).getInventoryCountByFilmIdWithStoreAddress(filmId);
	}

	@Test
	void addFilmToStoreTest() {
		// Prepare test data
		Inventory inventory = new Inventory();
		inventory.setInventoryId(1);
		inventory.setFilm(new Film());
		inventory.setStore(new Store());
		inventory.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

		// Create and set the film object
		Film film = new Film();
		film.setFilmId((int) 1); // Set the ID of the film

		// Create and set the store object
		Store store = new Store();
		store.setStoreId((int) 1); // Set the ID of the store

		// Mock the service method
		doNothing().when(inventoryService).addFilmToStore(inventory);

		// Call the controller method
		ResponseEntity<String> responseEntity = inventoryController.addFilmToStore(inventory);

		// Verify the response
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Record Created Successfully", responseEntity.getBody());
		verify(inventoryService, times(1)).addFilmToStore(inventory);
	}
}
