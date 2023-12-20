package com.cg.sakila.enitites;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class FilmCategoryId implements Serializable {

    @NotNull(message = "Film ID is required")
    @Column(name = "film_id")
    private int filmId;

    @NotNull(message = "Category ID is required")
    @Column(name = "category_id")
    private int categoryId;

    public FilmCategoryId(int filmId, int categoryId) {
        super();
        this.filmId = filmId;
        this.categoryId = categoryId;
    }

    public FilmCategoryId() {
        super();
    }

    public int getFilmId() {
        return filmId;
    }

	public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int i) {
        this.categoryId = i;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, filmId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FilmCategoryId other = (FilmCategoryId) obj;
        return Objects.equals(categoryId, other.categoryId) && Objects.equals(filmId, other.filmId);
    }
}
