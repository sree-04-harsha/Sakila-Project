package com.cg.sakila.enitites;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "film_category")
public class FilmCategory {
	@EmbeddedId
	private FilmCategoryId id;

	@Column(name = "last_update", nullable = false)
	private Timestamp lastUpdate;

	@ManyToOne
	@MapsId("filmId")
	@JoinColumn(name = "film_id")
	private Film film;

	@ManyToOne
	@MapsId("categoryId")
	@JoinColumn(name = "category_id")
	private Category category;

	public FilmCategory(FilmCategoryId id, Timestamp lastUpdate, Film film, Category category) {
		super();
		this.id = id;
		this.lastUpdate = lastUpdate;
		this.film = film;
		this.category = category;
	}

	public FilmCategory() {
		super();
	}

	public FilmCategoryId getId() {
		return id;
	}

	public void setId(FilmCategoryId id) {
		this.id = id;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "FilmCategory [lastUpdate=" + lastUpdate + ", film=" + film + ", category=" + category + "]";
	}

}
