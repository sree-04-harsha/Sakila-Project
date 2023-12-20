import { Component } from '@angular/core';
import { WebserviceService } from '../services/webservice.service';


@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent {
  rating: string = '';
  show: boolean = false;
  films: any[] = [];
  constructor(private webService: WebserviceService) { }


  searchFilmsByRating(rating: string) {
    this.show = true;
    this.webService.searchByRating(rating)
      .subscribe(
        (data: any) => {
          this.films = data;

        },
        (error: any) => {
          console.log('Error occurred:', error);
        }
      );
  }

}
