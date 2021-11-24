import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GameComponent } from './game/game.component';
import { HomeComponent } from './home/home.component';
import { SearchComponent } from './search/search.component';

const routes: Routes = [ 
  { path: 'game/name/:gameName', component: GameComponent },
  {path: '', component: HomeComponent}, 
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
