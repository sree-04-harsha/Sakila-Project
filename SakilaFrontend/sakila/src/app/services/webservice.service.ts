import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RentalDetails } from '../models/rentalDetails';
@Injectable({
       providedIn: 'root'
})
export class WebserviceService {
       constructor(private http: HttpClient) { }
       searchByName(title: string): Observable<any> {

              const url = `http://localhost:8080/api/v1/films/public/title/${title}`;
              return this.http.get(url);
       }
       searchByGenre(genre: string): Observable<any> {

              const url = `http://localhost:8080/api/v1/films/public/category/${genre}`;
              return this.http.get(url);
       }
       searchByRating(rating: string): Observable<any> {

              const url = `http://localhost:8080/api/v1/films/public/rating/${rating}`;
              return this.http.get(url);
       }

       searchByYear(year: string): Observable<any> {
              const url = `http://localhost:8080/api/v1/films/public/year/${year}`;
              return this.http.get(url);
       }

       //   createRentalDetails(rentalDetails: RentalDetails): Observable<any> {
       //          const url = `http://localhost:9095/api/v1/public/movie/post`;  //not worked
       //         return this.http.post<any>(url, rentalDetails);
       //          }

       //   getRentalDetails(): Observable<any> {
       //           const url = `http://localhost:9095/api/v1/public/movie/all`;   //not worked
       //         return this.http.get(url);
       //          }

       findFilmById(id: number): Observable<any> {
              const url = `http://localhost:8080/api/v1/films/public/${id}/actors`;
              return this.http.get(url);
       }
       searchById(id: number): Observable<any> {

              const url = `http://localhost:8080/api/v1/customer/customer/${id}`;

              return this.http.get(url);
       }

       searchRentalByCustomerId(id: number): Observable<any> {
              const url = `http://localhost:8080/api/v1/customer/rental/customer/${id}`;
              return this.http.get(url);
       }
}
