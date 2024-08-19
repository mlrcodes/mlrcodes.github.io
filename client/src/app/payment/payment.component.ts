import { Component, OnInit } from '@angular/core';
import { loadStripe, Stripe } from '@stripe/stripe-js';
import { HttpClient } from '@angular/common/http';
import { CheckoutFormComponent } from '../checkout-form/checkout-form.component';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [CheckoutFormComponent],
  templateUrl: 'payment.component.html'
})
export class PaymentComponent implements OnInit {
  stripe!: Stripe | null;
  clientSecret: string = '';

  constructor(private http: HttpClient) {}

  async ngOnInit() {
    this.http.get('http://localhost:8075/api/v1/stripe/public-key', { responseType: 'text' })
    .subscribe(async (publicKey: string) => {
      this.stripe = await loadStripe(publicKey)
      console.log(publicKey);  // Aquí tendrás la clave pública de Stripe como texto plano
    }, (error) => {
      console.error('Error fetching public key:', error);
    });
  
  

    this.http.post('http://localhost:8065/api/v1/orders', 
      {
        tickets: [14, 15],
        raffle: 1
      },
      {
        headers: { 'Content-Type': 'application/json' },
        responseType: 'text'  // Configura para recibir texto en lugar de JSON
      }
    ).subscribe((clientSecret: string) => {
      this.clientSecret = clientSecret;  // Aquí asignas el clientSecret recibido
    }, (error) => {
      console.error('Error during the request:', error);
    });
    
  }
}


