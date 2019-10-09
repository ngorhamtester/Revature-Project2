import { Injectable } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ThrowStmt } from '@angular/compiler';

//LOGIN SERVICE:
/*
 *  The login service handles the 3 stages of the spotify api.
 *      1) Sending the user to spotify, and getting the code back when they 
 *            log in.
 *      2) Sending the code to spotify in exchange for a token
 *      3) Hitting a Spotify endpoint and getting Json
 *      4) Refreshing the token
 *
 */

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  // VARIABLE DECLARATIONS
  proxy:string = "https://cors-anywhere.herokuapp.com/"
  headers:object;
  body:object;

  baseURL:string;
  redirectUrl:string = "http://localhost:4200/dashboard";
  clientID:string = "ac97261ac3e74255a9f5b928e6456d9d";
  clientSecret:string = "ab01f29469964e4ab5e5a7133410b732";
  responseType:string;
  code:string; //Returned when the user logs in.
  url:string; // Base url and any extension.
  grantType:string;

  accessToken:string;
  tokenType:string;
  scope:string[];
  expireTime:number; //In seconds of token.
  refreshToken:string;

  uriExtension:string;

  jsonResponse:object;

  constructor(private router:Router, private activatedRoute:ActivatedRoute, private http:HttpClient) 
  { }


  //Convert A String to be URL Encoded.
  toUrlEncodeStr(str:string):string 
  {
    var code, i, len;
    var retString = "";
  
    for (i = 0, len = str.length; i < len; i++) 
    {
      code = str.charCodeAt(i);
      if (!(code > 47 && code < 58) && // numeric (0-9)
          !(code > 64 && code < 91) && // upper alpha (A-Z)
          !(code > 96 && code < 123)&&  // lower alpha (a-z)
          !(code == 45 || code == 46)&& // - .
          !(code == 95 || code == 126)) // _ ~
      {
        if(code == 32) // (Space)
        {
          retString += '+';
        }
        else
        {
          retString += '%' + code.toString(16).toUpperCase();
        }

      }
      else
      {
        retString += str.charAt(i);
      }
    }
    return retString;
  };

  //Convert an object to be URL Encoded.
  toUrlEncodeObj(obj:object):string
  {
    let encodedString = '';
    let first = true;
    for(let o in obj)
    {
      if(!first)
      {
        encodedString = encodedString + '&';
      }
      else
      {
        first = false;
      }
      encodedString = encodedString + this.toUrlEncodeStr(o) + '=' + this.toUrlEncodeStr(obj[o]);
    }
    return encodedString;
  }

  // Redirect User to Spotify Login. If successful, they will be redirected to 
  // our login page with a code appended to the url.
  login() 
  {
    this.baseURL = "https://accounts.spotify.com/authorize";
    this.responseType = "code";
    this.redirectUrl = this.redirectUrl + "";//Optional redirect extension. EX: /login
    this.url = this.baseURL+"?" + 
    "client_id="+ this.clientID +
    "&"+ "response_type="+ this.responseType +
    "&"+"redirect_uri="+ this.redirectUrl; 
    this.router.navigate(['/SpotifyLogin', { externalUrl: this.url }], 
    {skipLocationChange: true});
  }

  // Helper function for navigating.
  navigate( path:string, authcode:string) 
  {
    this.router.navigate([path, {code: authcode}]);
  }
  
  //When User gets back, check the url for for a Code or error.
  getNewCode()
  {    
    //Check url for code from login.
    this.activatedRoute.queryParams.subscribe(params =>
      {
        //User Accepted
        if(params['code'])
        {
          this.code = params['code'];
          this.getNewToken();
        }
        //User did Not Accept
        else if(params['error'] == 'access_denied')
        {
          console.log("User refused to let us use their acc.");
        }
      });
  }
  // Update code if there is a recent one appended in the URL. Then Return the
  // Code being stored in the service.
  getcode()
  {
    this.getNewCode();
    return this.code;
  }

  //Get a token, using the code returned when the user logged in.
  getNewToken()
  {
    this.grantType = "authorization_code";
    this.baseURL = "https://accounts.spotify.com/api/token";

    //Set Headers
    this.headers = {headers: new HttpHeaders(
    {
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': 'Basic '+ btoa(this.clientID+":"+this.clientSecret)
    })};
    
    console.log(this.headers['headers']);
    //Set Body
    this.body = {
      'grant_type': this.grantType,
      'code': this.code,
      'redirect_uri': this.redirectUrl
    };
    //Send request
    this.http.post<any>(this.proxy + this.baseURL, this.toUrlEncodeObj(this.body), this.headers)
        .subscribe(responseJson => 
    {
      this.accessToken = responseJson.access_token;
      this.tokenType = responseJson.token_type;
      this.scope = responseJson.scope;
      this.expireTime = responseJson.expires_in;
      this.refreshToken = responseJson.refresh_token;
    }, error => 
    {
      console.log(error.error);
      console.log(error.error.message);
      this.router.navigate(['/']);
    });
  }

  // Use the token to make an API call to Spotify.
  getAtAPIExtension(extension:string):object
  {
    this.baseURL = "https://api.spotify.com";

    //Set Headers
    this.headers = {headers: new HttpHeaders(
    {
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': this.tokenType + " " + this.accessToken
    })};
    return this.http.get<any>(this.proxy + this.baseURL + extension, this.headers).subscribe(error => 
    {
      console.log(error.error);
      console.log(error.error.message);
      this.router.navigate(['/']);
    });
  }
  // When your token expires, this function uses the refresh token to get a 
  // new token.
  getRefreshedToken()
  {
    this.grantType = "refresh_token";
    this.baseURL = "https://accounts.spotify.com/api/token";

    //Set Headers
    this.headers = {headers: new HttpHeaders(
    {
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': 'Basic '+ btoa(this.clientID+":"+this.clientSecret)
    })};
    //Set Body
    this.body = {
      'grant_type': this.grantType,
      'refresh_token': this.refreshToken
    };
    this.http.post<any>(this.proxy + this.baseURL, this.toUrlEncodeObj(this.body), this.headers).subscribe(responseJson => 
    {
      this.accessToken = responseJson.access_token;
      this.tokenType = responseJson.token_type;
      this.scope = responseJson.scope;
      this.expireTime = responseJson.expires_in;
      console.log(this.accessToken);
    }, error => 
    {
      console.log(error.error);
      console.log(error.error.message);
      this.router.navigate(['/']);
    });
  }
}
