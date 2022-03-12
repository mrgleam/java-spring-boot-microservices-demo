import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.sass']
})
export class HeaderComponent implements OnInit {
  @Output() openCarts = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit(): void {
  }

  goToCarts(): void {
    this.openCarts.emit(true);
  }
}
