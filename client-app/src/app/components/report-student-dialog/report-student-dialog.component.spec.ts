import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportStudentDialogComponent } from './report-student-dialog.component';

describe('ReportStudentDialogComponent', () => {
  let component: ReportStudentDialogComponent;
  let fixture: ComponentFixture<ReportStudentDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReportStudentDialogComponent]
    });
    fixture = TestBed.createComponent(ReportStudentDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
