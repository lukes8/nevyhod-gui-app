import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ColumnListComponent } from './column-list.component';

describe('ColumnListComponent', () => {
  let component: ColumnListComponent;
  let fixture: ComponentFixture<ColumnListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ColumnListComponent]
    });
    fixture = TestBed.createComponent(ColumnListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
