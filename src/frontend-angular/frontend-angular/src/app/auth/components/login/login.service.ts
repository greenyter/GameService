import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../user/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  
  private postsURL = "http://localhost:8080/GameService/game/id/1";
  
  constructor(private httpclient:HttpClient) { }

  public getUser(userName:string, userPassword:string): Observable<any> {
  
  const httpOptions = {
    
    headers: new HttpHeaders({
      'Content-Type':  'application/x-www-form-urlencoded',
      'Authorization': 'Basic ' + 'green11:123456789'
    })
  };
  return this.httpclient.get<User>(this.postsURL, httpOptions); 
  }
}
