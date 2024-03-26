import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
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
  @Input() items2: BehaviorSubject<ItemVO[]>;
  @Output() pageChangedEvent = new EventEmitter<number>();
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
    this.items2.subscribe(r => {
      this.paginationHelper = PaginationHelperFactory.makeInstance(r, this.page, this.total, this.loading);
      this.getPage(this.page);
    })
  }

  getPage(page: number) {
    this.loading = true;
    this.pageChangedEvent.emit(page);
    // this.subject.next(
    //   {
    //     items: [],
    //     total: this.subject.getValue().total,
    //     page: this.subject.getValue().page
    //   }
    // );
    this.paginationHelper.getPage(page).subscribe(r => {
      this.subject.next(r);
      console.log(r);
      this.loading = false;
    });
  }
}
