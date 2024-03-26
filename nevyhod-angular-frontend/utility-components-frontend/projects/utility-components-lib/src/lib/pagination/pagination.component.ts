import { ChangeDetectionStrategy, Component, Input, OnInit } from '@angular/core';
import { BehaviorSubject, Observable, delay, map, of, tap } from 'rxjs';
import { ItemVO } from '../factory/item-model';
import { IServerResponse, PaginationHelper, PaginationHelperFactory } from '../util/pagination-factory';

@Component({
  selector: 'lib-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
  // changeDetection: ChangeDetectionStrategy.OnPush
})
export class PaginationComponent implements OnInit {
  @Input() items: ItemVO[] = [];
  asyncItems$: Observable<ItemVO[]>;
  subject: BehaviorSubject<IServerResponse> = new BehaviorSubject<IServerResponse>(
    {
      items: [],
      total: 0,
      page: 0,
      perPage: 4
    }
  );
  itemsArray: ItemVO[];
  page: number = 1;
  total: number;
  loading: boolean = false;

  paginationHelper: PaginationHelper;

  ngOnInit(): void {
    this.paginationHelper = PaginationHelperFactory.makeInstance(this.items, this.page, this.total, this.loading);
    this.getPageNext(this.page);
  }

  /**
   * Async approach
   * @param page 
   */
  getPageNext(page: number) {
    this.loading = true;
    const res = this.paginationHelper.getPage(page).subscribe(r => {
      this.subject.next(r);
      this.page = page;
    });

  }
  getPageInit(page: number) {
    this.loading = true;
    this.paginationHelper.getPage(page).subscribe(r => {
      this.subject = new BehaviorSubject(r);
    });
  }
}
