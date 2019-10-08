import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  loggedIn:boolean;
  authorizationCode:string;
  spotifyAPIResponse:object;
  apiExtension:HTMLInputElement;
  
  constructor(private loginService:LoginService) { }

  ngOnInit() {
    this.authorizationCode = this.loginService.getcode();
    if(this.authorizationCode)
    {
      console.log("got a code");
      console.log(this.authorizationCode);
      this.loggedIn = true;
    }
    else
    {
      console.log("need a code");
      console.log(this.authorizationCode);
      this.loggedIn = false;
    }
  }
  askSpotify():void
  {
    this.apiExtension = <HTMLInputElement>document.getElementById('dashboardAPIExtension');
    this.spotifyAPIResponse = this.loginService.getAtAPIExtension(this.apiExtension.value);
  }
}
