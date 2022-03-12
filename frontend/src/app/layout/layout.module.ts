import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { CartsComponent } from './carts/carts.component';

@NgModule({
  declarations: [
    HeaderComponent,
    CartsComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    HeaderComponent,
    CartsComponent
  ]
})
export class LayoutModule { }
