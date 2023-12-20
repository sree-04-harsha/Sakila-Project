export interface Rental {
  rentalId: number;
  rentalDate: Date;
  inventory: Inventory;
  customer: Customer;
  returnDate: Date;
  staff: Staff;
  lastUpdate: Date;
}

export interface Inventory {
  // Define the properties of the Inventory entity here
}

export interface Customer {
  // Define the properties of the Customer entity here
}

export interface Staff {
  // Define the properties of the Staff entity here
}
