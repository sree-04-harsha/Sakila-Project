package com.cg.sakila.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.sakila.enitites.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
