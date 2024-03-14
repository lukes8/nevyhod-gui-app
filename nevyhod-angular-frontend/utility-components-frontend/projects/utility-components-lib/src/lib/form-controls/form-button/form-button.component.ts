import { Component, Input } from '@angular/core';

@Component({
  selector: 'lib-form-button',
  templateUrl: './form-button.component.html',
  styleUrls: ['./form-button.component.css']
})
export class FormButtonComponent {
  @Input() visible: boolean = true;

  @Input() text: string = '';

  @Input() type: string = '';

}