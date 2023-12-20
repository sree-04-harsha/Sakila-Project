import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Films } from '../models/addfilm';
import { Category, Film } from '../models/film.model';
@Injectable({
  providedIn: 'root'
})
export class FilmService {
  // private apiUrl = 'http://localhost:8080/api/v1/public/films';
  constructor(private http: HttpClient) { }
  private apiUrl = 'http://localhost:8080/api/v1/films/admin/post';
  addFilm(film: Films): Observable<any> {
    return this.http.post(this.apiUrl, film, { responseType: 'text' });
  }
  getAllFilms(): Observable<Film[]> {
    return this.http.get<Film[]>('http://localhost:8080/api/v1/films/public/films');
  }
  searchFilmsByReleaseYear(year: number): Observable<Film[]> {
    const url = `http://localhost:8080/api/v1/films/public/year/${year}`;
    return this.http.get<Film[]>(url);
  }
  updateMovieTitle(movieId: number, newTitle: string): Observable<any> {
    const updatePayload = newTitle;
    return this.http.put(`http://localhost:8080/api/v1/films/admin/update/title/${movieId}`, updatePayload, { responseType: 'text' });
  }
  updateMovieReleaseYear(movieId: number, newReleaseYear: number): Observable<any> {
    const updatePayload = newReleaseYear;
    return this.http.put(`http://localhost:8080/api/v1/films/admin/update/releaseyear/${movieId}`, updatePayload, { responseType: 'text' });
  }

  updateMovieRentalDuration(movieId: number, newRentalDuration: number): Observable<any> {
    const updatePayload = newRentalDuration;
    return this.http.put(`http://localhost:8080/api/v1/films/admin/update/rentalduration/${movieId}`, updatePayload, { responseType: 'text' });
  }
  updateMovieRentalRate(movieId: number, newRentalRate: number): Observable<any> {
    const updatePayload = newRentalRate;
    return this.http.put(`http://localhost:8080/api/v1/films/admin/update/rentalrate/${movieId}`, updatePayload, { responseType: 'text' });
  }
  updateMovieLanguage(movieId: number, newLanguage: string): Observable<any> {
    const updatePayload =  newLanguage;
    return this.http.put(`http://localhost:8080/api/v1/films/admin/update/language/${movieId}`, updatePayload, { responseType: 'text' });
  }
  updateFilmCategory(filmId: number, categoryId: number, categoryName: string): Observable<any> {
    const updatePayload = {
      categoryId: categoryId,
      categoryName: categoryName,
      lastUpdate: new Date().toISOString()
    };
    return this.http.put(`http://localhost:8080/api/v1/films/admin/update/category/${categoryId}/film/${filmId}`, updatePayload, { responseType: 'text' });
  }
  searchFilmsByTitle(title: string) {

    return this.http.get(`http://localhost:8080/api/v1/films/public/title/${title}`);

  }
}