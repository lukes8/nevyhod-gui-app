import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UtilityComponentsLibModule } from 'utility-components-lib';
import { SubtitleDirective } from './subtitle.directive';

@NgModule({
  declarations: [
    AppComponent,
    SubtitleDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    UtilityComponentsLibModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
