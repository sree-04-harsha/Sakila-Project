import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Film } from '../models/film.model';
import { FilmService } from '../services/film.service';


@Component({
  selector: 'app-film-details',
  templateUrl: './film-details.component.html',
  styleUrls: ['./film-details.component.css'],
})
export class FilmDetailsComponent implements OnInit {
  film: Film | undefined;
  filmFound: boolean = true;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      const filmState = window.history.state.film;
      if (filmState) {
        this.film = filmState;
      } else {
        this.filmFound = false;
      }
    });
  }



}