import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Order } from '../models/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private orderUrl = 'orders';

  constructor(private http: HttpClient) { }

  getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${environment.apiOrderServiceUrl}${this.orderUrl}`);
  }

  createOrder(request: any): Observable<any> {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    let options = {headers:headers};
    return this.http.post(`${environment.apiOrderServiceUrl}${this.orderUrl}`, request, options)
  }
}
