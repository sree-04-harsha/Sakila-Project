import { Component } from '@angular/core';
import { Film } from '../models/film.model';
import { ActorService } from '../services/actor.service';
@Component({
  selector: 'app-assign-film',
  templateUrl: './assign-film.component.html',
  styleUrls: ['./assign-film.component.css']
})
export class AssignFilmComponent {
  actorId: number = 0;
  filmId: number = 0;
  constructor(private actorService: ActorService) { }
  assignFilm() {
    if (!this.actorId) {
      alert('Please provide an actor ID');
      return;
    }
    if (!this.filmId) {
      alert('Please provide a film ID');
      return;
    }
    const film: Film = { filmId: this.filmId } as Film;
    this.actorService.assignFilmToActor(this.actorId, film).subscribe(
      (response) => {
        console.log(JSON.stringify(response))
        alert('Film successfully assigned to the actor')

      },
      (error) => {
        console.log(error)
        alert(error.error || "Film ID not found");
        this.filmId = 0;
      }
    );
  }
}
