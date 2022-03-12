import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent {
  title = 'frontend';
  isOpenCarts = false;

  openCarts(): void {
    this.isOpenCarts = true;
  }

  closeCarts(): void {
    this.isOpenCarts = false;
  }
}
