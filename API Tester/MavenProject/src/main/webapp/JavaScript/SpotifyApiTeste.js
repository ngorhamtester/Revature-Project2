//Stage1 Element Grabbing
	//Request Params
let authcode = document.getElementById('authcodeid');
let authresptype = document.getElementById('authresptype');
let authredirecturi = document.getElementById('authredirecturi');
	//Full Request
let authLink = document.getElementById('authlink');
let authurl = document.getElementById('authurlstring');
let request=document.getElementById("AuthRequest");
	//Response
let response = document.getElementById("AuthResponse");
let codeElement = document.getElementById("codeElement"); 

//Stage2 Element Grabbing
	//Request
		//Head
let s2Clientid = document.getElementById('tokenclientid');
let s2ClientSecret = document.getElementById('tokensecret');
let s2Authorization = document.getElementById('tokenAuthorization');
		//Body
let s2GrantType = document.getElementById('tokenGrantType');
let s2code = document.getElementById('tokencode');
let s2RedirectUri = document.getElementById('tokenRedirectUri');
		//Destination
let s2url = document.getElementById('tokenurl');
		//Final
let s2request = document.getElementById('TokenRequest');
let s2SubmitButton = document.getElementById('SubmitToken');
	//Response
let s2response = document.getElementById('TokenResponse');

//Stage3 Element Grabbing
	//Request Head
let s3TokenType = document.getElementById("spotifyApiTokenType");
let s3AccessToken = document.getElementById("spotifyApiAccessToken");
let s3Authorization = document.getElementById("spotifyApiAuthorization");
	//Request URL
let s3BaseUrl = document.getElementById("spotifyApiBaseUrl");
let s3Extension = document.getElementById("spotifyApiExtension");
	//SendRequest
let s3SubReq = document.getElementById("spotifyApiSubReq");
let s3Response = document.getElementById("s3Response");

//Stage4 Element Grabbing
	//Request
		//Head
let s4ClientId = document.getElementById("s4Client_id");
let s4ClientSecret = document.getElementById("s4Client_Secret");
let s4Authorization = document.getElementById("s4Authorization");
		//Body
let s4GrantType = document.getElementById("s4GrantType");
let s4RefreshToken = document.getElementById("s4RefreshToken");
		//URL
let s4TargetUrl = document.getElementById("s4TargetUrl");
	//Response
let s4Response = document.getElementById("s4Response");
let s4SubReq = document.getElementById("RefreshTokenSubReq");

	//Recursively Display a Json Object.
function DisplayJson(JsonObj, ContainerElement)
{
	//console.log(typeof JsonObj)
	if(typeof JsonObj == "object")
	{
		let ul1 = document.createElement('ul');
			//For each element in the object
		for(let element in JsonObj)
		{
			let li1= document.createElement('li');
			li1.append(element+" =");
			DisplayJson(JsonObj[element], li1);
			ul1.appendChild(li1);
		}
		ContainerElement.appendChild(ul1);
	}//Arrays are objects.
//	else if(typeof JsonObj == "array")
//	{
//		let ul2 = document.createElement('ul');
//		//For each element in the object
//		for(let element of JsonObj)
//		{
//			let li2= document.createElement('li');
//			DisplayJson(element, li2);
//			ul2.appendChild(li2);
//		}
//		ContainerElement.appendChild(ul2);
//	}
	else
	{
		ContainerElement.append(JsonObj);
	}
}

//Autofill Stage 1 Fields when Client_Id is inputted.
var composeurl = function compurl()
{
	authurl.value= "https://accounts.spotify.com/authorize"+
    "?" + "client_id="+
    authcode.value+
    "&"+ "response_type="+
    authresptype.value+
    "&"+"redirect_uri="+
    authredirecturi.value;
	authLink.href=authurl.value;
}

//Get Code returned from Stage 1 and autofill Stage 2 Field.
//Autofill Stage 2 Fields when Client_id and Client_Secret are inputted.
var composestage2 = function cs2()
{
	s2Authorization.value="Basic "+window.btoa(s2Clientid.value+":"+s2ClientSecret.value);
	s2url.value= "https://accounts.spotify.com/api/token";
	s2code.value=codeElement.innerHTML;
	s2RedirectUri.value=authredirecturi.value;
	s4ClientId.value = s2Clientid.value;
	s4ClientSecret.value = s2ClientSecret.value;
}

//Autofill Stage 3 Fields when New Endpoint is specified.
var composestage3 = function cs3()
{
	s3Authorization.value = s3TokenType.value + " " + s3AccessToken.value;
		//Request URL
	s3BaseUrl.value = "https://api.spotify.com"
}

//Autofill Stage 4 Fields
var composestate4 = function cs4()
{
	//Head
	s4Authorization.value ="Basic "+window.btoa(s4ClientId.value+":"+s4ClientSecret.value);
	s4GrantType.value = "refresh_token";
	s4TargetUrl.value = "https://accounts.spotify.com/api/token";
}



//Generate XMLHttpRequest object based on CORS standards.
function createCORSRequest(method, url)
{
  var xhr = new XMLHttpRequest();
  if ("withCredentials" in xhr)
  // Check if the XMLHttpRequest object has a "withCredentials" property.
  {
    // "withCredentials" only exists on XMLHTTPRequest2 objects.
    console.log("One");
    xhr.open(method, url, true);
  }
  else if (typeof XDomainRequest != "undefined")
  {
    // Otherwise, check if XDomainRequest.
    // XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    xhr = new XDomainRequest();
    console.log("Two");
    xhr.open(method, url);
  }
  else
  {
    console.log("Three");
    // Otherwise, CORS is not supported by the browser.
    xhr = null;
  }
  return xhr;
}
//Encode A string to conform to "application/x-www-form-urlencoded" format
function encodeForm(string)
{
	let basestring = "";
	let chars = string.split("");
	for(let char of chars)
	{
		if(char == ' ')
		{
			basestring += "+";
		}
		else if(char == '%')
		{
			basestring += "%25";
		}
		else if(char == '&')
		{
			basestring += "%26";
		}
		else if(char == '+')
		{
			basestring += "%2B";
		}
		else if(char == '#')
		{
			basestring += "%A3";
		}
		else if(char == '/')
		{
			basestring += "%2F";
		}
		else if(char == ':')
		{
			basestring += "%3A";
		}
		else
		{
			basestring += char;
		}
	}
	return basestring;
}

// Upon Person Signing in, Display request url that was sent and display code
// That was returned.
function PrimeGetAuthorizationFromSpotify()
{
  let url = authurl.value;
  
  request.innerHTML=url; 
  let parseurl = window.location.href.split('=');
  response.innerHTML = window.location.href;
  codeElement.innerHTML = parseurl[1];
}

// Using the code that was returned from the user authentication, as well as
// the client_ID and Client_Secret, Request an authentication token.
function submitRequestForToken()
{
	//A proxy is used when sending from LocalHost to get around CORS 
	//restrictions.
	const proxyurl = "https://cors-anywhere.herokuapp.com/";
	let xhr = createCORSRequest('POST', proxyurl+s2url.value); //ready state is 0.
	if(!xhr)
	{
	  throw new Error('CORS not supported');
	}
	//SET HEADERS.
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.setRequestHeader("Authorization",s2Authorization.value);
	var encoded = encodeForm(s2RedirectUri.value);
	//Form Body.
			//Storing ID and Secret In Body Instead of the Head is OPTIONAL
	var params = 	//'client_id='+s2Clientid.value+'&'+ 
					//'client_secret='+s2ClientSecret.value+'&'+
					'grant_type='+s2GrantType.value+'&'+
					'code='+s2code.value+'&'+
					'redirect_uri='+encoded;
	console.log(params);
	xhr.onreadystatechange = function()
	{
	  if(xhr.readyState === 4 && xhr.status === 200)
	  {
		  //Upon Successful Return of JSON, parse into unordered list.
		let responseitems = JSON.parse(xhr.responseText);
		let s2ulresp=document.createElement('ul');
		let li1=document.createElement('li');
		//An access token that can be provided in subsequent calls, for example to Spotify Web API services.
		li1.append("AccessToken="+responseitems.access_token);
		s3AccessToken.value = responseitems.access_token;
		s2ulresp.appendChild(li1);
		let li2=document.createElement('li');
		//How the access token may be used: always “Bearer”.
		li2.append("token_type  ="+responseitems.token_type);
		s3TokenType.value = responseitems.token_type;
		s2ulresp.appendChild(li2);
		let li3=document.createElement('li');
		//A space-separated list of scopes which have been granted for this access_token
		li3.append("scope       ="+responseitems.scope);
		s2ulresp.appendChild(li3);
		let li4=document.createElement('li');
		//The time period (in seconds) for which the access token is valid.
		li4.append("expire_time ="+responseitems.expires_in);
		s2ulresp.appendChild(li4);
		let li5=document.createElement('li');
		/*
		 * A token that can be sent to the Spotify Accounts service in place 
		 * of an authorization code. (When the access code expires, send a POST 
		 * request to the Accounts service /api/token endpoint, but use this code 
		 * in place of an authorization code. A new access token will be returned. 
		 * A new refresh token might be returned too.)
		 */
		s4RefreshToken.value = responseitems.refresh_token;
		li5.append("refreshToken="+responseitems.refresh_token);
		s2ulresp.appendChild(li5);
		s2response.appendChild(s2ulresp);
		composestage3();
		composestate4();
	  }
	}
	xhr.send(params); //ready state is 2.
}

//Send Request to Spotify for [Information]
function submitRequestForplaylist()
{
	
	const proxyurl = "https://cors-anywhere.herokuapp.com/";
	let xhr = createCORSRequest('GET', proxyurl+s3BaseUrl.value+s3Extension.value); //ready state is 0.
	if(!xhr)
	{
	  throw new Error('CORS not supported');
	}
	//Set Request Headers.
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.setRequestHeader("Authorization",s3Authorization.value);
	console.log("ButtonPress");
	xhr.onreadystatechange = function()
	{
	  if(xhr.readyState === 4 && xhr.status === 200)
	  {
		console.log("Success");
		//Reset Response Space
		s3Response.innerHTML="";
		//Fill Response Space
		let responseitems = JSON.parse(xhr.responseText);
		DisplayJson(responseitems, s3Response);
	  }
	}
	xhr.send(); //ready state is 2.
}

//Request New Token With Refresh Token
function submitRequestRefreshToken()
{
	console.log("Trying");
	const proxyurl = "https://cors-anywhere.herokuapp.com/";
	let xhr = createCORSRequest('POST', proxyurl+s4TargetUrl.value); //ready state is 0.
	if(!xhr)
	{
	  throw new Error('CORS not supported');
	}
	//Set Request Headers.
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.setRequestHeader("Authorization",s4Authorization.value);
	var params = 	//'client_id='+s2Clientid.value+'&'+ 
					//'client_secret='+s2ClientSecret.value+'&'+
					'grant_type='+s4GrantType.value+'&'+
					'refresh_token='+s4RefreshToken.value;

	console.log("params = --- "+params);
	xhr.onreadystatechange = function()
	{
	  if(xhr.readyState === 4 && xhr.status === 200)
	  {
		console.log("Success");
		let responseitems = JSON.parse(xhr.responseText);
		DisplayJson(responseitems, s4Response);
		s4RefreshToken.value
		s3AccessToken.value = responseitems.access_token;
		s3TokenType.value = responseitems.token_type;
	  }
	}
	xhr.send(params); //ready state is 2.
}


window.onload = function()
{
  composeurl();
  PrimeGetAuthorizationFromSpotify();
  composestage2();
}

//Stage1 Event Listeners
authcode.addEventListener("change", composeurl);
authresptype.addEventListener("change", composeurl);
authredirecturi.addEventListener("change", composeurl);
//Stage2 Event Listeners
s2ClientSecret.addEventListener("change", composestage2);
s2Clientid.addEventListener("change", composestage2);
s2SubmitButton.addEventListener("click", submitRequestForToken);
//Stage3 Event Listeners
s3Extension.addEventListener("change", composestage3);
s3SubReq.addEventListener("click", submitRequestForplaylist);
//Stage4 Event Listeners
s4SubReq.addEventListener("click", submitRequestRefreshToken);