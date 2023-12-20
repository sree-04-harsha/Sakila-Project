import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Film, Language } from '../models/film.model';
import { FilmService } from '../services/film.service';
import { UserService } from '../services/user.service';
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  section: number = 0;
  selectedForm: number = 0;

  showSection(sectionNumber: number) {
    this.section = sectionNumber;
  }
  showForm(formNumber: number) {
    this.selectedForm = formNumber;
  }
  movieId: number = 0;
  newTitle: string = '';
  newReleaseYear: number = 0;
  updateOption: string = '';
  newRentalDuration: number = 0;
  newRentalRate: number = 0;
  newLanguage: string = '';
  // categoryName: string = '';
  // filmId: number = 0;
  // categoryId: number = 0;
  filmId: number = 0;
  categoryId: number = 0;
  categoryName: string = '';

  constructor(private filmService: FilmService, private router: Router, public userService: UserService) { }
  //--------------update movie details------------------------------------------
  updateMovieTitle(): void {
    if (this.newTitle) {
      this.filmService.updateMovieTitle(this.movieId, this.newTitle).subscribe(
        (response: any) => {
          console.log('Movie title updated successfully');
          alert('Movie title updated successfully');
          // Handle any additional logic or display a success message
        }
      );
    }
  }
  updateMovieReleaseYear(): void {
    if (this.newReleaseYear) {
      this.filmService.updateMovieReleaseYear(this.movieId, this.newReleaseYear).subscribe(
        (response: any) => {
          console.log('Movie release year updated successfully');
          alert('Movie release year updated successfully');
          // Handle any additional logic or display a success message
        }
      );
    }
  }
  updateMovieRentalDuration(): void {
    if (this.newRentalDuration) {
      this.filmService.updateMovieRentalDuration(this.movieId, this.newRentalDuration).subscribe(
        (response: any) => {
          console.log('Rental duration updated successfully');
          alert('Rental duration updated successfully');
          // Handle any additional logic or display a success message
        },
        (error: any) => {
          console.log('Failed to update rental duration' + JSON.stringify(error));
          // Handle error response or display an error message
        }
      );
    }
  }

  updateMovieRentalRate(): void {
    if (this.newRentalRate) {
      this.filmService.updateMovieRentalRate(this.movieId, this.newRentalRate).subscribe(
        (response: any) => {
          console.log('Rental rate updated successfully');
          alert('Rental rate updated successfully');
          // Handle any additional logic or display a success message
        },
        (error: any) => {
          console.log('Failed to update rental rate');
          // Handle error response or display an error message
        }
      );
    }
  }

  updateMovieLanguage(): void {
    if (this.newLanguage) {
      this.filmService.updateMovieLanguage(this.movieId, this.newLanguage).subscribe(
        (response: any) => {
          console.log('Language updated successfully');
          alert('Language updated successfully');
          // Handle any additional logic or display a success message
        },
        (error: any) => {
          console.log('Failed to update language');
          // Handle error response or display an error message
        }
      );
    }
  }
  //--------------update movie details END------------------------------------------
  //--------------Add category to movie---------------------------------------------
  updateFilmCategory(): void {
    if (this.filmId && this.categoryId && this.categoryName) {
      this.filmService.updateFilmCategory(this.filmId, this.categoryId, this.categoryName).subscribe(
        () => {
          console.log('Film category updated successfully');
          alert('Film category updated successfully');
          // Additional logic or display success message
        },
        (error) => {
          console.log('Failed to update film category', error);
          // Handle error response
        }
      );
    }
  }
  ///--------------Add category to movie END--------------------------------------------
  //------------------log out------------------
  public logout() {
    // Perform the logout logic here, such as clearing session data or making API calls

    // Redirect to the home page
    this.userService.clear();
    this.router.navigate(['']);
  }
}
//