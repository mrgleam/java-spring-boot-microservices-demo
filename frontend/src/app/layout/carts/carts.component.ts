import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Item } from 'src/app/models/item';
import { Order } from 'src/app/models/order';
import { CartService } from 'src/app/services/cart.service';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-carts',
  templateUrl: './carts.component.html',
  styleUrls: ['./carts.component.sass'],
})
export class CartsComponent implements OnInit {
  @Output() closeCarts = new EventEmitter<boolean>();

  items$: Observable<Item[]> = of([]);

  constructor(
    private router: Router,
    private cartService: CartService,
    private orderService: OrderService
  ) {}

  ngOnInit(): void {
    this.items$ = this.cartService.carts$;
  }

  checkout(): void {
    console.log();
    const order: Order = {
      id: null,
      userId: '1',
      paymentMode: this.fetchPaymentModes()[0],
      lineItems: this.cartService.carts.value,
      shippingAddress: {
        name: 'Bob',
        house: '24',
        street: 'Ashford Av.',
        city: 'New York',
        zip: '11001',
      },
      total: 0,
      orderStatus: null,
      responseMessage: null,
    };
    this.orderService.createOrder(order).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  goToProducts(): void {
    this.closeCarts.emit(false);
    this.router.navigate(['/products']);
  }

  close(): void {
    this.closeCarts.emit(false);
  }

  private fetchPaymentModes() {
    let paymentModes = ['Cash on Delivery', 'Card on Delivery'];
    return paymentModes;
  }
}
