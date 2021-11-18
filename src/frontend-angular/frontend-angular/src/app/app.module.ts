import { RegisterService } from './auth/components/register/register.service';
import { User } from './auth/user/user';
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
import { HomeComponent } from './home/home.component';
import { AuthModule } from './auth/auth.module';


@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    SearchComponent,
    SafePipe,
    HomeComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    AuthModule,
  
  ],
  providers: [GameService, RegisterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
