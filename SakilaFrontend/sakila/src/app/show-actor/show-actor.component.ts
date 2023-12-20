import { Component, OnInit } from '@angular/core';
import { Actor } from '../models/film.model';
import { ActorService } from '../services/actor.service';
@Component({
  selector: 'app-show-actor',
  templateUrl: './show-actor.component.html',
  styleUrls: ['./show-actor.component.css']
})
export class ShowActorComponent implements OnInit {
  actors: Actor[] = [];
  filteredActors: Actor[] = [];
  count: number = 0;
  visibleDropdowns: { [actorId: number]: boolean } = {};
  constructor(private actorService: ActorService) { }
  ngOnInit(): void {
    this.actorService.getAllActors().subscribe(actors => {
      this.actors = actors;
      this.loadActorFilms();
    });
  }
  loadActorFilms(): void {
    for (let actor of this.actors) {
      this.actorService.getFilmsByActorId(actor.actorId).subscribe(films => {
        actor.films = films;
      });
    }
  }
  filterById() {
    this.count++;
    const id = (document.getElementById('id') as HTMLInputElement).value.toLowerCase();
    this.filteredActors = this.actors.filter(actor => {
      return actor.actorId.toString().toLowerCase().includes(id);
    });
  }
  filterByFirstName() {
    this.count++;
    const id = (document.getElementById('firstname') as HTMLInputElement).value.toLowerCase();
    this.filteredActors = this.actors.filter(actor => {
      return actor.firstName.toString().toLowerCase().includes(id);
    });
  }
  filterByLastName() {
    this.count++;
    const id = (document.getElementById('lastname') as HTMLInputElement).value.toLowerCase();
    this.filteredActors = this.actors.filter(actor => {
      return actor.lastName.toString().toLowerCase().includes(id);
    });
  }
  toggleDropdown(actorId: number): void {
    this.visibleDropdowns[actorId] = !this.visibleDropdowns[actorId];
  }
  isDropdownVisible(actorId: number): boolean {
    return this.visibleDropdowns[actorId];
  }
  hasFilterCriteria(): boolean {
    const id = (document.getElementById('id') as HTMLInputElement).value;
    const firstName = (document.getElementById('firstname') as HTMLInputElement).value;
    const lastName = (document.getElementById('lastname') as HTMLInputElement).value;
    return !!(id || firstName || lastName);
  }
}