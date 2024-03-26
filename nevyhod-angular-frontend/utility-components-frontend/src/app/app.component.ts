import { Component } from '@angular/core';
import { IServerResponse, PaginationHelper } from 'projects/utility-components-lib/src/lib/util/pagination-factory';
import { BehaviorSubject, Observable, delay, of } from 'rxjs';
import { ItemFactory, ItemVO } from 'utility-components-lib';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'utility-components-frontend';

  items: ItemVO[] = ItemFactory.makeDefault4List();
  subject: BehaviorSubject<IServerResponse> = new BehaviorSubject<IServerResponse>(
    {
      items: [],
      page: 1,
      total: 0,
      perPage: 4
    }
  );

  constructor() {
    this.subject.next({
      items: PaginationHelper.sliceItems(ItemFactory.makeDefault4List(), 1, 4),
      page: 1,
      total: ItemFactory.makeDefault4List().length,
      perPage: 4
    })
  }

  onPageChangedEvent(page: number) {

    of("dummy observable timer").pipe(delay(1000)).subscribe(r => {
      console.log(r);
      this.subject.next({
        items: PaginationHelper.sliceItems(ItemFactory.makeDefault4List(), page, 4),
        page: page,
        total: ItemFactory.makeDefault4List().length,
        perPage: 4
      })
    });
  }


}
