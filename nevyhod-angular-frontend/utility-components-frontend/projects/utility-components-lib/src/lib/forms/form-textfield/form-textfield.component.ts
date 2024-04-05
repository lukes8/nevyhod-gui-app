import { Component, Input } from '@angular/core';

@Component({
  selector: 'lib-form-textfield',
  templateUrl: './form-textfield.component.html',
  styleUrls: ['../forms.scss']
})
export class FormTextfieldComponent {

  @Input() type: string = 's1' || 's2';
  @Input() placeholder: string;

}
