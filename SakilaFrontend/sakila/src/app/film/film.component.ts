import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Film } from '../models/film.model';
import { FilmService } from '../services/film.service';


@Component({
  selector: 'app-film',
  templateUrl: './film.component.html',
  styleUrls: ['./film.component.css']
})
export class FilmComponent implements OnInit {
  films: Film[] = [];
  count: number = 0;
  filteredFilms: Film[] = [];

  constructor(private filmService: FilmService, private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.filmService.getAllFilms().subscribe(films => {
      this.films = films;
    });
  }

  ngAfterViewChecked() {
    this.cdr.detectChanges();
  }

  filterById() {
    this.count++;
    const id = (document.getElementById('id') as HTMLInputElement).value.toLowerCase();
    
    this.filteredFilms = this.films.filter(film => {
      console.log(film.filmId);
      return film.filmId.toString().toLowerCase().includes(id);
    });
  }
  filterByTitle() {
    this.count++;
    const title = (document.getElementById('title') as HTMLInputElement).value.toLowerCase();

    this.filteredFilms = this.films.filter(film => {
      return film.title.toString().toLowerCase().includes(title);
    });
  }
  filterByYear() {
    this.count++;
    const year = (document.getElementById('year') as HTMLInputElement).value.toLowerCase();

    this.filteredFilms = this.films.filter(film => {
      return film.releaseYear.toString().toLowerCase().includes(year);
    });
  }
  filterByLanguage() {
    this.count++;
    const language = (document.getElementById('language') as HTMLInputElement).value.toLowerCase();

    this.filteredFilms = this.films.filter(film => {
      return film.language.name.toString().toLowerCase().includes(language);
    });
  }
  filterByLength() {
    this.count++;
    const len = (document.getElementById('length') as HTMLInputElement).value.toLowerCase();

    this.filteredFilms = this.films.filter(film => {
      return film.length.toString().toLowerCase().includes(len);
    });
  }
  filterByRent() {
    this.count++;
    const rent = (document.getElementById('rent') as HTMLInputElement).value.toLowerCase();

    this.filteredFilms = this.films.filter(film => {
      return film.rentalRate.toString().toLowerCase().includes(rent);
    });
  }
  filterByRating() {
    this.count++;
    const rate = (document.getElementById('rating') as HTMLInputElement).value.toLowerCase();

    this.filteredFilms = this.films.filter(film => {
      return film.rating.toString().toLowerCase().includes(rate);
    });
  }

  hasFilterCriteria(): boolean {

    const id = (document.getElementById('id') as HTMLInputElement).value;
    const title = (document.getElementById('title') as HTMLInputElement).value;
    const year = (document.getElementById('year') as HTMLInputElement).value;
    const language = (document.getElementById('language') as HTMLInputElement).value;
    const rent = (document.getElementById('rent') as HTMLInputElement).value;
    const length = (document.getElementById('length') as HTMLInputElement).value;
    const rating = (document.getElementById('rating') as HTMLInputElement).value;
    return !!(id || title || year || language || rent || length || rating);
  }
}