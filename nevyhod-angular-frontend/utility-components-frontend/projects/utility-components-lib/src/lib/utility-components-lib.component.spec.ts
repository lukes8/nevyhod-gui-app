import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UtilityComponentsLibComponent } from './utility-components-lib.component';

describe('UtilityComponentsLibComponent', () => {
  let component: UtilityComponentsLibComponent;
  let fixture: ComponentFixture<UtilityComponentsLibComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UtilityComponentsLibComponent]
    });
    fixture = TestBed.createComponent(UtilityComponentsLibComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
