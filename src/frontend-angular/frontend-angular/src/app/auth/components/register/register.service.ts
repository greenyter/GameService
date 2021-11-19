import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private httpclient:HttpClient) { }
  public sendUser(userName:string, userEmail:string, userPassword:string): Observable<any> {
    let params__ = new URLSearchParams();
    params__.append('userName', userName);
    params__.append('userPassword', userPassword);
    params__.append('userEmail', userEmail);
    return this.httpclient.post('http://localhost:8080/GameService/user/add/new_user?userName='+userName+'&userPassword='+userPassword+'&userEmail='+userEmail,null);
}
}
