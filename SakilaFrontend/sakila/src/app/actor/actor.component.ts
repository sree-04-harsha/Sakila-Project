import { Component } from '@angular/core';
import { ActorService } from '../services/actor.service';
import { Film, Actor } from '../models/film.model';

@Component({
  selector: 'app-actor',
  templateUrl: './actor.component.html',
  styleUrls: ['./actor.component.css']
})
export class ActorComponent {
  actorId: number = 0;
  newLastName: string = '';
  newFirstName: string = '';
  filmId: number = 0;
  isFormSubmitted: boolean = false;

  constructor(private actorService: ActorService) { }

  isFormValid(field: string): boolean {
    if (field === 'lastName') {
      return /^[a-zA-Z]+$/.test(this.newLastName);
    }
    if (field === 'firstName') {
      return /^[a-zA-Z]+$/.test(this.newFirstName);
    }
    return false;
  }

  updateLastName() {
    if (!this.actorId) {
      alert('Please provide an actor ID');
      return;
    }
    if (!this.isFormValid('lastName')) {
      this.isFormSubmitted = true;
      return;
    }

    this.actorService.updateActorLastName(this.actorId, this.newLastName).subscribe(
      (response) => {
        alert('Last Name Updated Successfully');
        this.newLastName = '';
      },
      (error) => {
        alert('Error updating last name');
        console.error(error);
      }
    );
  }

  updateFirstName() {
    if (!this.actorId) {
      alert('Please provide an actor ID');
      return;
    }
    if (!this.isFormValid('firstName')) {
      this.isFormSubmitted = true;
      return;
    }

    this.actorService.updateActorFirstName(this.actorId, this.newFirstName).subscribe(
      (response) => {
        alert('First Name Updated Successfully');
        this.newFirstName = '';
      },
      (error) => {
        alert('Error updating first name');
        console.error(error);
      }
    );
  }

}
