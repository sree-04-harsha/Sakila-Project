import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { FilmComponent } from './film/film.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { RentalHistoryComponent } from './rental-history/rental-history.component';
import { CustomerComponent } from './customer/customer.component';
import { AdminComponent } from './admin/admin.component';
import { AddFilmComponent } from './add-film/add-film.component';
import { AddActorComponent } from './add-actor/add-actor.component';
import { StaffComponent } from './staff/staff.component';
import { RentalComponent } from './rental/rental.component';
import { ActorComponent } from './actor/actor.component';
import { AssignFilmComponent } from './assign-film/assign-film.component';
import { ShowActorComponent } from './show-actor/show-actor.component';
import { httpInterceptorProviders } from './auth/auth.interceptor';
import { UserService } from './services/user.service';
import { StaffService } from './services/staff.service';
import { RentalService } from './services/rental.service';
import { FilmService } from './services/film.service';
import { CustomerService } from './services/customer.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CustomerComponentComponent } from './customer-component/customer-component.component';
import { GenreComponent } from './genre/genre.component';
import { NameComponent } from './name/name.component';
import { OrdersComponent } from './orders/orders.component';
import { RatingComponent } from './rating/rating.component';
import { UserComponent } from './user/user.component';
import { YearComponent } from './year/year.component';
import { FilmSearchComponent } from './film-search/film-search.component';
import { FilmDetailsComponent } from './film-details/film-details.component';

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    FilmComponent,
    LoginComponent,
    SignupComponent,
    RentalHistoryComponent,
    CustomerComponent,
    AdminComponent,
    AddFilmComponent,
    AddActorComponent,
    StaffComponent,
    RentalComponent,
    ActorComponent,
    AssignFilmComponent,
    ShowActorComponent,
    CustomerComponentComponent,
    GenreComponent,
    NameComponent,
    OrdersComponent,
    RatingComponent,
    UserComponent,
    YearComponent,
    FilmSearchComponent,
    FilmDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule
  ],
  providers: [
    UserService,
    StaffService,
    RentalService,
    FilmService,
    CustomerService,
    httpInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
