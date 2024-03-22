import { ChangeDetectionStrategy, Component, Input, OnInit } from '@angular/core';
import { Observable, delay, map, of, tap } from 'rxjs';
import { ItemVO } from '../factory/item-model';
import { PaginationHelper, PaginationHelperFactory } from '../util/pagination-factory';

interface IServerResponse {
  items: ItemVO[];
  total: number;
}

@Component({
  selector: 'lib-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PaginationComponent implements OnInit {
  @Input() items: ItemVO[] = [];
  asyncItems$: Observable<ItemVO[]>;
  itemsArray: ItemVO[];
  page: number = 1;
  total: number;
  loading: boolean = false;

  paginationHelper: PaginationHelper;

  ngOnInit(): void {
    this.paginationHelper = PaginationHelperFactory.makeInstance(this.items, this.page, this.total, this.loading);
    this.getPage(this.page);
  }

  /**
   * Async approach
   * @param page 
   */
  getPage(page: number) {
    this.loading = true;
    this.asyncItems$ = this.paginationHelper.getPage(page).pipe(tap(r => {
      this.total = r.total;
      this.page = page;
    }), map(r => r.items));
  }
}
