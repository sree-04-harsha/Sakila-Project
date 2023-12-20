package com.cg.sakila.enitites;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class FilmActorId implements Serializable {

	@NotNull(message = "Actor ID is required")
	@Column(name = "actor_id")
	private int actorId;

	@NotNull(message = "Film ID is required")
	@Column(name = "film_id")
	private int filmId;

	public FilmActorId(int actorId, int filmId) {
		super();
		this.actorId = actorId;
		this.filmId = filmId;
	}

	public FilmActorId() {
		super();
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int i) {
		this.actorId = i;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int i) {
		this.filmId = i;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actorId, filmId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		FilmActorId other = (FilmActorId) obj;
		return Objects.equals(actorId, other.actorId) && Objects.equals(filmId, other.filmId);
	}

	// Constructors, equals, and hashCode methods
}
