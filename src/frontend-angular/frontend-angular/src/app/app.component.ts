import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Game } from './game';
import { Observable } from 'rxjs';
import { GameService } from './game.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public games: Game[] = [];

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

  ngOnInit(): void {
    this.getGames();
  }

  
}
