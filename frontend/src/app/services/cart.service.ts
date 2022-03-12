import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of, Subject } from 'rxjs';
import { Item } from '../models/item';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  carts = new BehaviorSubject<Item[]>([]);
  carts$: Observable<Item[]>;

  constructor() {
    this.carts$ = this.carts.asObservable();
  }

  addToCarts(productId: string): void {
    const previous: Item[] = this.carts.value;

    let current: Item[] = previous;

    if (previous.find(item => item.productId === productId)) {
      current = this.addPreviosItem(productId, previous);
    } else {
      current = previous.concat({productId: productId, quantity: 1});
    }
    
    this.carts.next(current);
  }

  private addPreviosItem(productId: string, priviousItems: Item[]) {
    return priviousItems.map(item => {
      if (item.productId === productId) {
        item.quantity = item.quantity + 1;
      }
      return item;
    })
  }
}
