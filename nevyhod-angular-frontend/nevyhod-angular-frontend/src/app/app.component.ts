import { Component } from '@angular/core';
import { ItemService } from './nevyhodcore/rest/service/item.service';
import { ItemFactory, ItemVO } from 'utility-components-lib';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular-frontend';
  items: BehaviorSubject<ItemVO[]> = new BehaviorSubject<ItemVO[]>([]);
  
  constructor(private itemService: ItemService) {
    itemService.findAll4Firebase().subscribe({
      next: r => {
        console.log("received green");
        console.log(r);
        // this.items.next(ItemFactory.makeDefault4List());
        this.items.next(r);
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


}
