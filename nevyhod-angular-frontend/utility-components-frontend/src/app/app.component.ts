import { Component } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ItemFactory, ItemVO } from 'utility-components-lib';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'utility-components-frontend';


  items: ItemVO[] = ItemFactory.makeDefault4List();
  items2: BehaviorSubject<ItemVO[]> = new BehaviorSubject<ItemVO[]>(ItemFactory.makeDefault4List());

  onPageChangedEvent(page: number) {
    alert('Hi green, I got a message: ' + page);
  }
}
