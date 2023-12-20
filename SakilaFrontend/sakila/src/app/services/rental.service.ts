import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RentalService {
  private apiUrl = 'http://localhost:8080/api/v1/admin/inventory';

  constructor(private http: HttpClient) { }

  getInventory(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  createRental(data: any): Observable<any> {
    return this.http.post(`http://localhost:8080/api/v1/admin/rental/add`, data, { responseType: 'text' });
  }

  createPayment(data: any): Observable<any> {
    return this.http.post('http://localhost:8080/api/v1/admin/payment/add', data, { responseType: 'text' });
  }

  getAllPayments(): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/v1/admin/payment/getpayments`);
  }

  getRevenueByPaymentDate(storeId: number): Observable<any> {
    const url = `http://localhost:8080/api/v1/admin/payment/revenue/datewise/store/${storeId}`;
    return this.http.get(url);
  }

  getFilmRevenue(): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/v1/admin/payment/revenue/filmwise`);
  }

}
