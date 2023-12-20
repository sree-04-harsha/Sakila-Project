import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignFilmComponent } from './assign-film.component';

describe('AssignFilmComponent', () => {
  let component: AssignFilmComponent;
  let fixture: ComponentFixture<AssignFilmComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AssignFilmComponent]
    });
    fixture = TestBed.createComponent(AssignFilmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
