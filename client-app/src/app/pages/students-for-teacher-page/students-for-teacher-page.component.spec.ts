import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsForTeacherPageComponent } from './students-for-teacher-page.component';

describe('StudentsForTeacherPageComponent', () => {
  let component: StudentsForTeacherPageComponent;
  let fixture: ComponentFixture<StudentsForTeacherPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentsForTeacherPageComponent]
    });
    fixture = TestBed.createComponent(StudentsForTeacherPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
