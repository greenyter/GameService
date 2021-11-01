import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Game } from './game';
import { GameService } from './game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  sub:any
  namePath!:string
  

  public games: Game[] = [];
  public game!: Game;
  constructor(private route: ActivatedRoute,private gameService:GameService) { }
  
  
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.namePath = params['gameName'];
      this.getGameName(this.namePath);
      console.log(this.namePath)
    });
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
      var date= new Date(this.game.releaseDate);
      this.game.releaseDate = date;
      console.log(this.games);
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );
}

}
