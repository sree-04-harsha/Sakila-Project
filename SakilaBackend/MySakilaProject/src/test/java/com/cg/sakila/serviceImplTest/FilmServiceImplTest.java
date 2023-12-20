package com.cg.sakila.serviceImplTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.Configuration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cg.sakila.enitites.*;
import com.cg.sakila.repositories.FilmRepository;
import com.cg.sakila.service.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(classes = FilmServiceImplTest.TestConfiguration.class)
class FilmServiceImplTest {

	@InjectMocks
	private FilmServiceImpl filmService;

	@Mock
	private FilmRepository filmRepository;

	@Test
	void testCreateFilm() {
		Film film = new Film();
		film.setTitle("Example Film");
		film.setReleaseYear(2022);

		when(filmRepository.save(any(Film.class))).thenReturn(film);

		Film createdFilm = filmService.createFilm(film);

		assertNotNull(createdFilm);
		assertEquals("Example Film", createdFilm.getTitle());
		assertEquals(2022, createdFilm.getReleaseYear());

		verify(filmRepository, times(1)).save(film);
	}

	@Test
	void testSearchFilmsByTitle() {
		Film film = new Film();
		film.setTitle("Example Film");
		film.setReleaseYear(2022);
		List<Film> films = Collections.singletonList(film);

		when(filmRepository.findByTitleContaining("Example Film")).thenReturn(films);

		List<Film> result = filmService.searchFilmsByTitle("Example Film");

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Example Film", result.get(0).getTitle());
		assertEquals(2022, result.get(0).getReleaseYear());

		verify(filmRepository, times(1)).findByTitleContaining("Example Film");
	}

	@Test
	void testSearchFilmsByReleaseYear() {
		Film film = new Film();
		film.setTitle("Example Film");
		film.setReleaseYear(2022);
		List<Film> films = Collections.singletonList(film);

		when(filmRepository.findByReleaseYear(2022)).thenReturn(films);

		List<Film> result = filmService.searchFilmsByReleaseYear(2022);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Example Film", result.get(0).getTitle());
		assertEquals(2022, result.get(0).getReleaseYear());

		verify(filmRepository, times(1)).findByReleaseYear(2022);
	}

	@Test
	void testUpdateFilmRentalRate() {
		int filmId = 1;
		Double newRentalRate = 9.99;
		Film film = new Film();
		when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));
		when(filmRepository.save(film)).thenReturn(film);

		assertDoesNotThrow(() -> filmService.updateFilmRentalRate(filmId, newRentalRate));

		assertEquals(newRentalRate, film.getRentalRate());
		verify(filmRepository, times(1)).findById(filmId);
		verify(filmRepository, times(1)).save(film);
	}

	@Test
	void testUpdateFilmRating() {
		int filmId = 1;
		String newRating = "PG";
		Film film = new Film();
		when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));
		when(filmRepository.save(film)).thenReturn(film);

		assertDoesNotThrow(() -> filmService.updateFilmRating(filmId, newRating));

		assertEquals(newRating, film.getRating());
		verify(filmRepository, times(1)).findById(filmId);
		verify(filmRepository, times(1)).save(film);
	}

	@Configuration
	static class TestConfiguration {

	}
}
