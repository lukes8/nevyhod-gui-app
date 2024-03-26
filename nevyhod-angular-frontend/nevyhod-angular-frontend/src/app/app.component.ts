import { Component } from '@angular/core';
import { ItemService } from './nevyhodcore/rest/service/item.service';
import { ItemFactory, ItemVO, IServerResponse } from 'utility-components-lib';
import { BehaviorSubject, delay, of } from 'rxjs';
import { PageableFactory } from 'utility-components-lib';
// import { IServerResponse, PaginationHelper } from 'utility-components-lib/lib/util/pagination-factory';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular-frontend';
  itemsSubject: BehaviorSubject<ItemVO[]> = new BehaviorSubject<ItemVO[]>([]);
  subject: BehaviorSubject<IServerResponse> = new BehaviorSubject<IServerResponse>(
    {
      items: [],
      page: 1,
      total: 0,
      perPage: 4
    }
  );

  constructor(private itemService: ItemService) {
    this.findAll(0);
  }

  findAll(page: number) {
    let pageable = PageableFactory.makeObject(page-1, 3, []);
    this.itemService.findAllByPageable(pageable).subscribe({
      next: r => {
        console.log("received green");
        console.log(r);
        this.subject.next({
          items: r.content,
          total: r.totalElements,
          page: page,
          perPage: pageable.perPage
        });
      },
      error: e => {
        console.error("received error");
        console.log(e);
      },
      complete: () => {
        console.log('complete');
      }
    })
  }

  onPageChangedEvent(page: number) {
    of("dummy observable timer").pipe(delay(1000)).subscribe(r => {
      console.log(r);
      this.findAll(page);
    });
  }

}
