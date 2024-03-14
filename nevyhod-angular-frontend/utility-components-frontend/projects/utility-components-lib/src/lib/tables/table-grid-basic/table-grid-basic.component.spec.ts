import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableGridBasicComponent } from './table-grid-basic.component';

describe('TableGridBasicComponent', () => {
  let component: TableGridBasicComponent;
  let fixture: ComponentFixture<TableGridBasicComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TableGridBasicComponent]
    });
    fixture = TestBed.createComponent(TableGridBasicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
