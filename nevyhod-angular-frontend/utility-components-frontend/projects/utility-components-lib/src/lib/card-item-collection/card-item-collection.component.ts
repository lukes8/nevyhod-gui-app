import { Component, Input, OnInit } from '@angular/core';
import { ItemVO } from '../factory/item-model';
import { ItemFactory } from '../factory/item-factory';
import { PaginationComponent } from '../pagination/pagination.component';
import { BehaviorSubject, Observable, map, tap } from 'rxjs';
import { IServerResponse, PaginationHelper, PaginationHelperFactory } from '../util/pagination-factory';

@Component({
  selector: 'lib-card-item-collection',
  templateUrl: './card-item-collection.component.html',
  styleUrls: ['./card-item-collection.component.css'],
  providers: [PaginationComponent]
})
export class CardItemCollectionComponent implements OnInit {

  @Input() items: ItemVO[] = [];
  asyncItems$: Observable<ItemVO[]>;
  subject: BehaviorSubject<IServerResponse> = new BehaviorSubject<IServerResponse>(
    {
      items: [],
      total: 0,
      page: 0
    }
  );
  total: number;
  loading: boolean = false;
  page: number = 1;

  constructor() {
  }

  paginationHelper: PaginationHelper;

  ngOnInit(): void {
    this.paginationHelper = PaginationHelperFactory.makeInstance(this.items, this.page, this.total, this.loading);
    this.getPage(this.page);
  }

  getPage(page: number) {
    this.paginationHelper.getPage(page).subscribe(r => {
      this.subject.next(r);
      console.log(r)
    });
  }
}
