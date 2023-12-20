import { JsonPipe } from '@angular/common';
import { Component } from '@angular/core';
import { RentalService } from '../services/rental.service';

@Component({
  selector: 'app-rental',
  templateUrl: './rental.component.html',
  styleUrls: ['./rental.component.css']
})
export class RentalComponent {
  showSection: number = 0;
  updateSection: string = '';
  updateSuccessMessage: string = '';
  updateErrorMessage: string = '';
  inventoryList: any[] = [];
  storeId: number = 0;
  revenueList: any[] = [];
  errorMessage: string = '';
  isLoading: boolean = true;
  filmRevenueData: any[] = [];
  searchQuery: string = '';


  rentalData: any = {
    rentalDate: '',
    inventory: {
      inventoryId: 0
    },
    customer: {
      customerId: 0
    },
    returnDate: '',
    staff: {
      staffId: 0
    },
    lastUpdate: ''
  };

  paymentData: any = {
    customer: {
      customerId: null
    },
    staff: {
      staffId: null
    },
    rental: {
      rentalId: null
    },
    amount: null,
    paymentDate: '',
    lastUpdate: new Date().toISOString()
  };

  successMessage: string = '';
  showEmptyFieldError: boolean | undefined;
  constructor(private apiService: RentalService) { }

  toggleSection(section: number) {
    this.showSection = section;
    this.updateSection = '';
    this.updateSuccessMessage = '';
    this.updateErrorMessage = '';
  }

  toggleUpdateSection(section: string) {
    this.updateSection = section;
    this.updateSuccessMessage = '';
    this.updateErrorMessage = '';
  }

  ngOnInit(): void {
    this.getInventory();
    this.loadInventoryData();
    this.getPayments();
    this.getFilmRevenue();

  }

  getInventory(): void {
    this.apiService.getInventory().subscribe(
      (response: any) => {
        this.inventoryList = response;
      },
      (error: any) => {
        console.log(error);
      }
    );
  }
  loadInventoryData() {
    this.isLoading = true; // Set loading flag to true before making the API call
    this.apiService.getInventory().subscribe((data: any[]) => {
      this.isLoading = false; // Set loading flag to false after data is loaded
      // Process and assign the loaded inventory data
    }, (error) => {
      this.isLoading = false; // Set loading flag to false in case of error
      // Handle error
      console.error('Error fetching inventory data:', error);
    });
  }

  onSubmitRentalForm() {
    const currentDate = new Date();
    const returnDate = new Date(this.rentalData.returnDate);

    if (returnDate < currentDate) {
      this.errorMessage = 'Return date cannot be in the past.';
      return;
    }

    this.rentalData.rentalDate = currentDate.toISOString();
    this.rentalData.lastUpdate = currentDate.toISOString();

    this.apiService.createRental(this.rentalData).subscribe(
      (response) => {
        this.successMessage = 'Rental created successfully.';
        this.errorMessage = '';
        const rentalId = response.slice(12, 17);
        this.paymentData.rental.rentalId = rentalId; // Assign rentalId to paymentData
        this.paymentData.customer.customerId = this.rentalData.customer.customerId;
        this.paymentData.staff.staffId = this.rentalData.staff.staffId; // Autofill customer ID in paymentData
        alert('Rental created successfully with ID: ' + rentalId);
        console.log(response);
        // Reset form data
        this.rentalData = {
          rentalDate: '',
          inventory: {
            inventoryId: 0
          },
          customer: {
            customerId: 0
          },
          returnDate: '',
          staff: {
            staffId: 0
          },
          lastUpdate: ''
        };
      },
      (error) => {
        this.successMessage = '';
        this.errorMessage = 'Failed to create rental. Please try again.';
        console.log(error);
      }
    );
  }




  onSubmitPaymentForm(): void {
    const currentDate = new Date();
    this.paymentData.paymentDate = currentDate.toISOString();
    this.apiService.createPayment(this.paymentData).subscribe(
      (response: any) => {
        console.log(response);
        alert(response);
        // Reset the form
        this.paymentData = {
          customer: {
            customerId: 0
          },
          staff: {
            staffId: 0
          },
          rental: {
            rentalId: 0
          },
          amount: 0,
          paymentDate: '',
          lastUpdate: new Date().toISOString()
        };
      },
      (error: any) => {
        console.log('Error:', error);
        alert('Failed to create payment. Please try again.');
      }
    );
  }

  payments: any[] = [];
  getPayments(): void {
    this.apiService.getAllPayments().subscribe(
      (response) => {
        this.payments = response;
      },
      (error) => {
        console.log('Error:', error);
      }
    );
  }

  toggleRevenueList() {
    if (!this.storeId || isNaN(this.storeId)) {
      this.errorMessage = 'Input is invalid. Please enter a valid store ID.';
      this.revenueList = [];
      return;
    }

    this.errorMessage = '';

    if (this.revenueList.length === 0) {
      this.apiService.getRevenueByPaymentDate(this.storeId).subscribe(
        (data: any[]) => {
          if (data.length === 0) {
            this.errorMessage = 'Record not found.';
          } else {
            this.revenueList = data;
          }
        },
        (error) => {
          console.error('An error occurred:', error);
          if (error.error && error.error.message) {
            this.errorMessage = error.error.message;
          } else {
            this.errorMessage = 'Error occurred while fetching data.';
          }
        }
      );
    } else {
      this.revenueList = [];
    }
  }

  getFilmRevenue() {
    this.apiService.getFilmRevenue().subscribe(
      (data) => {
        this.filmRevenueData = data;
      },
      (error) => {
        console.log('Error:', error);
      }
    );
  }

  searchFilm() {
    if (!this.searchQuery) {
      this.showEmptyFieldError = true;
      return;
    }

    this.showEmptyFieldError = false;

    this.apiService.getFilmRevenue().subscribe(
      (data) => {
        const filteredData = data.filter((item: { filmTitle: string }) =>
          item.filmTitle.toLowerCase().includes(this.searchQuery.toLowerCase())
        );

        if (filteredData.length === 0) {
          console.log('Film not found.');
        }

        this.filmRevenueData = filteredData;
      },
      (error) => {
        console.log('Error:', error);
      }
    );
  }


}
