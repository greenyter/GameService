import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private httpclient:HttpClient) { }
  public sendUser(userName:string, userPassword:string, userEmail:string): Observable<any> {
    
    return this.httpclient.post('http://localhost:8080/GameService/user/add/new_user',
  {
                params: {
                  'userName': userName,
                  'userPassword': userPassword,
                  'userEmail':userEmail
                },
  
            }
  );
  }
}
