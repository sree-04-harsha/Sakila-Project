package com.cg.sakila.enitites;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "language")
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_sequence")
	@SequenceGenerator(name = "language_sequence", sequenceName = "language_sequence", allocationSize = 1)
	@Column(name = "language_id", updatable = false)
	@PositiveOrZero(message = "Language ID must be a positive number or zero")
	private int languageId;

	@NotBlank(message = "Name is required")
	@Size(max = 255, message = "Name must be less than or equal to 255 characters")
	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "language")
	private Set<Film> films;

	@OneToMany(mappedBy = "originalLanguage")
	private Set<Film> originalLanguageFilms;

	@NotNull(message = "Last update is required")
	@Column(name = "last_update", nullable = false)
	private Timestamp lastUpdate;

	public Language(int languageId, String name, Timestamp lastUpdate) {
		super();
		this.languageId = languageId;
		this.name = name;
		this.lastUpdate = lastUpdate;
	}

	public Language() {
		super();
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Film> getFilms() {
		return films;
	}

	public void setFilms(Set<Film> films) {
		this.films = films;
	}

	public Set<Film> getOriginalLanguageFilms() {
		return originalLanguageFilms;
	}

	public void setOriginalLanguageFilms(Set<Film> originalLanguageFilms) {
		this.originalLanguageFilms = originalLanguageFilms;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Language [languageId=" + languageId + ", name=" + name + ", films=" + films + ", originalLanguageFilms="
				+ originalLanguageFilms + ", lastUpdate=" + lastUpdate + "]";
	}

	// Getters and setters
}
