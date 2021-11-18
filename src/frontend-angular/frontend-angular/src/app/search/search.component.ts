import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor(private router: Router) { }
  value!:string;
  ngOnInit(): void {
  }
  

  searchGame(value:string):void{

      this.router.navigateByUrl(`game/name/${value}`);
  }

  public getValue():any{
    return this.value;
  }

}
