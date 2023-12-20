package com.cg.sakila.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.enitites.Actor;
import com.cg.sakila.enitites.Film;
import com.cg.sakila.enitites.FilmActor;
import com.cg.sakila.enitites.FilmActorId;
import com.cg.sakila.exception.CustomException;
import com.cg.sakila.repositories.ActorRepository;
import com.cg.sakila.repositories.FilmActorRepository;
import com.cg.sakila.repositories.FilmRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class FilmActorServiceImpl implements FilmActorService {

	private final FilmActorRepository filmActorRepository;

	private final ActorRepository actorRepository;

	private final FilmRepository filmRepository;

	@Autowired
	public FilmActorServiceImpl(FilmActorRepository filmActorRepository, ActorRepository actorRepository,
			FilmRepository filmRepository) {
		super();
		this.filmActorRepository = filmActorRepository;
		this.actorRepository = actorRepository;
		this.filmRepository = filmRepository;
	}

	// Find all Actors of a Film by Film id
	@Override
	public List<Actor> findAllActorsByFilmId(int filmId) throws CustomException {
		List<FilmActor> filmActors = filmActorRepository.findAllByIdFilmId(filmId);
		List<Actor> list = filmActors.stream().map(FilmActor::getActor).toList();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Find all actors bt filmId are not found.. ");
		}
	}

	@Override
	public List<Film> getFilmsByActorId(int actorId) throws CustomException {
		List<FilmActor> filmActors = filmActorRepository.findByActorId(actorId);
		List<Film> list = filmActors.stream().map(FilmActor::getFilm).toList();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Films by actorId are not found.. ");
		}
	}

	@Override
	public void assignActorToFilm(int filmId, int actorId) throws CustomException {
		Optional<Film> optionalFilm = filmRepository.findById(filmId);
		Optional<Actor> optionalActor = actorRepository.findById(actorId);
		if (optionalFilm.isPresent() && optionalActor.isPresent()) {
			Film film = optionalFilm.get();
			Actor actor = optionalActor.get();

			FilmActorId filmActorId = new FilmActorId();
			filmActorId.setFilmId(filmId);
			filmActorId.setActorId(actorId);

			FilmActor filmActor = new FilmActor();
			filmActor.setId(filmActorId);
			filmActor.setFilm(film);
			filmActor.setActor(actor);
			filmActor.setLastUpdate(new Timestamp(System.currentTimeMillis()));

			filmActorRepository.save(filmActor);
		} else {
			throw new CustomException("Film not found for ID: " + filmId);
		}
	}

	//// ************************Remaining***********************************/////

	// Assign Film to a Actor

	@Override
	public void assignFilmToActor(int actorId, Film film) throws CustomException {
		Optional<Actor> findById = actorRepository.findById(actorId);
		if (findById.isPresent()) {
			Actor actor = findById.get();
			FilmActorId filmActorId = new FilmActorId();
			filmActorId.setActorId(actorId);
			filmActorId.setFilmId(film.getFilmId());

			FilmActor filmActor = new FilmActor();
			filmActor.setId(filmActorId);
			filmActor.setActor(actor);
			filmActor.setFilm(film);
			filmActor.setLastUpdate(new Timestamp(System.currentTimeMillis()));

			filmActorRepository.save(filmActor);
		} else {
			throw new CustomException("Actor not found for ID: " + actorId);
		}
	}

}