
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LandingPageComponent } from './landing-page/landing-page.component';
// import { FilmListComponent } from './film-list/film-list.component';
import { FilmComponent } from './film/film.component';
import { SignupComponent } from './signup/signup.component';
import { RentalHistoryComponent } from './rental-history/rental-history.component';
import { AdminComponent } from './admin/admin.component';
import { CustomerComponent } from './customer/customer.component';
import { ShowActorComponent } from './show-actor/show-actor.component';
import { Authguard } from './auth/auth.guard';
import { LoginComponent } from './login/login.component';
import { CustomerComponentComponent } from './customer-component/customer-component.component';
import { NameComponent } from './name/name.component';
import { GenreComponent } from './genre/genre.component';
import { YearComponent } from './year/year.component';
import { RatingComponent } from './rating/rating.component';
import { FilmDetailsComponent } from './film-details/film-details.component';
const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'films', component: FilmComponent },
  { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminComponent, canActivate: [Authguard] },
  { path: 'customer', component: CustomerComponent },
  { path: 'register', component: SignupComponent },
  { path: 'showactor', component: ShowActorComponent },
  { path: 'mycustomer', component: CustomerComponentComponent, canActivate: [Authguard] },
  { path: 'name', component: NameComponent },
  { path: 'genre', component: GenreComponent },
  { path: 'year', component: YearComponent },
  { path: 'rating', component: RatingComponent },
  { path: 'film-details', component: FilmDetailsComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

