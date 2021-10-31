import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GameService } from './game/game.service';
import { GameComponent } from './game/game.component';
import { SearchComponent } from './search/search.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { SafePipe } from './safe.pipe';


@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    SearchComponent,
    SafePipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [GameService],
  bootstrap: [AppComponent]
})
export class AppModule { }
