import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Actor, Film } from '../models/film.model';
@Injectable({
  providedIn: 'root'
})
export class ActorService {
  private apiUrl = 'http://localhost:8080/api/v1/admin/actors/post';
  private baseUrl = 'http://localhost:8080/api/v1/public/actors';
  private headers = new HttpHeaders().set('Content-Type', 'application/json');
  getAllActors(): Observable<Actor[]> {
    return this.http.get<Actor[]>(this.baseUrl);
  }
  constructor(private http: HttpClient) { }
  addActor(actor: Actor): Observable<ArrayBuffer> {
    return this.http.post(this.apiUrl, actor, { headers: this.headers, responseType: 'arraybuffer' });
  }
  updateActorLastName(actorId: number, newLastName: string): Observable<string> {
    const url = `http://localhost:8080/api/v1/admin/actors/update/lastname/${actorId}`;
    const requestBody = { newLastName };
    return this.http.put(url, requestBody, { responseType: 'text' }).pipe(
      map(() => 'Last Name Updated Successfully')
    );
  }
  updateActorFirstName(actorId: number, newFirstName: string): Observable<string> {
    const url = `http://localhost:8080/api/v1/admin/actors/update/firstname/${actorId}`;
    const requestBody = { newFirstname: newFirstName };
    return this.http.put(url, requestBody, { responseType: 'text' }).pipe(
      map(() => 'First Name Updated Successfully')
    );
  }
  assignFilmToActor(actorId: number, film: Film): Observable<string> {
    const url = `http://localhost:8080/api/v1/admin/actors/${actorId}/film`;
    return this.http.put(url, film, { responseType: 'text' }).pipe(
      map(() => 'Film successfully assigned to the actor')
    );
  }
  getActorById(actorId: number): Observable<Actor> {
    const url = `${this.apiUrl}/${actorId}`;
    return this.http.get<Actor>(url);
  }
  getFilmsByActorId(actorId: number): Observable<Film[]> {
    const url = `${this.baseUrl}/${actorId}/films`;
    return this.http.get<Film[]>(url);
  }
}