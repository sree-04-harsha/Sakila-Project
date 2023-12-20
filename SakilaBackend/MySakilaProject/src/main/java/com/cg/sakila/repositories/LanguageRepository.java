package com.cg.sakila.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.sakila.enitites.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

	Language findByName(String languageName);

}
