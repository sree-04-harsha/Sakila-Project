import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CustomerService } from '../services/customer.service';
import { WebserviceService } from '../services/webservice.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent {

  showSection: number;   
  updateSection: string;
  updateSuccessMessage: string = '';
  updateErrorMessage: string = '';

  customerData = {
    customerId:0,
    storeId: 1,
    firstName: '',
    lastName: '',
    email: '',
    address: {
      addressId: 0,
      address: '',
      address2: '',
      district: '',
      city: {
        cityId: 0,
        city: '',
        country: {
          countryId: 0
        }
      },
      postalCode: '',
      phone: ''
    },
    active: 0,
    createDate: new Date().toISOString(),
    lastUpdate: new Date().toISOString()
  };
  customerId: number = 0;
  activeCustomers: any[];

  constructor(private customerService: CustomerService,private webService: WebserviceService) {
    this.showSection = 0;
    this.updateSection = '';
    this.customerId = 0;
    this.activeCustomers = [];
  
  }

   id:any=0;
  toggleSection(section: number) {
    this.showSection = section;
    if (this.showSection === 2) {
      this.getCustomerList();
    }
    if (this.showSection === 4) {
      this.fetchActiveCustomers();
    }
    if (this.showSection === 5) {
      this.fetchInactiveCustomers();
    }
    if (this.showSection === 6) {
      this.getByCustomerId(this.id);
    }
    
  }


  toggleUpdateSection(section: string) {
    this.updateSection = section;
  }

  onSubmitCustomerForm(customerForm: NgForm) {
    if (customerForm.valid) {
      // Perform additional custom validations
      if (!/^[0-9]{10}$/.test(this.customerData.address.phone)) {
        this.updateErrorMessage = 'Invalid phone number. Please enter 10-digit numbers only.';
        return;
      }
      // if (!/^[0-9]{6}$/.test(this.customerData.address.postalCode)) {
      //   this.updateErrorMessage = 'Invalid postal code. Please enter a 6-digit number.';
      //   return;
      // }
      if (this.customerData.storeId !== 1 && this.customerData.storeId !== 2) {
        this.updateErrorMessage = 'Invalid store ID. Only store IDs 1 and 2 are allowed.';
        return;
      }
      // if (this.customerData.address.city.cityId > 600) {
      //   this.updateErrorMessage = 'Invalid city ID. City ID should be 1 to 600.';
      //   return;
      // }
      if (this.customerData.address.addressId > 605) {
        this.updateErrorMessage = 'Invalid address ID. Address ID should be 1 to 605.';
        return;
      }
      // if (this.customerData.customerId < 607) {
      //   this.updateErrorMessage = 'Invalid customer Id...';
      //   return;
      // }
  
      this.customerService.addCustomer(this.customerData).subscribe(
        (response: any) => {
          this.updateErrorMessage = 'Failed to add customer.';
          customerForm.resetForm();
        },
        (error: any) => {
          this.updateSuccessMessage = 'Customer added successfully!';
        }
      );
    }
  }

  //----------get all customer--------------------------------------
  customerList: any[] = [];

  getCustomerList() {
    this.customerService.getCustomerList().subscribe(
      (response: any) => {
        this.customerList = response;
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

  //--------------------update ----------------------------------------

  updateFirstName(): void {
    const payload = this.customerData.firstName;
    this.customerService.updateFirstName(this.customerId, payload).subscribe(
      (response: any) => {
        this.updateSuccessMessage = 'First name updated successfully!';
        this.updateErrorMessage = '';
        // Reset form or perform other actions
      },
      (error: any) => {
        this.updateSuccessMessage = '';
        this.updateErrorMessage = 'Failed to update first name.';
      }
    );
  }

  updateLastName(): void {
    const payload = this.customerData.lastName;
    this.customerService.updateLastName(this.customerId, payload).subscribe(
      (response: any) => {
        this.updateSuccessMessage = 'Last name updated successfully!';
        this.updateErrorMessage = '';
        // Reset form or perform other actions
      },
      (error: any) => {
        this.updateSuccessMessage = '';
        this.updateErrorMessage = 'Failed to update last name.';
      }
    );
  }

  updateEmail(): void {
    const payload = this.customerData.email;
    this.customerService.updateEmail(this.customerId, payload).subscribe(
      (response: any) => {
        this.updateSuccessMessage = 'Email updated successfully!';
        this.updateErrorMessage = '';
        // Reset form or perform other actions
      },
      (error: any) => {
        this.updateSuccessMessage = '';
        this.updateErrorMessage = 'Failed to update email.';
      }
    );
  }

  updatePhone(): void {
    // Assuming this.customerData.email and this.customerId are defined
  
    const payload = {
      email: this.customerData.address.phone
    };
  
    this.customerService.updatePhone(this.customerId, payload).subscribe(
      (response: any) => {
        this.updateSuccessMessage = 'Phone Number updated successfully!';
        this.updateErrorMessage = '';
        // Reset form or perform other actions
      },
      (error: any) => {
        this.updateSuccessMessage = '';
        this.updateErrorMessage = 'Failed to update Phone Number.';
      }
    );
  }

  //--------------------active------------------------------
  fetchActiveCustomers() {
    this.customerService.getActiveCustomers().subscribe(
      (response: any) => {
        this.activeCustomers = response;
      },
      (error: any) => {
        console.error('Error:', error);
      }
    );
  }


  fetchInactiveCustomers() {
    this.customerService.getInactiveCustomers().subscribe(
      (response: any) => {
        this.activeCustomers = response;
      },
      (error: any) => {
        console.error('Error:', error);
      }
    );
  }

  customer:any="";
  
 
  getByCustomerId(id: number) {
    // this.show = true;
    this.webService.searchById(id)
      .subscribe(
        (data: any) => {
          this.customer = data;

        },
        (error: any) => {
          console.log('Error occurred:', error);
        }
      );
  }



}
