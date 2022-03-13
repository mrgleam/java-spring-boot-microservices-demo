import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Item } from '../models/item';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  carts = new BehaviorSubject<Item[]>([]);
  carts$: Observable<Item[]>;

  constructor() {
    this.carts$ = this.carts.asObservable();
  }

  addToCarts(product: Product): void {
    const previous: Item[] = this.carts.value;

    let current: Item[] = previous;

    if (previous.find((item) => item.productId === product.id)) {
      current = this.addPreviosItem(product.id, previous);
    } else {
      current = previous.concat({
        productId: product.id,
        name: product.name,
        price: product.price,
        quantity: 1,
      });
    }

    this.carts.next(current);
  }

  private addPreviosItem(productId: string, priviousItems: Item[]) {
    return priviousItems.map((item) => {
      if (item.productId === productId) {
        item.quantity = item.quantity + 1;
      }
      return item;
    });
  }
}
