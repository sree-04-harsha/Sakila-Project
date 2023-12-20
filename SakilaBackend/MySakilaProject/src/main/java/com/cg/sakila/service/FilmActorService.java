package com.cg.sakila.service;

import java.util.List;

import com.cg.sakila.enitites.Actor;
import com.cg.sakila.enitites.Film;

public interface FilmActorService {

	// Find all Actors of a Film by Film id
	List<Actor> findAllActorsByFilmId(int filmId);

	// find all films by actor id
	List<Film> getFilmsByActorId(int actorId);

	// Assign Actor to a Film
	void assignActorToFilm(int filmId, int actorId);

	void assignFilmToActor(int actorId, Film film);

}
