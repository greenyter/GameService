import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Game} from './game';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private http: HttpClient){}

  public getGames(): Observable<Game[]> {
    return this.http.get<Game[]>(`http://localhost:8080/GameService/game/all`);
  }

  public getGameName(gameName: string): Observable<Game>{
    return this.http.get<Game>(`http://localhost:8080/GameService/game/name/${gameName}`);
  }
}
