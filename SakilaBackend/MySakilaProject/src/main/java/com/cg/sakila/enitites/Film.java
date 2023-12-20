package com.cg.sakila.enitites;

import java.sql.Timestamp;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "film")
public class Film {
	
	@Id
	@Column(name = "film_id")
	private int filmId;

	@NotBlank(message = "Title is required")
	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "release_year")
	private int releaseYear;

	@NotNull(message = "Language is required")
	@ManyToOne
	@JoinColumn(name = "language_id", nullable = false)
	@JsonIgnoreProperties("films")
	private Language language;

	@ManyToOne
	@JoinColumn(name = "original_language_id")
	private Language originalLanguage;

	@Positive(message = "Negitive values are not accepting")
	@Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "Positive numbers only")
	@Column(name = "rental_duration", nullable = false)
	private int rentalDuration;

	@Column(name = "rental_rate", nullable = false)
	private double rentalRate;

	@Positive(message = "Length must be a positive number or zero")
	@Column(name = "length")
	private int length;

	@Column(name = "replacement_cost", nullable = false)
	private double replacementCost;
	
	@Column(name = "rating", nullable = false)
	private String rating;

	@Column(name = "special_features", columnDefinition = "TEXT")
	private String specialFeatures; // Use TEXT for longer string values

	@Column(name = "last_update", nullable = false)
	private Timestamp lastUpdate;

	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "film_actor", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
	private Set<Actor> actors;

	@ManyToMany
	@JoinTable(name = "film_category", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories;

	public Film() {
		super();
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Language getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(Language originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

}
