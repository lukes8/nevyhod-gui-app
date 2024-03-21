import { Component, Input } from '@angular/core';

@Component({
  selector: 'lib-column-list',
  templateUrl: './column-list.component.html',
  styleUrls: ['./column-list.component.css']
})
export class ColumnListComponent {
  @Input() values: number[] = [];
}
