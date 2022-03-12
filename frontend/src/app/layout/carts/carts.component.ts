import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Item } from 'src/app/models/item';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-carts',
  templateUrl: './carts.component.html',
  styleUrls: ['./carts.component.sass'],
})
export class CartsComponent implements OnInit {
  @Output() closeCarts = new EventEmitter<boolean>();
  
  items: Observable<Item[]> = of([]);

  constructor(
    private router: Router,
    private cartService: CartService,
  ) {}

  ngOnInit(): void {
    this.items = this.cartService.carts$;
  }

  goToProducts(): void {
    this.closeCarts.emit(false);
    this.router.navigate(['/products']);
  }

  close(): void {
    this.closeCarts.emit(false);
  }
}
