package com.revature.proj2.web;

import java.util.Arrays;
import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.revature.proj2.model.TokensRequest;
import com.revature.proj2.model.TokensResponse;

/**
 * Revature Proj2: Sortify
 * Package: Web
 * Rest.java
 * Rest Template
 * Purpose: Handles REST HTTP Requests to Spotify API
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/09/2019
 */

public class Rest {
	//Private variables
	private static String loginBaseUrl = "https://accounts.spotify.com/authorize";
	private static String clientID = "client_id=ac97261ac3e74255a9f5b928e6456d9d";
	private static String responseType = "response_type=code";
	private static String redirectUrl = "redirect_uri=http://localhost:8088/getSpotifyTokens";
	
	public static void spotifyLogin() {
		String URL = loginBaseUrl + "?" + clientID + "&" + responseType + "&" + redirectUrl;
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> resp = rt.getForEntity(URL, String.class);
		System.out.println(resp.toString());
	}
	
	public static ResponseEntity<TokensResponse> getSpotifyTokens(String tokenUrl, String redirectUri, String code, String clientID, String clientSecret) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setBasicAuth(clientID, clientSecret);
		TokensRequest tokensRequest = new TokensRequest("authorization_code", code, redirectUri);
		HttpEntity<TokensRequest> request = new HttpEntity<>(tokensRequest, headers);
		System.out.println(request.toString());
		ResponseEntity<TokensResponse> response = rt.exchange(tokenUrl, HttpMethod.POST, request, TokensResponse.class);
		return response;
	}
}
