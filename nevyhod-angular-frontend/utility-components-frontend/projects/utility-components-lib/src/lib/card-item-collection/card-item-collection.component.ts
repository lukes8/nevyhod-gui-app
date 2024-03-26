import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ItemVO } from '../factory/item-model';
import { ItemFactory } from '../factory/item-factory';
import { PaginationComponent } from '../pagination/pagination.component';
import { BehaviorSubject, Observable, delay, map, of, tap } from 'rxjs';
import { IServerResponse, PaginationHelper, PaginationHelperFactory } from '../util/pagination-factory';

@Component({
  selector: 'lib-card-item-collection',
  templateUrl: './card-item-collection.component.html',
  styleUrls: ['./card-item-collection.component.css'],
  providers: [PaginationComponent]
})
export class CardItemCollectionComponent implements OnInit {

  @Output() pageChangedEvent = new EventEmitter<number>();
  @Input() subject: BehaviorSubject<IServerResponse> = new BehaviorSubject<IServerResponse>(
    {
      items: [],
      total: 0,
      page: 0,
      perPage: 4
    }
  );
  total: number;
  loading: boolean = false;
  page: number = 1;

  constructor() {
  }

  paginationHelper: PaginationHelper;

  ngOnInit(): void {
    this.subject.subscribe(r => {
      console.log('subscribed from card-collection');
      this.loading = false;
    })
  }

  getPage(page: number) {
    this.loading = true;
    this.pageChangedEvent.emit(page);
  }
}
