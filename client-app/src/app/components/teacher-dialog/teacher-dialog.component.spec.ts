import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherDialogComponent } from './teacher-dialog.component';

describe('TeacherDialogComponent', () => {
  let component: TeacherDialogComponent;
  let fixture: ComponentFixture<TeacherDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TeacherDialogComponent]
    });
    fixture = TestBed.createComponent(TeacherDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
