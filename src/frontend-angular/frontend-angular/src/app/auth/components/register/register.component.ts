
import { RegisterService } from './register.service';
import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { User } from '../../user/user';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  private user:User | undefined;
  constructor(private registerService:RegisterService) { }

  ngOnInit(): void {
  }

  public sendRequestUser(userName:string, userPassword:string, userEmail:string): void {
    this.registerService.sendUser(userName, userPassword, userEmail).subscribe(
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
