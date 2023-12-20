import { Component } from '@angular/core';
import { Rental } from '../models/rental.model';

@Component({
  selector: 'app-rental-history',
  templateUrl: './rental-history.component.html',
  styleUrls: ['./rental-history.component.css']
})
export class RentalHistoryComponent {
  rentalHistory: Rental[] = []; // Assuming Rental is a custom class or interface

  constructor() {
    // Fetch rental history data from API or mock data
  }
}
