package com.cg.sakila.service;

import java.util.List;

import com.cg.sakila.enitites.Film;

public interface FilmCategoryService {
	
	List<Film> getFilmsByCategory(String category);

	void assignCategoryToFilm(int filmId, int categoryId);

}
