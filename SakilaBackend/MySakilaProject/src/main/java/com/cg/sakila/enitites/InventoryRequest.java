package com.cg.sakila.enitites;

import jakarta.validation.constraints.NotNull;

public class InventoryRequest {

	@NotNull(message = "Store ID is required")
	private int storeId;

	@NotNull(message = "Film is required")
	private Film film;

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
}
