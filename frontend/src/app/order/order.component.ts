import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { Order } from '../models/order';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.sass']
})
export class OrderComponent implements OnInit {
  previousOrders: Observable<Order[]> = of([]);

  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    this.setPreviousOrders();
  }

  setPreviousOrders(): void {
    this.previousOrders = this.orderService.getOrders();
  }
}
