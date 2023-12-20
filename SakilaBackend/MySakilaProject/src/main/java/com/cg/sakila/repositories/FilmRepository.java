package com.cg.sakila.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.sakila.enitites.Film;
import com.cg.sakila.enitites.Language;

public interface FilmRepository extends JpaRepository<Film, Integer> {
	
	//find by title
    List<Film> findByTitleContaining(String title);
    
    
    //find by release year
    List<Film> findByReleaseYear(int year);

    //Search Films where Rental Duration is greater 
    List<Film> findFilmsByRentalDurationGreaterThan(int rentalDuration);
    
    //Search Films where Rental Rate is greater 
    List<Film> findFilmsByRentalRateGreaterThan(double rentalRate);
    
    //Search Films where Length is greater
    List<Film> findFilmsByLengthGreaterThan(int length);
    
    //Search Films where Rental Duration is lower 
    List<Film> findFilmsByRentalDurationLessThan(int rentalDuration);
    
    //Search Films where Rental Rate is low
    List<Film> findFilmsByRentalRateLessThan(double rentalRate);
    
    //Search Films where Length is lower 
    List<Film> findFilmsByLengthLessThan(int length);
        
    //Search Films where Rating is lower 
    @Query("SELECT f FROM Film f WHERE ASCII(f.rating) < ASCII(:rating)")
    List<Film> findFilmsByRatingLessThan(String rating);
    
    //Search Films where Rating is greater 
    @Query("SELECT f FROM Film f WHERE ASCII(f.rating) > ASCII(:rating)")
    List<Film> findFilmsByRatingGreaterThan(String rating);
    
	//Find by language
    List<Film> findByLanguageName(String language);
	
	
	//Update Title of a Film
    @Modifying
	@Query("UPDATE Film f SET f.title = :newTitle WHERE f.id = :id")
    void updateFilmTitleById(int id, String newTitle);
    
    //Update Language of a Film
    @Modifying
    @Query("UPDATE Film f SET f.language = :language WHERE f.id = :filmId")
    void updateFilmLanguage(@Param("filmId") int filmId, @Param("language") Language language);
    
    //Count film by year
    @Query("SELECT f.releaseYear, COUNT(f) FROM Film f GROUP BY f.releaseYear")
    List<Object[]> countFilmsByYear();


	List<Film> findFilmsByRating(String rating);
    
	@Query("SELECT f FROM Film f WHERE f.releaseYear BETWEEN :fromYear AND :toYear")
    List<Film> findFilmsReleasedBetweenYears(int fromYear, int toYear);
	
    
    
    
}
