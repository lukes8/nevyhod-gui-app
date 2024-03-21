import { Component, Input } from '@angular/core';

@Component({
  selector: 'lib-column',
  templateUrl: './column.component.html',
  styleUrls: ['./column.component.css']
})
export class ColumnComponent {

  @Input() value: number = 0;
}
