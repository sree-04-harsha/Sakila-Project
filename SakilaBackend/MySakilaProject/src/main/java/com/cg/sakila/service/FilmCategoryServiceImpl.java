package com.cg.sakila.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.enitites.Category;
import com.cg.sakila.enitites.Film;
import com.cg.sakila.enitites.FilmCategory;
import com.cg.sakila.enitites.FilmCategoryId;
import com.cg.sakila.exception.CustomException;
import com.cg.sakila.repositories.CategoryRepository;
import com.cg.sakila.repositories.FilmCategoryRepository;
import com.cg.sakila.repositories.FilmRepository;

@Service
public class FilmCategoryServiceImpl implements FilmCategoryService {

	private final FilmCategoryRepository filmCategoryRepository;
	
	private final FilmRepository filmRepository;
	
	private final CategoryRepository categoryRepository;

	@Autowired
	public FilmCategoryServiceImpl(FilmCategoryRepository filmCategoryRepository, FilmRepository filmRepository, CategoryRepository categoryRepository) {
		this.filmCategoryRepository = filmCategoryRepository;
		this.filmRepository = filmRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Film> getFilmsByCategory(String category)throws CustomException {
		List<FilmCategory> filmCategories = filmCategoryRepository.findByCategoryName(category);
		List<Film> list = filmCategories.stream().map(FilmCategory::getFilm).toList();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("fIlmsCategory are not found.. ");
		}
	}

	///////// *******************REMAINING****************************////////

	@Override
	public void assignCategoryToFilm(int filmId, int categoryId) {
		Optional<Film> optionalFilm = filmRepository.findById(filmId);
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if(optionalFilm.isEmpty() || optionalCategory.isEmpty()) {
			throw new CustomException("Film or category not found");
		}
		Film film = optionalFilm.get();
		Category category = optionalCategory.get();
		film.setFilmId(filmId);
		
		FilmCategoryId filmCategoryId = new FilmCategoryId();
		filmCategoryId.setFilmId(filmId);
		filmCategoryId.setCategoryId(category.getCategoryId());

		FilmCategory filmCategory = new FilmCategory();
		filmCategory.setId(filmCategoryId);
		filmCategory.setFilm(film);
		filmCategory.setCategory(category);
		filmCategory.setLastUpdate(new Timestamp(System.currentTimeMillis()));

		filmCategoryRepository.save(filmCategory);
	}


}