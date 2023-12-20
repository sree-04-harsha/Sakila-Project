package com.cg.sakila.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.sakila.enitites.FilmActor;
import com.cg.sakila.enitites.FilmActorId;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {
	
	//Find all Actors of a Film by Film id
    List<FilmActor> findAllByIdFilmId(int filmId);
    
	//Find all Films by actor id
    @Query("SELECT fa FROM FilmActor fa JOIN fa.actor a WHERE a.id = :actorId")
	List<FilmActor> findByActorId(@Param("actorId") int actorId);
    
    
}
