import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PaymentComponent } from './payment/payment.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, PaymentComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
}