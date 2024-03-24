import { Component } from '@angular/core';
import { ItemService } from './nevyhodcore/rest/service/item.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular-frontend';

  constructor(private itemService: ItemService) {
    itemService.findById(2).subscribe({
      next: r => {
        console.log("received green");
        console.log(r);
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
