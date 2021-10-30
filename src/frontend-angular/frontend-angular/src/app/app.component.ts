import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Game } from './game/game';
import { Observable } from 'rxjs';
import { GameService } from './game/game.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public games: Game[] = [];
  public game!: Game;

  constructor(private gameService:GameService){

  }


  public getGames(): void {
    this.gameService.getGames().subscribe(
      (response: Game[]) => {
        this.games = response;
        console.log(this.games);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getGameName(gameName: string): void{
    this.gameService.getGameName(gameName).subscribe(
    (response: Game) => {
      this.game = response;
      console.log(this.games);
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );
}

  ngOnInit(): void {
    this.getGameName("Control");
  }

  
}
