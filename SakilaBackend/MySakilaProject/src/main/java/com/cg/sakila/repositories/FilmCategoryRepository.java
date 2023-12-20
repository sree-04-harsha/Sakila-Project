package com.cg.sakila.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.sakila.enitites.FilmCategory;
import com.cg.sakila.enitites.FilmCategoryId;

import java.util.List;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryId> {

	List<FilmCategory> findByCategoryName(String category);

}
