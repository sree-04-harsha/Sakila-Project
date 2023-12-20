import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { UserService } from './services/user.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'film-rental-service';
  showSearchOption: boolean = true;
  constructor(private router: Router, private userService: UserService) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.showSearchOption = event.url !== '/film-details';
      }
    });
  }
  roleCall(): any {
    return this.userService.getRoles()
  }
}
