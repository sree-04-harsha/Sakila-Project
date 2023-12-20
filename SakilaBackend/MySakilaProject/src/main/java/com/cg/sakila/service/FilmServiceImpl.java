package com.cg.sakila.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.enitites.Film;
import com.cg.sakila.enitites.Language;
import com.cg.sakila.exception.CustomException;
import com.cg.sakila.repositories.FilmRepository;
import com.cg.sakila.repositories.LanguageRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

	private final FilmRepository filmRepository;

	private final LanguageRepository languageRepository;

	@Autowired
	public FilmServiceImpl(FilmRepository filmRepository, LanguageRepository languageRepository) {
		this.filmRepository = filmRepository;
		this.languageRepository = languageRepository;
	}

	@Override
	public List<Film> getAllFilms() throws CustomException {
		List<Film> list = filmRepository.findAll();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("fIlms are not found.. ");
		}
	}

	@Override
	public List<Film> searchFilmsByTitle(String title)throws CustomException {
		List<Film> list = filmRepository.findByTitleContaining(title);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("films by title are not found.. ");
		}
	}

	@Override
	public List<Film> searchFilmsByReleaseYear(int year)throws CustomException {
		List<Film> list = filmRepository.findByReleaseYear(year);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("fIlms by release year are not found.. ");
		}
	}

	@Override
	public List<Film> findFilmsByRentalDurationGreaterThan(int rentalDuration)throws CustomException {
		List<Film> list = filmRepository.findFilmsByRentalDurationGreaterThan(rentalDuration);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Films By Rental Duration Greater Than rental duration are not found.. ");
		}
	}

	@Override
	public List<Film> findFilmsByRentalRateGreaterThan(double rentalRate) throws CustomException{
		List<Film> list = filmRepository.findFilmsByRentalRateGreaterThan(rentalRate);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Films By Rental rate Greater Than rental rate are not found.. ");
		}
	}

	@Override
	public List<Film> findFilmsByLengthGreaterThan(int length)throws CustomException {
		List<Film> list = filmRepository.findFilmsByLengthGreaterThan(length);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Films By length Greater Than length are not found.. ");
		}
	}

	@Override
	public List<Film> findFilmsByRentalDurationLessThan(int rentalDuration)throws CustomException {
		List<Film> list = filmRepository.findFilmsByRentalDurationLessThan(rentalDuration);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Films By Rental Duration less Than rental duration are not found.. ");
		}
	}

	@Override
	public List<Film> findFilmsByRentalRateLessThan(double rentalRate)throws CustomException {
		List<Film> list = filmRepository.findFilmsByRentalRateLessThan(rentalRate);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Films By Rental rate less Than rental rate are not found.. ");
		}
	}

	@Override
	public List<Film> findFilmsByLengthLessThan(int length) throws CustomException{
		List<Film> list = filmRepository.findFilmsByLengthLessThan(length);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Films By length less Than length are not found.. ");
		}
	}

	@Override
	public List<Film> getFilmsReleasedBetweenYears(int fromYear, int toYear)throws CustomException {
		List<Film> list = filmRepository.findFilmsReleasedBetweenYears(fromYear, toYear);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Films Released Between Years are not found.. ");
		}
	}

	@Override
	public List<Film> getFilmsByRatingLessThan(String rating)throws CustomException {
		List<Film> list = filmRepository.findFilmsByRatingLessThan(rating);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Films By Rating Less Than are rating not found.. ");
		}
	}

	@Override
	public List<Film> getFilmsByRatingGreaterThan(String rating)throws CustomException {
		List<Film> list = filmRepository.findFilmsByRatingGreaterThan(rating);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Films By Rating greater Than rating are not found.. ");
		}
	}

	@Override
	public List<Film> getFilmsByLanguage(String language)throws CustomException {
		List<Film> list = filmRepository.findByLanguageName(language);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Films By language are not found.. ");
		}
	}

//********************************************************************************************************* 

	@Override
	public Film createFilm(Film film) {
		return filmRepository.save(film);
	}

	// Update Title of a Film
	@Override
	public Film updateFilmTitle(int filmId, String title) throws CustomException{
		Optional<Film> optFilm = filmRepository.findById(filmId);
		if(optFilm.isEmpty()) {
			throw new CustomException("films are not found.. with id: "+filmId);
		}
		optFilm.get().setTitle(title);
		return filmRepository.save(optFilm.get());
	}

	// Update Release Year of a Film
	@Override
	public Film updateFilmReleaseYear(int id, Integer newReleaseYear)throws CustomException {
		Film film = filmRepository.findById(id)
				.orElseThrow(() -> new CustomException("No film found with the ID: " + id));
		film.setReleaseYear(newReleaseYear);
		return filmRepository.save(film);
	}

	// Update Rental Duration of a Film
	@Override
	public Film updateFilmRentalDuration(int id, int newRentalDuration)throws CustomException {
		Film film = filmRepository.findById(id)
				.orElseThrow(() -> new CustomException("No film found with the ID: " + id));
		film.setRentalDuration(newRentalDuration);
		return filmRepository.save(film);
	}

	// Update Rental Rate of a Film
	@Override
	public Film updateFilmRentalRate(int id, double newRentalRate) throws CustomException{
		Optional<Film> optFilm = filmRepository.findById(id);
		if(optFilm.isEmpty()) {
			throw new CustomException("films are not found.. with id: "+id);
		}
		optFilm.get().setRentalRate(newRentalRate);
		return filmRepository.save(optFilm.get());
	}

	// Update Rating of a Film
	@Override
	public Film updateFilmRating(int id, String newRating)throws CustomException {
		Film film = filmRepository.findById(id)
				.orElseThrow(() -> new CustomException("No film found with the ID: " + id));
		film.setRating(newRating);
		return filmRepository.save(film);
	}

	// Update Language of a Film
	@Override
	public Film updateFilmLanguage(int filmId, String languageName)throws CustomException {
		Film film = filmRepository.findById(filmId)
				.orElseThrow(() -> new IllegalArgumentException("Film not found with id: " + filmId));
		Language language = languageRepository.findByName(languageName);
		if (language == null) {
			throw new IllegalArgumentException("Language not found with name: " + languageName);
		}
		film.setLanguage(language);
		return filmRepository.save(film);
	}

	// count film by year
	
	@Override
	public Map<Integer, Long> getCountOfFilmsByYear() {
		return filmRepository.findAll().stream().collect(Collectors.groupingBy(Film::getReleaseYear, Collectors.counting()));
	}

	@Override
	public Film getFilmById(int filmId) {
		Optional<Film> optionalFilm = filmRepository.findById(filmId);
		if(optionalFilm.isEmpty()) {
			throw new CustomException("film Id not present");
		}
		return optionalFilm.get();
	}
	
	@Override
    public List<Film> getFilmByRating(String rating) {
        return filmRepository.findFilmsByRating(rating);
    }

}
