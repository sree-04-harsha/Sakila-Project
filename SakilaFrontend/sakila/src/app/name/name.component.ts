import { AfterViewInit, Component } from '@angular/core';
import { RentalDetails } from '../models/rentalDetails';
import { WebserviceService } from '../services/webservice.service';


@Component({
  selector: 'app-name',
  templateUrl: './name.component.html',
  styleUrls: ['./name.component.css']
})
export class NameComponent {

  movieId: number = 0;
  cusId: number = 0;
  show: boolean = false;
  title: string = '';
  films: any[] = [];
  constructor(private webService: WebserviceService) {

  }

  setId(id: number) {
    this.movieId = id;
  }
  searchFilmsByName(title: string) {
    this.webService.searchByName(title)
      .subscribe(
        (data: any) => {
          this.films = data;

        },
        (error: any) => {
          console.log('Error occurred:', error);
        }
      );

    this.show = !this.show;
  }
}
