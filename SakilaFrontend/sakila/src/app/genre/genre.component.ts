import { Component } from '@angular/core';
import { WebserviceService } from '../services/webservice.service';


@Component({
  selector: 'app-genre',
  templateUrl: './genre.component.html',
  styleUrls: ['./genre.component.css']
})
export class GenreComponent {
  genre: string = '';
  show: boolean = false;
  films: any[] = [];
  constructor(private webService: WebserviceService) { }


  searchFilmsByGenre(genre: string) {
    this.show = true;
    this.webService.searchByGenre(genre)
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
