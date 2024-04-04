import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UtilityComponentsLibModule } from 'utility-components-lib';
import { SubtitleDirective } from './subtitle.directive';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    SubtitleDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    UtilityComponentsLibModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
