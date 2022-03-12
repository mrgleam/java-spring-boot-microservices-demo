import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-carts',
  templateUrl: './carts.component.html',
  styleUrls: ['./carts.component.sass'],
})
export class CartsComponent implements OnInit {
  @Output() closeCarts = new EventEmitter<boolean>();

  constructor(private router: Router) {}

  ngOnInit(): void {}

  goToProducts(): void {
    this.closeCarts.emit(false);
    this.router.navigate(['/products']);
  }

  close(): void {
    this.closeCarts.emit(false);
  }
}
