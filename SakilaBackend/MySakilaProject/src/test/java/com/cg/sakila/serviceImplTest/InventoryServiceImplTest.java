package com.cg.sakila.serviceImplTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.sakila.enitites.Film;
import com.cg.sakila.enitites.Inventory;
import com.cg.sakila.enitites.Store;
import com.cg.sakila.repositories.InventoryRepository;
import com.cg.sakila.service.InventoryServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InventoryServiceImplTest {

	@Mock
	private InventoryRepository inventoryRepository;

	@InjectMocks
	private InventoryServiceImpl inventoryService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllInventoryTest() {
		// Prepare test data
		List<Inventory> expectedInventoryList = new ArrayList<>();
		// Add some inventories to the expected list
		Inventory inventory1 = new Inventory();
		inventory1.setInventoryId(1);
		inventory1.setFilm(new Film());
		inventory1.setStore(new Store());
		inventory1.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
		expectedInventoryList.add(inventory1);

		Inventory inventory2 = new Inventory();
		inventory2.setInventoryId(2);
		inventory2.setFilm(new Film());
		inventory2.setStore(new Store());
		inventory2.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
		expectedInventoryList.add(inventory2);

		// Mock the repository method
		when(inventoryRepository.findAll()).thenReturn(expectedInventoryList);

		// Call the service method
		List<Inventory> actualInventoryList = inventoryService.getAllInventory();

		// Verify the result
		assertEquals(expectedInventoryList, actualInventoryList);
		verify(inventoryRepository, times(1)).findAll();
	}

	@Test
	void getInventoryCountForFilmTest() {
		// Prepare test data
		Film film = new Film(); // Create a Film object
		film.setFilmId((int) 1); // Set the ID of the film
		film.setTitle("Film Title"); // Set the title of the film

		// Mock the repository method
		when(inventoryRepository.countByFilm(film)).thenReturn(5);

		// Call the service method
		int inventoryCount = inventoryService.getInventoryCountForFilm(film);

		// Verify the result
		assertEquals(5, inventoryCount);
		verify(inventoryRepository, times(1)).countByFilm(film);
	}

	// Additional test methods
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

		// Mock the repository method
		when(inventoryRepository.save(inventory)).thenReturn(inventory);

		// Call the service method
		inventoryService.addFilmToStore(inventory);

		// Verify the result
		verify(inventoryRepository, times(1)).save(inventory);
	}

	@Test
	void getInventoryByStoreIdTest() {
		// Prepare test data
		int storeId = 1;
		List<Inventory> inventoryList = new ArrayList<>();
		Inventory inventory1 = new Inventory();
		inventory1.setInventoryId(1);
		inventory1.setFilm(new Film());
		inventory1.setStore(new Store());
		inventory1.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
		inventoryList.add(inventory1);
		// Add more inventories to the list if needed

		// Create and set the film object
		Film film = new Film();
		film.setFilmId((int) 1); // Set the ID of the film

		// Create and set the store object
		Store store = new Store();
		store.setStoreId((int) 1); // Set the ID of the store

		// Mock the repository method
		when(inventoryRepository.findByStoreStoreId(storeId)).thenReturn(inventoryList);

		// Call the service method
		List<Map<String, Object>> result = inventoryService.getInventoryByStoreId(storeId);

		// Verify the result
		assertEquals(inventoryList.size(), result.size());
		verify(inventoryRepository, times(1)).findByStoreStoreId(storeId);
	}

	@Test
	void getInventoryCountByFilmIdWithStoreAddressTest() {
		// Prepare test data
		int filmId = 1;
		List<Map<String, Object>> inventoryCountList = new ArrayList<>();
		Map<String, Object> inventoryCount1 = new HashMap<>();
		inventoryCount1.put("filmTitle", "Film 1");
		inventoryCount1.put("totalCount", 5);
		inventoryCount1.put("storeAddress", "Address 1");
		inventoryCountList.add(inventoryCount1);
		// Add more inventory counts to the list if needed

		// Mock the repository method
		when(inventoryRepository.getInventoryCountByFilmIdWithStoreAddress(filmId)).thenReturn(inventoryCountList);

		// Call the service method
		List<Map<String, Object>> result = inventoryService.getInventoryCountByFilmIdWithStoreAddress(filmId);

		// Verify the result
		assertEquals(inventoryCountList, result);
		verify(inventoryRepository, times(1)).getInventoryCountByFilmIdWithStoreAddress(filmId);
	}

//    @Test
//    void getInventoryCountByFilmAndStoreTest() {
//        // Prepare test data
//        int filmId = 1;
//        int storeId = 1;
//        List<Object[]> inventoryCount = new ArrayList<>();
//        Object[] row1 = {"Address 1", 5};
//        inventoryCount.add(row1);
//        // Add more rows to the list if needed
//
//        // Mock the repository method
//        when(inventoryRepository.findInventoryCountByFilmAndStore(filmId, storeId)).thenReturn(inventoryCount);
//
//        // Call the service method
//        List<Map<String, Object>> result = inventoryService.getInventoryCountByFilmAndStore(filmId, storeId);
//
//        // Verify the result
//        assertEquals(inventoryCount.size(), result.size());
//        verify(inventoryRepository, times(1)).findInventoryCountByFilmAndStore(filmId, storeId);
//    }

}
