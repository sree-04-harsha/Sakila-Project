import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { StaffService } from '../services/staff.service';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.css']
})
export class StaffComponent {

  staffList: any[] = [];
  staffData = {
    firstName: '',
    lastName: '',
    address: {
      addressId: 0
    },
    email: '',
    store: {
      storeId: 0
    },
    active: 1,
    lastUpdate: new Date().toISOString(),
    picture: null
  };

  staffId: number = 0;
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  showSection1: boolean = false;
  showSection2: boolean = false;
  showSection3: boolean = false;
  updateSuccessMessage: string = '';
  updateErrorMessage: string = '';
  updateSection: string = '';
  isSuccess = false;
  errorMessage = '';
  showSection: number = 0;

  constructor(private staffService: StaffService) { }

  ngOnInit(): void {
    this.loadStaffData();
    this.getStaffList();

  }
  getStaffList(): void {
    this.staffService.getStaffList().subscribe(
      (response: any) => {
        this.staffList = response;
      }
    );
  }
  onSubmit(staffForm: NgForm): void {
    if (staffForm.invalid) {
      this.errorMessage = 'Please fill in all required fields.';
      return;
    }

    if (!/^[a-zA-Z ]+$/.test(this.staffData.firstName)) {
      this.errorMessage = 'Invalid first name. Only letters and spaces are allowed.';
      return;
    }

    if (!/^[a-zA-Z ]+$/.test(this.staffData.lastName)) {
      this.errorMessage = 'Invalid last name. Only letters and spaces are allowed.';
      return;
    }

    if (!/^[1-2]$/.test(this.staffData.store.storeId.toString())) {
      this.errorMessage = 'Invalid store ID. Allowed values are 1 and 2.';
      return;
    }

    if (!/^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(this.staffData.email)) {
      this.errorMessage = 'Invalid email format.';
      return;
    }

    this.isSuccess = true;
    this.staffData = {
      firstName: '',
      lastName: '',
      address: {
        addressId: 0
      },
      email: '',
      store: {
        storeId: 0
      },
      active: 1,
      lastUpdate: new Date().toISOString(),
      picture: null
    };
  }


  loadStaffData(): void {
    this.staffService.getAllStaff().subscribe(
      data => {
        this.staffList = data;
      },
      error => {
        console.log('Error fetching staff data:', error);
      }
    );
  }


  openSection(section: number): void {
    this.showSection1 = section === 1;
    this.showSection2 = section === 2;
    this.showSection3 = section === 3;
  }

  //-------update first name---------
  openUpdateSection(section: string): void {
    this.showSection3 = true;
    this.updateSection = section;
    this.updateSuccessMessage = '';
    this.updateErrorMessage = '';
    this.staffId = 0; // Reset staff ID field
  }

  updateFirstName(): void {
    if (!/^[a-zA-Z ]*$/.test(this.staffData.firstName)) {
      alert('Invalid first name. Only letters and spaces are allowed.');
      return;
    }

    const updatePayload = {
      firstName: this.staffData.firstName
    };

    this.staffService.updateFirstName(this.staffId, updatePayload).subscribe(
      (response: any) => {
        this.updateSuccessMessage = 'First name updated successfully';
        this.getStaffList();
      },
      (error: any) => {
        alert(error.error);
        this.updateErrorMessage = 'Failed to update first name';
        console.log('Failed to update first name', error);
      }
    );
  }

  updateLastName(): void {
    if (!/^[a-zA-Z ]*$/.test(this.staffData.lastName)) {
      alert('Invalid last name. Only letters and spaces are allowed.');
      return;
    }

    const updatePayload = {
      lastName: this.staffData.lastName
    };

    this.staffService.updateLastName(this.staffId, updatePayload).subscribe(
      (response: any) => {
        this.updateSuccessMessage = 'Last name updated successfully';
        this.getStaffList();
      },
      (error: any) => {
        alert(error.error);
        this.updateErrorMessage = 'Failed to update last name';
        console.log('Failed to update last name', error);
      }
    );
  }

  updateEmail(): void {
    if (!/^[\w+.-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(this.staffData.email)) {
      alert('Invalid email format.');
      return;
    }

    const updatePayload = {
      email: this.staffData.email
    };

    this.staffService.updateEmail(this.staffId, updatePayload).subscribe(
      (response: any) => {
        this.updateSuccessMessage = 'Email updated successfully';
        this.getStaffList();
      },
      (error: any) => {
        alert(error.error);
        this.updateErrorMessage = 'Failed to update email';
        console.log('Failed to update email', error);
      }
    );
  }


  toggleSection(section: number): void {
    if (this.showSection === section) {
      this.showSection = 0;
    } else {
      this.showSection = section;
      this.updateSection = ''; // Reset update section
    }
  }

  toggleUpdateSection(section: string): void {
    if (this.updateSection === section) {
      this.updateSection = '';
    } else {
      this.updateSection = section;
    }
  }
}
