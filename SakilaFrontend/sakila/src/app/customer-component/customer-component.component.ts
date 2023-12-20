import { Component } from '@angular/core';
import { WebserviceService } from '../services/webservice.service';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-component',
  templateUrl: './customer-component.component.html',
  styleUrls: ['./customer-component.component.css']
})
export class CustomerComponentComponent {
  customerId: string = '';
  show: boolean = false;
  show1: boolean = false;

  constructor(private webService: WebserviceService, private router: Router,public userService:UserService) { }

  customer: any = ""

  getByCustomerId(id: string) {
    this.show = true;
    this.webService.searchById(Number(id))
      .subscribe(
        (data: any) => {
          this.customer = data;

        },
        (error: any) => {
          console.log('Error occurred:', error);
        }
      );
  }

  rentals: any[] = [];

  searchRentalByCustomerId(id: string) {
    this.show1 = true;
    this.webService.searchRentalByCustomerId(Number(id))
      .subscribe(
        (data: any) => {
          this.rentals = data;

        },
        (error: any) => {
          console.log('Error occurred:', error);
        }
      );
  }
  public logout(){
    this.userService.clear();
    this.router.navigate(['']);
    }

}






