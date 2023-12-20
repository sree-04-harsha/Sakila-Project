import { Component } from '@angular/core';
import { WebserviceService } from '../services/webservice.service';


@Component({
  selector: 'app-year',
  templateUrl: './year.component.html',
  styleUrls: ['./year.component.css']
})
export class YearComponent {

  year: string = '';
  show: boolean = false;
  films: any[] = [];
  constructor(private webService: WebserviceService) { }


  searchFilmsByYear(year: string) {
    this.show = true;
    this.webService.searchByYear(year)
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
