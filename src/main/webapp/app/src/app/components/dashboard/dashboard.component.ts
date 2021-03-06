import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { UtilService } from 'src/app/services/util.service';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit 
{
  loggedIn:boolean;
  authorizationCode:string;
  spotifyAPIResponse:object;
  spotifyJsonResponse:any;
  apiExtension:HTMLInputElement;
  
  constructor(private loginService:LoginService, private activatedRoute:ActivatedRoute, private utilService:UtilService, private router:Router) { }

  ngOnInit() {
    console.log("oninitcalled")
    //this.authorizationCode = this.loginService.getcode();
    if(!this.loginService.accessToken) {
        this.activatedRoute.queryParams.subscribe(params =>
      {
        //User Accepted
        if(params['code'])
        {
          this.loginService.code = params['code'];
          this.loginService.getNewToken().subscribe(responseJson => 
            {
              this.loginService.accessToken = responseJson.access_token;
              this.loginService.tokenType = responseJson.token_type;
              this.loginService.scope = responseJson.scope;
              this.loginService.expireTime = responseJson.expires_in;
              this.loginService.refreshToken = responseJson.refresh_token;
              console.log("Hey! I got the token!");
              this.router.navigate(['/dashboard']);


            }, error => 
            {
              console.log(error.error);
              console.log(error.error.message);
              this.loginService.code = "";
              this.router.navigate(['/']);
            });
        }
        //User did Not Accept
        else if(params['error'] == 'access_denied')
        {
          console.log("User refused to let us use their acc.");
        }
      });
      }
      else
      {
       //ajax calls
       console.log(this.loginService.accessToken);
      }
    if(this.authorizationCode)
    {
      console.log("got a code");
      this.loggedIn = true;
    }
    else
    {
      console.log("need a code");
      this.loggedIn = false;
    }
   
  }
 
 getaccesstoken = ():void => {console.log(this.loginService.accessToken)}
  // Function that is run on the returned object when askSpotify Hits an endpoint.
  // This function must be defined using arrow notation to avoid referencing the
  // Wrong "this" while setting your variables.
  handleRequestObject = (responseJson:object):void =>
  {
    this.spotifyJsonResponse = responseJson;
    this.utilService.DisplayJson(this.spotifyJsonResponse, document.getElementById("jsonResponseContainer"), -1);
  }
  // A simple function that links a button press to an API endpoint Call with
  // a particular requestObjectHandler.
  askSpotify1():void
  {
    this.apiExtension = <HTMLInputElement>document.getElementById('dashboardAPIExtension'); 
    this.askSpotify(this.handleRequestObject, this.apiExtension.value);
  }


  // Generalized API Endpoint function that takes a function of the form:
  //      func(responseJson:obj):void
  // that defines what to do with the JsonObject when it is returned.
  askSpotify(apiRequestOnSuccess, endpoint:string):void
  {
    //Due to the HttpRequest being asynchronous, I must pass the observable and 
    //subscribe to it in the component so that the Json object is not returned 
    //after the function exits.
    //Make First Request 
    this.loginService.getAtAPIExtension(endpoint).subscribe(responseJson => 
    {
      //ON Success
      apiRequestOnSuccess(responseJson);
    }, error => 
    {
      //If First attempt fails, try refresh token and try again.
      this.loginService.onFailGetAtAPIExtension(error);
      //Attempting to refresh
      this.loginService.getRefreshedToken().subscribe(responseJson => 
      {
        //Token Refreshed
        this.loginService.onSuccessGetRefreshedToken(responseJson);
        //Try API call again with refreshed token. (2nd attempt)
        this.loginService.getAtAPIExtension(endpoint).subscribe(responseJson => 
        {
          //On success of second attempt.
          apiRequestOnSuccess(responseJson);
        }, error => 
        {
          //Refresh Token Succeded but new Token does not work.  
              //Should never reach this point.
          console.log("Impossible For Code To Reach (Token Failed, Refresh "+
                "token worked, new token failed) inside getAtAPIExtension");
          console.log(error.error);
          console.log(error.error.message);
          this.loginService.accessToken = "";
          this.loginService.refreshToken = "";
          this.loginService.code = "";
          this.router.navigate(['/']);
        });
      },error =>
      {
        // Refresh Token Fails
        this.loginService.onFailGetRefreshedToken(error);
        //Try and get new token and refresh token with current code
        this.loginService.getNewToken().subscribe(responseJson =>
        {
          //On success of new token/refresh token, try API call again.
          this.loginService.onSuccessGetNewToken(responseJson);
          this.loginService.getAtAPIExtension(endpoint).subscribe(responseJson => 
          {
            //On success of second attempt.
            apiRequestOnSuccess(responseJson);
          }, error => 
          {
            //new Token/Refresh Token Succeded but new Token does not work.  
                //Should never reach this point.
            console.log("Impossible For Code To Reach (Token Failed, Refresh "+
                  "token worked, new token failed) inside getAtAPIExtension");
            console.log(error.error);
            console.log(error.error.message);
            this.loginService.accessToken = "";
            this.loginService.refreshToken = "";
            this.loginService.code = "";
            this.router.navigate(['/']);
          });
        }, error =>
        {
          //Attempt to get new token/refresh token failed.
          this.loginService.onFailGetNewToken(error);
        });
      });  
    });
  }
}
