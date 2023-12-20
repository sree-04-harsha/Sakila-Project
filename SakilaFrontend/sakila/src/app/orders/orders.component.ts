import { Component } from '@angular/core';
import { WebserviceService } from '../services/webservice.service';


@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent {


  customerId: number = 0;

  details: any[] = [];
  constructor(private webService: WebserviceService) {

  }


  filmIdArr: any[] = [];


  title: string[] = [];


  getFilm() {

    for (let i = 0; i < this.filmIdArr.length; i++) {
      console.log("inside method");
      const filmId = this.filmIdArr[i];

      this.webService.findFilmById(Number(filmId)).subscribe((data: any) => {
        this.title.push(data.title);
      });
    }
  }









}
