import { Injectable } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ThrowStmt } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  proxy:string = "https://cors-anywhere.herokuapp.com/"
  headers:object;
  body:object;

  baseURL:string;
  redirectUrl:string = "http://localhost:4200";
  clientID:string = "ac97261ac3e74255a9f5b928e6456d9d";
  clientSecret:string = "ab01f29469964e4ab5e5a7133410b732"
  responseType:string;
  code:string;
  url:string;
  grantType:string;

  accessToken:string;
  tokenType:string;
  scope:string[];
  expireTime:number;
  refreshToken:string;

  uriExtension:string;

  jsonResponse:object;

  constructor(private router:Router, private activatedRoute:ActivatedRoute, private http:HttpClient) 
  { }
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
  navigate( path:string, authcode:string) 
  {
    this.router.navigate([path, {code: authcode}]);
  }
  
  getNewCode()
  {    
    //Check url for code from login.
    this.activatedRoute.queryParams.subscribe(params =>
      {
        if(params['code'])
        {
          this.code = params['code'];
          this.getNewToken();
        }
      });
  }

  getcode()
  {
    this.getNewCode();
    return this.code;
  }


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
    this.http.post<any>(this.proxy + this.baseURL, this.toUrlEncodeObj(this.body), this.headers).subscribe(responseJson => 
    {
      this.accessToken = responseJson.access_token;
      this.tokenType = responseJson.token_type;
      this.scope = responseJson.scope;
      this.expireTime = responseJson.expires_in;
      this.refreshToken = responseJson.refresh_token;
    });
  }


  getAtAPIExtension(extension:string):object
  {
    this.baseURL = "https://api.spotify.com";

    //Set Headers
    this.headers = {headers: new HttpHeaders(
    {
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': this.tokenType + " " + this.accessToken
    })};
    console.log("Look, Here is my access Token:" + this.accessToken);
    return this.http.get<any>(this.proxy + this.baseURL + extension, this.headers)//.subscribe(responseJson => 
    // {
    //   this.jsonResponse = responseJson;
    //   console.log("RESPONSE OBJECTIN = "+this.jsonResponse);
    //   return this.jsonResponse;
    // });

  }

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
    });
  }
}
