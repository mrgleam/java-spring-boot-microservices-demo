import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-cancel-order',
  templateUrl: './cancel-order.component.html',
  styleUrls: ['./cancel-order.component.sass']
})
export class CancelOrderComponent implements OnInit {
  @Input()
  orderId!: string;
  @Output() confirm = new EventEmitter<string>();
  @Output() close = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit(): void {
  }
}
