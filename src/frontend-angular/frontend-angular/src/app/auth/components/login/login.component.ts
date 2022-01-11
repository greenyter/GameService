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

  private user!:User
  constructor(private loginService:LoginService) { }

  ngOnInit(): void {
  }

  public sendRequestUser(userName:string, userPassword:string): void {
    this.loginService.tryToLogUser(userName, userPassword).subscribe(
      (response: User) => {
        this.user = response;
        this.waitForOneSecond();
        var userObject = JSON.stringify(this.user);
        localStorage.setItem('user', userObject);
        console.log(userObject);
        alert("You has been logged succesfully!");
      },
      (error: HttpErrorResponse) => {
        alert("Wrong credentials!")
      }
    );
   
  }
  waitForOneSecond() {
    return new Promise(resolve => {
       setTimeout(() => {
          resolve("I promise to return after one second!");
       }, 1000);
    });
 }
  public ifLogged():boolean{
    if(this.user.tokenUser == "logged"){
      return true;
    }else{
      return false;
    }
  }


  public getStorageUser(){
    if(localStorage.getItem('user')!=null){  
      return JSON.parse(localStorage.getItem('user') || '{}')
    }
  }

  public getUser(userName:string): void {
    this.loginService.getUserByName(userName).subscribe(
      (response: User) => {
        this.user = response;
       
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
