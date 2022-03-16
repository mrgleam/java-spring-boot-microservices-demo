import { Injectable, NgZone } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productUrl = 'products';

  products: Product[] = [];

  constructor(private _zone: NgZone) { }

  getProducts(): Observable<Product[]> {
    this.products = [];
    return new Observable((observer) => {
      let eventSource = new EventSource(`${environment.apiProductServiceUrl}${this.productUrl}`);
      eventSource.onmessage = (event) => {
        console.log('Received event: ', event);
        let json = JSON.parse(event.data);
        this.products.push(json);
        this._zone.run(() => {
          observer.next(this.products);
        });
      }
      eventSource.onerror = (error) => {
        if (eventSource.readyState === 0) {
          console.log('The stream has been closed by the server.');
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
}
