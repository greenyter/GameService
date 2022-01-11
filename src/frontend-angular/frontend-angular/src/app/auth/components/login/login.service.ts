import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {User} from '../../user/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  user!:User;
  tempUser!:User;
  constructor(private httpclient:HttpClient) { }
  public tryToLogUser(userName:string,userPassword:string): Observable<any> {
    let params__ = new URLSearchParams();
    params__.append('userName', userName);
    params__.append('userPassword', userPassword);
    return this.httpclient.post('http://localhost:8080/GameService/user/login?userName='+userName+'&userPassword='+userPassword,null);
}



public getUserByName(userName:string):Observable<User>{
    return this.httpclient.get<User>(`http://localhost:8080/GameService/user/name?userName=`+userName);
  }
}
