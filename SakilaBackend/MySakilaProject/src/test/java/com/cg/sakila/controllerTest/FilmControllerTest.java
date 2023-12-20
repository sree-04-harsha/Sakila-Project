package com.cg.sakila.controllerTest;

import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.annotation.Validated;

import com.cg.sakila.controllers.*;
import com.cg.sakila.enitites.*;
import com.cg.sakila.service.*;

@Validated
@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(classes = FilmControllerTest.TestConfiguration.class)
class FilmControllerTest {

	@InjectMocks
	private FilmController filmController;

	@Mock
	private FilmService filmService;

	@Test
	void testAddFilm() {
		Film film = new Film();
		film.setTitle("Example Film");
		film.setReleaseYear(2022);
		when(filmService.createFilm(any(Film.class))).thenReturn(film);

		ResponseEntity<String> response = filmController.addFilm(film);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Record Created Successfully", response.getBody());

		verify(filmService, times(1)).createFilm(any(Film.class));
	}

	@Test
	void testSearchFilmsByTitle() {
		Film film = new Film();
		film.setTitle("Example Film");
		film.setReleaseYear(2022);
		List<Film> films = Collections.singletonList(film);
		when(filmService.searchFilmsByTitle("Example Film")).thenReturn(films);

		ResponseEntity<List<Film>> response = filmController.searchFilmsByTitle("Example Film");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(films, response.getBody());

		verify(filmService, times(1)).searchFilmsByTitle("Example Film");
	}

	@Test
	void testUpdateFilmRentalRate() {
		int filmId = 1;
		Double newRentalRate = 9.99;

		Film updatedFilm = new Film();
		updatedFilm.setRentalRate(newRentalRate);

		when(filmService.updateFilmRentalRate(filmId, newRentalRate)).thenReturn(updatedFilm);

		ResponseEntity<Film> response = filmController.updateFilmRentalRate(filmId, newRentalRate);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedFilm, response.getBody());

		verify(filmService, times(1)).updateFilmRentalRate(filmId, newRentalRate);
	}

	@Test
	void testUpdateFilmRating() {
		int filmId = 1;
		String newRating = "PG";

		Film updatedFilm = new Film();
		updatedFilm.setRating(newRating);

		when(filmService.updateFilmRating(filmId, newRating)).thenReturn(updatedFilm);

		ResponseEntity<Film> response = filmController.updateFilmRating(filmId, newRating);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedFilm, response.getBody());

		verify(filmService, times(1)).updateFilmRating(filmId, newRating);
	}

	@Test
	void testUpdateFilmLanguage() {
		int filmId = 1;
		Language newLanguage = new Language(2, "English", new Timestamp(System.currentTimeMillis()));

		Film updatedFilm = new Film();
		updatedFilm.setLanguage(newLanguage);

		when(filmService.updateFilmLanguage(filmId, newLanguage.getName())).thenReturn(updatedFilm);

		ResponseEntity<Film> response = filmController.updateFilmLanguage(filmId, newLanguage.getName());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedFilm, response.getBody());

		verify(filmService, times(1)).updateFilmLanguage(filmId, newLanguage.getName());
	}

	@Configuration
	static class TestConfiguration {

	}

}
