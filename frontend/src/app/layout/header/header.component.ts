import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { map, Observable, of } from 'rxjs';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.sass']
})
export class HeaderComponent implements OnInit {
  @Output() openCarts = new EventEmitter<boolean>();

  count: Observable<number> = of(0);

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.count = this.cartService.carts$.pipe(map(items => items?.reduce((p, c) => p + c.quantity, 0)));
  }

  goToCarts(): void {
    this.openCarts.emit(true);
  }
}
