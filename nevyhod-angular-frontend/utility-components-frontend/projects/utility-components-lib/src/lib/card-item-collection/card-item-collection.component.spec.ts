import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardItemCollectionComponent } from './card-item-collection.component';

describe('CardItemCollectionComponent', () => {
  let component: CardItemCollectionComponent;
  let fixture: ComponentFixture<CardItemCollectionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CardItemCollectionComponent]
    });
    fixture = TestBed.createComponent(CardItemCollectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
