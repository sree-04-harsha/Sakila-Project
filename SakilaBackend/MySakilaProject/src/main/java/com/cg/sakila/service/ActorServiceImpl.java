package com.cg.sakila.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.enitites.Actor;
import com.cg.sakila.exception.CustomException;
import com.cg.sakila.repositories.ActorRepository;

@Service
public class ActorServiceImpl implements ActorService {

	private final ActorRepository actorRepository;

	@Autowired
	public ActorServiceImpl(ActorRepository actorRepository) {
		super();
		this.actorRepository = actorRepository;
	}

	@Override
	public List<Actor> findAllActors()throws CustomException{
		List<Actor> list = actorRepository.findAll();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Actors are not found.. ");
		}
	}

	@Override
	public Actor addActor(Actor actor) {
	    try {
	        return actorRepository.save(actor);
	    } catch (Exception e) {
	        // CustomerException can be a custom exception class specific to your application
	        throw new CustomException("An error occurred while adding the actor.");
	    }
	}


	@Override
	public List<Actor> findActorsByLastName(String lastName)  throws CustomException {
		List<Actor> list = actorRepository.findByLastName(lastName);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Actor not found.. ");
		}
	}

	@Override
	public List<Actor> findActorsByFirstName(String firstName) throws CustomException{
		List<Actor> list = actorRepository.findByFirstName(firstName);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Actor not found.. ");
		}
	}

	@Override
	public Actor updateActorLastName(int id, String lastName)throws CustomException {
		Actor actor = actorRepository.findById(id).orElse(null);
		if (actor != null) {
			actor.setLastName(lastName);
			return actorRepository.save(actor);
		}
		throw new CustomException("Actor not found..");
	}

	@Override
	public Actor updateActorFirstName(int id, String firstName)throws CustomException {
		Actor actor = actorRepository.findById(id).orElse(null);
		if (actor != null) {
			actor.setFirstName(firstName);
			return actorRepository.save(actor);
		}
		throw new CustomException("Actor not found..");
	}

	@Override
	public List<Actor> getTopTenActorsByFilmCount()throws CustomException {
		List<Actor> list = actorRepository.findTopTenActorsByFilmCount();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Actor not found.. ");
		}
	}

}
