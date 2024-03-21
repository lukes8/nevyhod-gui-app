import { Component, Input } from '@angular/core';
import { ItemVO } from '../factory/item-model';
import { ItemFactory } from '../factory/item-factory';

@Component({
  selector: 'lib-card-item-collection',
  templateUrl: './card-item-collection.component.html',
  styleUrls: ['./card-item-collection.component.css']
})
export class CardItemCollectionComponent {

  @Input() items: ItemVO[] = [];
}
