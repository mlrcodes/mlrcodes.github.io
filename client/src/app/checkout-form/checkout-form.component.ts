import { Component, Input } from '@angular/core';
import { Stripe } from '@stripe/stripe-js';
import { StripeElements } from '@stripe/stripe-js';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-checkout-form',
  standalone: true,
  templateUrl: 'checkout-form.component.html'
})
export class CheckoutFormComponent {
  @Input() stripe!: Stripe | null;
  @Input() clientSecret!: string;

  elements: StripeElements | null = null;
  isProcessing = false;
  message: string | null = null;

  constructor(private http: HttpClient) {}

  async ngOnInit() {
    if (this.stripe) {
      this.elements = this.stripe.elements({clientSecret: this.clientSecret});
      const paymentElement = this.elements.create('payment');
      paymentElement.mount('#payment-element');
    }
  }

  async handleSubmit(event: Event) {
    event.preventDefault();

    if (!this.stripe || !this.elements) {
      return;
    }

    this.isProcessing = true;

    const { error } = await this.stripe.confirmPayment({
      elements: this.elements,
      confirmParams: {
        return_url: `${window.location.origin}/completion`,
      },
    });

    if (error) {
      this.message = error.message || 'An unexpected error occurred.';
    }

    this.isProcessing = false;
  }
}
