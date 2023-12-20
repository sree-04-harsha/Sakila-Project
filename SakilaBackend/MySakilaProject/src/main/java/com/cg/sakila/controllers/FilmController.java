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
import com.cg.sakila.enitites.Category;
import com.cg.sakila.enitites.Film;
import com.cg.sakila.service.CategoryService;
import com.cg.sakila.service.FilmActorService;
import com.cg.sakila.service.FilmCategoryService;
import com.cg.sakila.service.FilmService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/films")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class FilmController {

	private final FilmService filmService;

	private final FilmActorService filmActorService;

	private final FilmCategoryService filmCategoryService;

	private final CategoryService categoryService;

	@Autowired
	public FilmController(FilmService filmService, FilmActorService filmActorService,
			FilmCategoryService filmCategoryService, CategoryService categoryService) {
		super();
		this.filmService = filmService;
		this.filmActorService = filmActorService;
		this.filmCategoryService = filmCategoryService;
		this.categoryService = categoryService;
	}

	@PostMapping("/post")
	public ResponseEntity<String> addFilm(@Valid @RequestBody Film film) {
		filmService.createFilm(film);
		return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
	}

	@GetMapping("/films")
	public ResponseEntity<List<Film>> getAllFilms() {
		List<Film> films = filmService.getAllFilms();
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	@GetMapping("/title/{title}")
	public ResponseEntity<List<Film>> searchFilmsByTitle(@Valid @PathVariable String title) {
		List<Film> films = filmService.searchFilmsByTitle(title);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	@GetMapping("/year/{year}")
	public ResponseEntity<List<Film>> searchFilmsByReleaseYear(@Valid @PathVariable int year) {
		List<Film> films = filmService.searchFilmsByReleaseYear(year);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	@GetMapping("/duration/gt/{rd}")
	public ResponseEntity<List<Film>> findFilmsByRentalDurationGreaterThan(
			@Valid @PathVariable("rd") int rentalDuration) {
		return new ResponseEntity<>(filmService.findFilmsByRentalDurationGreaterThan(rentalDuration), HttpStatus.OK);
	}

	@GetMapping("/rate/gt/{rate}")
	public ResponseEntity<List<Film>> findFilmsByRentalRateGreaterThan(@Valid @PathVariable("rate") double rentalRate) {
		return new ResponseEntity<>(filmService.findFilmsByRentalRateGreaterThan(rentalRate), HttpStatus.OK);
	}

	@GetMapping("/length/gt/{length}")
	public ResponseEntity<List<Film>> findFilmsByLengthGreaterThan(@Valid @PathVariable("length") int length) {
		return new ResponseEntity<>(filmService.findFilmsByLengthGreaterThan(length), HttpStatus.OK);
	}

	@GetMapping("/duration/lt/{rentalDuration}")
	public ResponseEntity<List<Film>> findFilmsByRentalDurationLessThan(
			@Valid @PathVariable("rentalDuration") int rentalDuration) {
		return new ResponseEntity<>(filmService.findFilmsByRentalDurationLessThan(rentalDuration), HttpStatus.OK);
	}

	@GetMapping("/rate/lt/{rentalRate}")
	public ResponseEntity<List<Film>> findFilmsByRentalRateLessThan(
			@Valid @PathVariable("rentalRate") double rentalRate) {
		return new ResponseEntity<>(filmService.findFilmsByRentalRateLessThan(rentalRate), HttpStatus.OK);
	}

	@GetMapping("/length/lt/{length}")
	public ResponseEntity<List<Film>> findFilmsByLengthLessThan(@Valid @PathVariable("length") int length) {
		return new ResponseEntity<>(filmService.findFilmsByLengthLessThan(length), HttpStatus.OK);
	}

	@GetMapping("/betweenyear/{from}/{to}")
	public ResponseEntity<List<Film>> getFilmsReleasedBetweenYears(@Valid @PathVariable int from,
			@PathVariable int to) {
		return new ResponseEntity<>(filmService.getFilmsReleasedBetweenYears(from, to), HttpStatus.OK);
	}

	@GetMapping("/rating/lt/{rating}")
	public ResponseEntity<List<Film>> findFilmsByRatingLessThan(@Valid @PathVariable("rating") String rating) {
		return new ResponseEntity<>(filmService.getFilmsByRatingLessThan(rating), HttpStatus.OK);
	}

	@GetMapping("/rating/gt/{rating}")
	public ResponseEntity<List<Film>> getFilmsByRatingGreaterThan(@Valid @PathVariable String rating) {
		return new ResponseEntity<>(filmService.getFilmsByRatingGreaterThan(rating), HttpStatus.OK);
	}

	@GetMapping("/language/{lang}")
	public ResponseEntity<List<Film>> getFilmsByLanguage(@Valid @PathVariable("lang") String language) {
		return new ResponseEntity<>(filmService.getFilmsByLanguage(language), HttpStatus.OK);
	}

	// count film by year
	@GetMapping("/countbyyear")
	public ResponseEntity<Map<Integer, Long>> countFilmsByYear() {
		Map<Integer, Long> filmsByYear = filmService.getCountOfFilmsByYear();
		return ResponseEntity.ok(filmsByYear);
	}

	// Find all Actors of a Film by Film id
	@GetMapping("/{id}/actors")
	public ResponseEntity<List<Actor>> findAllActorsByFilmId(@Valid @PathVariable("id") int filmId) {
		return new ResponseEntity<>(filmActorService.findAllActorsByFilmId(filmId), HttpStatus.OK);
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<Film>> getFilmsByCategory(@Valid @PathVariable("category") String category) {
		return new ResponseEntity<>(filmCategoryService.getFilmsByCategory(category), HttpStatus.OK);
	}

	// Update Title of a Film
	@PutMapping("/update/title/{id}")
	public ResponseEntity<Film> updateFilmTitle(@Valid @PathVariable("id") int filmId, @RequestBody String title) {
		return ResponseEntity.status(HttpStatus.OK).body(filmService.updateFilmTitle(filmId, title));
	}

	// Update Release Year of a Film
	@PutMapping("/update/releaseyear/{id}")
	public ResponseEntity<Film> updateFilmReleaseYear(@Valid @PathVariable("id") int filmId,
			@RequestBody int releaseYear) {
		return ResponseEntity.status(HttpStatus.OK).body(filmService.updateFilmReleaseYear(filmId, releaseYear));
	}

	// Update Rental Duration of a Film
	@PutMapping("/update/rentalduration/{id}")
	public ResponseEntity<Film> updateFilmRentalDuration(@Valid @PathVariable("id") int filmId,
			 @RequestBody int rentalDuration) {
		return ResponseEntity.status(HttpStatus.OK).body(filmService.updateFilmRentalDuration(filmId, rentalDuration));
	}

	// Update Rental Rate of a Film
	@PutMapping("/update/rentalrate/{id}")
	public ResponseEntity<Film> updateFilmRentalRate(@Valid @PathVariable("id") int filmId,
			@RequestBody double rentalRate) {
		return ResponseEntity.status(HttpStatus.OK).body(filmService.updateFilmRentalRate(filmId, rentalRate));
	}

	// Update Rating of a Film
	@PutMapping("/update/rating/{id}")
	public ResponseEntity<Film> updateFilmRating(@Valid @PathVariable("id") int filmId, @RequestBody String rating) {
		return ResponseEntity.status(HttpStatus.OK).body(filmService.updateFilmRating(filmId, rating));
	}

	// Update Language of a Film
	@PutMapping("/update/language/{id}")
	public ResponseEntity<Film> updateFilmLanguage(@Valid @PathVariable("id") int filmId,
			@RequestBody String language) {
		return ResponseEntity.status(HttpStatus.OK).body(filmService.updateFilmLanguage(filmId, language));
	}

	// Assign Actor to a Film
	@PutMapping("/{id}/actor/{actorId}")
	public ResponseEntity<String> assignActorToFilm(@Valid @PathVariable("id") int filmId,
			@PathVariable("actorId") int actorId) {
		filmActorService.assignActorToFilm(filmId, actorId);
		return new ResponseEntity<>("Actor is assigned to Film", HttpStatus.ACCEPTED);
	}

	// Assign category to a Film
	@PutMapping("/update/film/{id}/category/{categoryid}")
	public ResponseEntity<String> assignCategoryToFilm(@Valid @PathVariable("id") int filmId,
			@PathVariable("categoryid") int categoryId) {
		filmCategoryService.assignCategoryToFilm(filmId, categoryId);
		return ResponseEntity.ok("Category successfully assigned to the film!");
	}

	// get all categories
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();
		if (categories.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/public/films/rating/{rating}")
	public ResponseEntity<List<Film>> filmByRating(@Valid @PathVariable String rating) {
		return new ResponseEntity<>(filmService.getFilmByRating(rating), HttpStatus.OK);
	}

}
