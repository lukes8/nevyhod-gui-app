import { NgModule } from '@angular/core';
import { UtilityComponentsLibComponent } from './utility-components-lib.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { FormControlsComponent } from './form-controls/form-controls.component';
import { CardItemComponent } from './card-item/card-item.component';
import { CardItemCollectionComponent } from './card-item-collection/card-item-collection.component';
import { FormLoginComponent } from './form-login/form-login.component';
import { FormRegisterComponent } from './form-register/form-register.component';
import { FormAddComponent } from './form-add/form-add.component';
import { FormCartComponent } from './form-cart/form-cart.component';
import { TableGridBasicComponent } from './tables/table-grid-basic/table-grid-basic.component';
import { FormButtonComponent } from './form-controls/form-button/form-button.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    UtilityComponentsLibComponent,
    HeaderComponent,
    FooterComponent,
    FormControlsComponent,
    CardItemComponent,
    CardItemCollectionComponent,
    FormLoginComponent,
    FormRegisterComponent,
    FormAddComponent,
    FormCartComponent,
    FormButtonComponent
  ],
  imports: [ CommonModule, TableGridBasicComponent, HttpClientModule ],
  exports: [
    UtilityComponentsLibComponent, 
    TableGridBasicComponent, 
    HeaderComponent, 
    FormButtonComponent,
    CardItemComponent
  ]
})
export class UtilityComponentsLibModule { }
