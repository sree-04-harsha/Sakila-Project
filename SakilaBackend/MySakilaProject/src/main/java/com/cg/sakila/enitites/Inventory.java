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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "inventory")
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventory_id")
	private int inventoryId;

	@NotNull(message = "Film is required")
	@ManyToOne
	@JoinColumn(name = "film_id")
	private Film film;

	@NotNull(message = "Store is required")
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	@Column(name = "last_update", nullable = false)
	private Timestamp lastUpdate;

	public Inventory(int inventoryId, Film film, Store store, Timestamp lastUpdate) {
		super();
		this.inventoryId = inventoryId;
		this.film = film;
		this.store = store;
		this.lastUpdate = lastUpdate;
	}

	public Inventory() {
		super();
	}

	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Inventory [inventoryId=" + inventoryId + ", film=" + film + ", store=" + store + ", lastUpdate="
				+ lastUpdate + "]";
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

}
