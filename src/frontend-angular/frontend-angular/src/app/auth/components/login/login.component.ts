import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from '../../user/user';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private user:User | undefined;
  constructor(private loginService:LoginService) { }

  ngOnInit(): void {
  }

  public getRequestUser(userName:string, userPassword:string): void {
    this.loginService.getUser(userName, userPassword).subscribe(
      (response: User) => {
        this.user = response;
        console.log(this.user);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
