import { Component } from '@angular/core';
import { ItemFactory, ItemVO } from 'utility-components-lib';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'utility-components-frontend';


  items: ItemVO[] = ItemFactory.makeDefault4List();
}
