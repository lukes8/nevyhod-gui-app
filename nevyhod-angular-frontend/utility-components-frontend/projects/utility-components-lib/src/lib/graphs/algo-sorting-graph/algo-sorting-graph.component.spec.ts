import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlgoSortingGraphComponent } from './algo-sorting-graph.component';

describe('AlgoSortingGraphComponent', () => {
  let component: AlgoSortingGraphComponent;
  let fixture: ComponentFixture<AlgoSortingGraphComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AlgoSortingGraphComponent]
    });
    fixture = TestBed.createComponent(AlgoSortingGraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
