import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit 
{
  basicurl:string;
  encurl:string;
  loggedIn:boolean;
  authorizationCode:string;
  title:string = 'Sortify';
  constructor(private loginService:LoginService) { }
  // On init, Check if there is an authorization code. If there is, then you 
  // are logged in. Else, you aren't
  ngOnInit() 
  {
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
  
  // When Login Button is pressed, If we are logged int, Go to dashboard.
  // Otherwise Send user to spotify to log in.
  tryLoggingIn()
  {
    if(this.loggedIn)
    {
      this.loginService.navigate("/dashboard", this.authorizationCode);
      console.log(this.authorizationCode);
    }
    else
    {
      this.loginService.login();
    }
  }
}
