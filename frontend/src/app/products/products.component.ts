import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';
import { Item } from '../models/item';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.sass']
})
export class ProductsComponent implements OnInit {

  products: Observable<Product[]> = of([]);

  constructor(
    private productService: ProductService,
    private cartService: CartService) { }

  ngOnInit(): void {
    this.setProducts();
  }

  setProducts(): void {
    this.products = this.productService.getProducts();
  }

  addToCarts(product: Product): void {
    this.cartService.addToCarts(product); 
  }
}