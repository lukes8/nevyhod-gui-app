import { Component, Input } from '@angular/core';
import { ItemVO } from '../factory/item-model';
import { ItemFactory } from '../factory/item-factory';

@Component({
  selector: 'lib-card-item',
  templateUrl: './card-item.component.html',
  styleUrls: ['./card-item.component.css']
})
export class CardItemComponent {

  @Input() item: ItemVO = ItemFactory.makeDefault();

  flipCard: boolean = false;

  onFlipCard() {
    this.flipCard = !this.flipCard;
  }
}
