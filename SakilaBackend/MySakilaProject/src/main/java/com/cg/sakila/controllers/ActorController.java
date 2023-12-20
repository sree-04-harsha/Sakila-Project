package com.cg.sakila.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sakila.enitites.Actor;
import com.cg.sakila.enitites.Film;
import com.cg.sakila.service.ActorService;
import com.cg.sakila.service.FilmActorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/actor")
@CrossOrigin(origins = "http://localhost:4200")
public class ActorController {

	private final ActorService actorService;

	private final FilmActorService filmActorService;

	@Autowired
	public ActorController(ActorService actorService, FilmActorService filmActorService) {
		this.actorService = actorService;
		this.filmActorService = filmActorService;
	}

	@PostMapping("/actors/post")
	public ResponseEntity<String> addActor(@Valid @RequestBody Actor actor) {
		actorService.addActor(actor);
		return new ResponseEntity<>("Record Created Successfully", HttpStatus.CREATED);
	}

	// --------------------get-------------------------------------------------

	@GetMapping("/actors")
	public ResponseEntity<List<Actor>> getAllActors() {
		List<Actor> actors = (List<Actor>) actorService.findAllActors();
		return new ResponseEntity<>(actors, HttpStatus.OK);
	}

	@GetMapping("/actors/toptenbyfilmcount")
	public ResponseEntity<List<Actor>> getTopTenActorsByFilmCount() {
		List<Actor> actors = actorService.getTopTenActorsByFilmCount();
		return new ResponseEntity<>(actors, HttpStatus.OK);
	}

	@GetMapping("/actors/lastname/{ln}")
	public ResponseEntity<List<Actor>> searchActorsByLastName(@PathVariable String ln) {
		List<Actor> actors = (List<Actor>) actorService.findActorsByLastName(ln);
		return new ResponseEntity<>(actors, HttpStatus.OK);
	}

	@GetMapping("/actors/firstname/{fn}")
	public ResponseEntity<List<Actor>> searchActorsByFirstName(@PathVariable String fn) {
		List<Actor> actors = (List<Actor>) actorService.findActorsByFirstName(fn);
		return new ResponseEntity<>(actors, HttpStatus.OK);
	}

	// with exception
	@GetMapping("/actors/{id}/films")
	public ResponseEntity<List<Film>> getFilmsByActorId(@PathVariable("id") int actorId) {
		List<Film> films = filmActorService.getFilmsByActorId(actorId);
		return ResponseEntity.ok(films);
	}

	// --------------------put-------------------------------------------------

	// with exception
	@PutMapping("/actors/update/lastname/{id}")
	public ResponseEntity<String> updateActorLastName(@Validated @PathVariable("id") int id,
			@RequestBody Map<String, String> requestBody) {
		String newLastName = requestBody.get("newLastName");
		actorService.updateActorLastName(id, newLastName);
		return ResponseEntity.ok("Last Name Updated Successfully");
	}

	@PutMapping("/actors/update/firstname/{id}")
	public ResponseEntity<String> updateActorFirstName(@PathVariable("id") int id,
			@RequestBody Map<String, String> requestBody) {
		String newFirstname = requestBody.get("newFirstname");
		actorService.updateActorFirstName(id, newFirstname);
		return ResponseEntity.status(HttpStatus.OK).body("First name Updated Successfully");
	}

	@PutMapping("/actors/{id}/film")
	public ResponseEntity<String> assignFilmToActor(@PathVariable("id") int actorId, @RequestBody Film film) {
		filmActorService.assignFilmToActor(actorId, film);
		return ResponseEntity.ok("Film successfully assigned to the actor.");
	}

}