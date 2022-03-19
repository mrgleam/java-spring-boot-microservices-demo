import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, NgZone } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Order } from '../models/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  orders: Order[] = [];

  private orderUrl = 'orders';

  constructor(private _zone: NgZone, private http: HttpClient) { }

  getOrders(): Observable<Order[]> {
    return new Observable((observer) => {
      let eventSource = new EventSource(`${environment.apiOrderServiceUrl}${this.orderUrl}`);
      eventSource.onmessage = (event) => {
        let json = JSON.parse(event?.data);
        this.orders.push(json);
        this._zone.run(() => {
          observer.next(this.orders);
        });
      }
      eventSource.onerror = (error) => {
        if(eventSource.readyState === 0) {
          eventSource.close();
          this._zone.run(() => {
            observer.complete();
          });
        } else {
          this._zone.run(() => {
            observer.error('EventSource error: ' + error);
          });
        }
      }
    });
  }

  createOrder(request: Order): Observable<Order> {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    let options = {headers:headers};
    return this.http.post<Order>(`${environment.apiOrderServiceUrl}${this.orderUrl}`, request, options)
  }
}
