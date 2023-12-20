import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowActorComponent } from './show-actor.component';

describe('ShowActorComponent', () => {
  let component: ShowActorComponent;
  let fixture: ComponentFixture<ShowActorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowActorComponent]
    });
    fixture = TestBed.createComponent(ShowActorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
