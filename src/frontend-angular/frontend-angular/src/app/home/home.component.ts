import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Game } from '../game/game';
import { GameService } from '../game/game.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  sub:any
  namePath!:string

  public sort!: string;
  public game!: Game;
  public games: Game[] = [];
  
  constructor(private route: ActivatedRoute,private gameService:GameService, private router: Router) {  }

  ngOnInit(): void {
    {
      this.route.params.subscribe(params => {
        this.namePath = params['gameName'];
        this.getGames();
        console.log(this.getGames)
      });
    }
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
  public openGameSite(gameName:string): void{
    this.router.navigateByUrl(`/game/name/${gameName}`);
    console.log(gameName);
  }
}
