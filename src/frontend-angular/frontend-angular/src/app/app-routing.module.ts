import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GameComponent } from './game/game.component';
import { SearchComponent } from './search/search.component';

const routes: Routes = [
  { path: 'game/:gameName', component: GameComponent },
  {path: '', component: SearchComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
