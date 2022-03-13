import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Item } from '../models/item';
import { Order } from '../models/order';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.sass']
})
export class OrdersComponent implements OnInit {

  orders: Observable<Order[]> = of([]);
  isOpenCancelOrder: boolean = false;

  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    this.setOrders();
  }

  setOrders(): void {
    this.orders = this.orderService.getOrders();
  }

  openCancelOrder(): void {
    this.isOpenCancelOrder = true;
  }

  confirmCancelOrder(orderId: string): void {
    this.isOpenCancelOrder = false;
  }

  closeCancelOrder(): void {
    this.isOpenCancelOrder = false;
  }
}
