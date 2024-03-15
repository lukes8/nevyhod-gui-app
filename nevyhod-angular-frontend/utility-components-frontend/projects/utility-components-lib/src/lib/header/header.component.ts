import { Component } from '@angular/core';
import { DataStorageService } from '../shared/data-storage.service';

@Component({
  selector: 'lib-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private _dataStorage: DataStorageService) { }

  onSaveData() {
    console.log('here we are')
    this._dataStorage.storeRecipes();
  }
}
