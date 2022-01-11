import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  public USER = SearchComponent.getLocalStorage();
  constructor(private router: Router) { }
  value!:string;
  ngOnInit(): void {
  }

  loginButton():void{
    this.router.navigateByUrl(`login`);
  }
  registerButton():void{
    this.router.navigateByUrl(`register`);
  }

  searchGame(value:string):void{
      

      this.router.navigateByUrl(`game/name/${value}`);
  }

  public static getLocalStorage(){
    if(localStorage.getItem('user')!=null){
      return localStorage.getItem('user');
    }else{
      return null;
    }
  }

  public getValue():any{
    return this.value;
  }

}
