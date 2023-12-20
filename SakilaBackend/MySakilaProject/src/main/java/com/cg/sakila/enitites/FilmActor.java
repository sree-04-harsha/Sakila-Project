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
@Table(name = "film_actor")
public class FilmActor {
	@EmbeddedId
	private FilmActorId id;

	@Column(name = "last_update", nullable = false)
	private Timestamp lastUpdate;

	@ManyToOne
	@MapsId("actorId")
	@JoinColumn(name = "actor_id")
	private Actor actor;

	@ManyToOne
	@MapsId("filmId")
	@JoinColumn(name = "film_id")
	private Film film;

	public FilmActor(FilmActorId id, Timestamp lastUpdate, Actor actor, Film film) {
		super();
		this.id = id;
		this.lastUpdate = lastUpdate;
		this.actor = actor;
		this.film = film;
	}

	public FilmActor() {
		super();
	}

	public FilmActorId getId() {
		return id;
	}

	public void setId(FilmActorId id) {
		this.id = id;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	@Override
	public String toString() {
		return "FilmActor [lastUpdate=" + lastUpdate + ", actor=" + actor + ", film=" + film + "]";
	}

}
