import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsPerClassPageComponent } from './students-per-class-page.component';

describe('StudentsPerClassPageComponent', () => {
  let component: StudentsPerClassPageComponent;
  let fixture: ComponentFixture<StudentsPerClassPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentsPerClassPageComponent]
    });
    fixture = TestBed.createComponent(StudentsPerClassPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
