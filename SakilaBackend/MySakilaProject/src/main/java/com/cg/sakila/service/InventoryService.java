package com.cg.sakila.service;

import java.util.List;
import java.util.Map;

import com.cg.sakila.enitites.Film;
import com.cg.sakila.enitites.Inventory;

public interface InventoryService {

	void addFilmToStore(Inventory inventory);

	List<Inventory> getAllInventory();

	int getInventoryCountForFilm(Film film);

	List<Map<String, Object>> getInventoryByStoreId(int storeId);

	List<Map<String, Object>> getInventoryCountByFilmIdWithStoreAddress(int filmId);

	// *****************************************************************************
	List<Object[]> getInventoryCountByFilmAndStore(int filmId, int storeId);
	// *****************************************************************************

}
