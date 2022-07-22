import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css'],
})
export class AlertComponent {
  message: string = '';

  constructor() {}

  deleteAlert() {
    this.message = 'Set deleted successfuly';
  }
}
