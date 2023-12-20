package com.cg.sakila.service;

import java.util.Collection;
import java.util.List;

import com.cg.sakila.enitites.Actor;

public interface ActorService {
	
	Collection<Actor> findAllActors();

    Actor addActor(Actor actor);

    Collection<Actor> findActorsByLastName(String lastName);

    Collection<Actor> findActorsByFirstName(String firstName);

    Actor updateActorLastName(int id, String lastName);

	Actor updateActorFirstName(int id, String firstName);
    
    List<Actor> getTopTenActorsByFilmCount();

}
