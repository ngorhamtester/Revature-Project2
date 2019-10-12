package com.revature.proj2;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.revature.proj2.model.Playlist;
import com.revature.proj2.model.User;
import com.revature.proj2.service.PlaylistService;
import com.revature.proj2.web.FrontController;

/**
 * Revature Proj2: Sortify
 * Package: Test
 * FrontControllerPlaylistTest.java
 * Unit Test
 * Purpose: Test Front Controller PlaylistService methods
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/07/2019
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FrontControllerPlaylistTest {
	//Private variables
	@LocalServerPort
	private int port;
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private PlaylistService playlistService;
	@InjectMocks
	private FrontController frontController;
	
	@Before
	public void setUp() {
		//Initialize Mockito mocks
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(frontController).build();
	}
	
	@Test
	public void testMocks() {
		assertNotNull(playlistService);
		assertNotNull(frontController);
	}
	
	@Test
	public void testInsertPlaylist() throws Exception {
		//Mock data
		String jsonStr = "{\"spotifyPlaylistId\":\"1\","
				+ "\"spotifyPlaylistName\":\"playlistName1\","
				+ "\"spotifyPlaylistUri\":\"URI1\","
				+ "\"spotifyUser\":{"
				+ "\"spotifyUserId\":\"1\","
				+ "\"spotifyUserUri\":\"URI1\","
				+ "\"spotifyDisplayName\":\"displayName1\","
				+ "\"spotifyEmail\":\"user1@email.com\""
				+ "}"
				+ "}";
		Playlist playlist = new Playlist("1", "playlistName1", "URI1",
				new User("1", "URI1", "displayName1", "user1@email.com"));
		
		mockMvc.perform(post("/insertPlaylist")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.spotifyPlaylistId", Matchers.is("1")))
				.andExpect(jsonPath("$.spotifyPlaylistName", Matchers.is("playlistName1")))
				.andExpect(jsonPath("$.spotifyPlaylistUri", Matchers.is("URI1")))
				.andExpect(jsonPath("$.spotifyUser.spotifyUserId", Matchers.is("1")))
				.andExpect(jsonPath("$.spotifyUser.spotifyUserUri", Matchers.is("URI1")))
				.andExpect(jsonPath("$.spotifyUser.spotifyDisplayName", Matchers.is("displayName1")))
				.andExpect(jsonPath("$.spotifyUser.spotifyEmail", Matchers.is("user1@email.com")))
				.andDo(print()).andReturn();
		
		verify(playlistService).insertPlaylist(playlist);
	}
	
	@Test
	public void testGetPlaylistsFromDB() throws Exception {
		//Mock data
		List<Playlist> playlists = new ArrayList<Playlist>();
		playlists.add(new Playlist("1", "playlistName1", "URI1",
				new User("2", "URI1", "displayName1", "user1@email.com")));
		Mockito.when(playlistService.getAllPlaylistsById("1")).thenReturn(playlists);
		
		mockMvc.perform(get("/getPlaylistsFromDB/2"))
				.andExpect(status().isOk())
				.andDo(print());
		
		verify(playlistService).getAllPlaylistsById("2");
	}
	
	@Test
	public void testGetPlaylistFromDB() throws Exception {
		//Mock data
		Playlist playlist = new Playlist("1", "playlistName1", "URI1",
				new User("2", "URI1", "displayName1", "user1@email.com"));
		
		Mockito.when(playlistService.getPlaylistById("1")).thenReturn(playlist);
		
		mockMvc.perform(get("/getPlaylistFromDB/1")
				.content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.spotifyPlaylistId", Matchers.is("1")))
				.andExpect(jsonPath("$.spotifyPlaylistName", Matchers.is("playlistName1")))
				.andExpect(jsonPath("$.spotifyPlaylistUri", Matchers.is("URI1")))
				.andExpect(jsonPath("$.spotifyUser.spotifyUserId", Matchers.is("2")))
				.andExpect(jsonPath("$.spotifyUser.spotifyUserUri", Matchers.is("URI1")))
				.andExpect(jsonPath("$.spotifyUser.spotifyDisplayName", Matchers.is("displayName1")))
				.andExpect(jsonPath("$.spotifyUser.spotifyEmail", Matchers.is("user1@email.com")))
				.andDo(print());
		
		verify(playlistService).getPlaylistById("1");
	}
	
	@Test
	public void testUpdatePlaylist() throws Exception {
		//Mock data
		String jsonStr = "{\"spotifyPlaylistId\":\"1\","
				+ "\"spotifyPlaylistName\":\"playlistName1\","
				+ "\"spotifyPlaylistUri\":\"URI1\","
				+ "\"spotifyUser\":{"
				+ "\"spotifyUserId\":\"1\","
				+ "\"spotifyUserUri\":\"URI1\","
				+ "\"spotifyDisplayName\":\"displayName1\","
				+ "\"spotifyEmail\":\"user1@email.com\""
				+ "}"
				+ "}";
		Playlist playlist = new Playlist("1", "playlistName1", "URI1",
				new User("1", "URI1", "displayName1", "user1@email.com"));
		
		mockMvc.perform(put("/updatePlaylist")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonStr))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.spotifyPlaylistId", Matchers.is("1")))
				.andExpect(jsonPath("$.spotifyPlaylistName", Matchers.is("playlistName1")))
				.andExpect(jsonPath("$.spotifyPlaylistUri", Matchers.is("URI1")))
				.andExpect(jsonPath("$.spotifyUser.spotifyUserId", Matchers.is("1")))
				.andExpect(jsonPath("$.spotifyUser.spotifyUserUri", Matchers.is("URI1")))
				.andExpect(jsonPath("$.spotifyUser.spotifyDisplayName", Matchers.is("displayName1")))
				.andExpect(jsonPath("$.spotifyUser.spotifyEmail", Matchers.is("user1@email.com")))
				.andDo(print());
		
		verify(playlistService).updatePlaylist(playlist);
	}
	
	@Test
	public void testDeletePlaylist() throws Exception {
		mockMvc.perform(delete("/deletePlaylist")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted())
				.andDo(print());		
	}
}
