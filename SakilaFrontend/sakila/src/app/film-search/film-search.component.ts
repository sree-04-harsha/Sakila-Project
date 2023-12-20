import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FilmService } from '../services/film.service';

@Component({
  selector: 'app-film-search',
  templateUrl: './film-search.component.html',
  styleUrls: ['./film-search.component.css'],
})
export class FilmSearchComponent {
  searchTitle: string = "";
  films: any[] = [];
  selectedFilm: any;

  constructor(private filmService: FilmService, private router: Router) { }

  searchFilms() {
    if (this.searchTitle) {
      this.filmService.searchFilmsByTitle(this.searchTitle).subscribe(
        (response: any) => {
          const film = response[0]; // Assuming the API returns only one film for the given title
          if (film) {
            this.router.navigate(['/film-details'], { state: { film } });
          }
        },
        (error: any) => {
          alert("No films found")
          this.searchTitle = ""
          // Handle the error, show a message, etc.
        }
      );
    }
  }
}
