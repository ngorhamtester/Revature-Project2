package com.revature.proj2.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.revature.proj2.model.TokensResponse;
import com.revature.proj2.model.Playlist;
import com.revature.proj2.model.User;
import com.revature.proj2.service.PlaylistService;
import com.revature.proj2.service.UserService;

/**
 * Revature Proj2: Sortify
 * Package: Web
 * FrontController.java
 * Front Controller
 * Purpose: Handles HTTP Requests
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/05/2019
 */

@RestController
@CrossOrigin(origins="http://localhost:4200")
//@RequestMapping(value="/")
public class FrontController {
	//Private variables
	private UserService us;
	private PlaylistService ps;
	//Spotify variables
	private String proxy = "https://cors-anywhere.herokuapp.com/";
	private String baseUrl = "https://accounts.spotify.com/authorize?";
	private String tokenUrl = "https://accounts.spotify.com/api/token";
	private String responseType = "code";
	//Joe's clientID/clientSecret
	//private String clientID = "client_id=ed736dc4cdb4429792e0aa5dce505893";
	//private String clientSecret = "4f7c368bd8044a15b864f72107da5612";
	private String clientID = "832e87015a6a4a0ba9fd4ee9b4b064cb";
	private String clientSecret = "7efc5f1c32c247c5a4de5c25daf7245e";
	private String scope = "user-read-private%20user-read-email";
	//private String redirectUrl = "redirect_uri=http%3A%2F%2Flocalhost%3A4200%2Fdashboard";
	private String redirectUrl = "http%3A%2F%2Flocalhost%3A4200%2Fdashboard";
	
	
	//Inject UserService into this controller
	@Autowired
	public void setUserService(UserService us) {
		this.us = us;
	}
	
	//Inject PlaylistService into this controller
	@Autowired
	public void setPlaylistService(PlaylistService ps) {
		this.ps = ps;
	}
	
	// === USER CRUD METHODS ===
	/**
	 * Insert new User into DB
	 * 
	 * @param user User new user to insert into DB
	 */
	@PostMapping(value="/insertUser", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> insertUser(@RequestBody User user) {
		us.insertUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	/**
	 * Get User from DB by Spotify user id
	 * 
	 * @param id String Spotify user id
	 * @return user User Spotify user
	 */
	@GetMapping(value="/getUser/{id}")
	@ResponseBody
	public ResponseEntity<User> getUserById(@PathVariable String id) {
		User user = us.getUserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	/**
	 * Update User in DB
	 * 
	 * @param user User update Spotify user
	 */
	@PutMapping(value="/updateUser")
	@ResponseBody
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		if(user != null) { us.updateUser(user); }
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	/**
	 * Delete User from DB by User 
	 * 
	 * @param int id User id
	 */
	@DeleteMapping(value="/deleteUser")
	@ResponseBody
	public ResponseEntity<Void> deleteUser(User user) {
		us.deleteUser(user);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	// === PLAYLISTS CRUD METHODS
	/**
	 * Insert new Playlist into DB
	 * 
	 * @param playlist Playlist Spotify playlist
	 */
	@PostMapping(value="/insertPlaylist", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Playlist> insertPlaylist(@RequestBody Playlist playlist) {
		ps.insertPlaylist(playlist);
		return new ResponseEntity<Playlist>(playlist, HttpStatus.CREATED);
	}
	
	/**
	 * Get all User Playlists
	 * 
	 * @return playlists List list of User playlists
	 */
	@GetMapping(value="/getPlaylistsFromDB/{id}")
	@ResponseBody
	public ResponseEntity<List<Playlist>> getPlaylistsFromDB(@PathVariable String id) {
		List<Playlist> playlists =  ps.getAllPlaylistsById(id);
		return new ResponseEntity<List<Playlist>>(playlists, HttpStatus.OK);
	}
	
	/**
	 * Get User Playlist by id
	 * 
	 * @param id String Spotify playlist id
	 * @return playlist Playlist User playlist
	 */
	@GetMapping(value="/getPlaylistFromDB/{id}")
	@ResponseBody
	public ResponseEntity<Playlist> getPlaylistFromDB(@PathVariable String id) {
		Playlist playlist =  ps.getPlaylistById(id);
		return new ResponseEntity<Playlist>(playlist, HttpStatus.OK);
	}
	
	/**
	 * Update User Playlist
	 * 
	 * @param playlist Playlist User playlist
	 */
	@PutMapping(value="/updatePlaylist")
	@ResponseBody
	public ResponseEntity<Playlist> updatePlaylist(@RequestBody Playlist playlist) {
		if(playlist != null) { ps.updatePlaylist(playlist); }
		return new ResponseEntity<Playlist>(playlist, HttpStatus.OK);
	}
	
	/**
	 * Delete User Playlist
	 * 
	 * @param playlist Playlist User playlist to be deleted
	 */
	@DeleteMapping(value="/deletePlaylist")
	@ResponseBody
	public ResponseEntity<Void> deletePlaylist(Playlist playlist) {
		ps.deletePlaylist(playlist);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	// === SPOTIFY ENDPOINTS ===
	/**
	 * Handle Spotify login/authentication
	 */
	@GetMapping(value="/spotifyAPICall")
	public ResponseEntity<String> spotifyAPICall(@RequestHeader(value="Content-Type") String contentType, @RequestParam String prop) {
		System.out.println("endpoint reached");
		//System.out.println("auth" + auth);
		System.out.println("content-type: " + contentType);
		System.out.println("prop: " + prop);
		return new ResponseEntity<String>(prop, HttpStatus.OK);
	}
	
	@GetMapping(value="/login")
	public ResponseEntity<Object> login() {
		String url = baseUrl 
				+ "client_id=" + clientID + "&" 
				+ "response_type="+ responseType + "&" 
				+ "redirect_uri=" + redirectUrl + "&" 
				+ "scope=" + scope;
		URI spotifyAuth = null;
		try {
			spotifyAuth = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		httpHeaders.setAccessControlAllowOrigin("*"); //bad practice
		httpHeaders.setLocation(spotifyAuth);
		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
	
	@GetMapping(value="/getSpotifyTokens")
	public void getSpotifyTokens(@RequestParam("code") String code) {
		//Rest call to get tokens
		System.out.println(code);
		ResponseEntity<TokensResponse> tokensResponse = 
				Rest.getSpotifyTokens(tokenUrl, redirectUrl, code, clientID, clientSecret);
		System.out.println(tokensResponse.toString());
		//return tokensResponse;
		//return new ResponseEntity<TokensResponse>(tokensResponse, HttpStatus.OK);
	}
	
	/**
	 * Get User Playlists from Spotify
	 * 
	 * @return
	 */
	@GetMapping(value="/getPlaylistsFromSpotify")
	public String getPlaylistsFromSpotify() {
		return "get playlists called";
	}
	
	/**
	 * Send Merged Playlist to Spotify
	 * 
	 * @return
	 */
	@PostMapping(value="/mergePlaylists")
	public String mergePlaylists() {
		return "merge playlists called";
	}
}
