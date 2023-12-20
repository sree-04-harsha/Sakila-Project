import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StaffService {
  private apiUrl = 'http://localhost:8080/api/v1/admin/staff/post';

  constructor(private http: HttpClient) { }

  addStaff(staffData: any): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post(this.apiUrl, staffData, { responseType: 'text' });
  }

  getStaffList(): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/v1/admin/staff`);
  }

  getAllStaff(): Observable<any> {
    return this.http.get(`http://localhost:8080/api/v1/admin/staff`);
  }

  updateFirstName(staffId: number, updatePayload: any): Observable<any> {
    return this.http.put(`http://localhost:8080/api/v1/admin/staff/update/fn/${staffId}`, updatePayload, { responseType: 'text' });
  }

  updateLastName(staffId: number, updatePayload: any): Observable<any> {
    return this.http.put(`http://localhost:8080/api/v1/admin/staff/update/ln/${staffId}`, updatePayload, { responseType: 'text' });
  }

  updateEmail(staffId: number, updatePayload: any): Observable<any> {
    return this.http.put(`http://localhost:8080/api/v1/admin/staff/update/email/${staffId}`, updatePayload, { responseType: 'text' });
  }
}
