package com.cg.sakila.service;

import java.util.List;
import java.util.Map;

import com.cg.sakila.enitites.Film;

public interface FilmService {

	// Add new Film object in DB
	Film createFilm(Film film);

	List<Film> searchFilmsByTitle(String title);

	List<Film> searchFilmsByReleaseYear(int year);

	List<Film> findFilmsByRentalDurationGreaterThan(int rentalDuration);

	List<Film> findFilmsByRentalRateGreaterThan(double rentalRate);

	List<Film> findFilmsByLengthGreaterThan(int length);

	List<Film> findFilmsByRentalDurationLessThan(int rentalDuration);

	List<Film> findFilmsByRentalRateLessThan(double rentalRate);

	List<Film> findFilmsByLengthLessThan(int length);

	List<Film> getFilmsReleasedBetweenYears(int fromYear, int toYear);

	List<Film> getFilmsByRatingLessThan(String rating);

	List<Film> getFilmsByRatingGreaterThan(String rating);

	List<Film> getFilmsByLanguage(String language);

	// Display number of Films released by each Year

	// Update Title of a Film
	Film updateFilmTitle(int filmId, String title);

	// Update Release Year of a Film
	Film updateFilmReleaseYear(int id, Integer newReleaseYear);

	// Update Rental Duration of a Film
	Film updateFilmRentalDuration(int id, int newRentalDuration);

	// Update Rental Rate of a Film
	Film updateFilmRentalRate(int id, double newRentalRate);

	// Update Rating of a Film
	Film updateFilmRating(int id, String newRating);

	// Update Language of a Film
	Film updateFilmLanguage(int filmId, String languageName);

	// count film by year
	Map<Integer, Long> getCountOfFilmsByYear();

	// for inventory (Display inventory(count) of a Film in all Stores)
	Film getFilmById(int filmId);

	List<Film> getAllFilms();

	List<Film> getFilmByRating(String rating);

	//

}
