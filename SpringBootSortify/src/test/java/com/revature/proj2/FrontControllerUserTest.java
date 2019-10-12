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

import com.revature.proj2.model.User;
import com.revature.proj2.service.UserService;
import com.revature.proj2.web.FrontController;

/**
 * Revature Proj2: Sortify
 * Package: Test
 * FrontControllerUserTest.java
 * Unit Test
 * Purpose: Test Front Controller UserService methods
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/07/2019
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FrontControllerUserTest {
	//Private variables
	@LocalServerPort
	private int port;
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private UserService userService;
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
		assertNotNull(userService);
		assertNotNull(frontController);
	}
	
	@Test
	public void testInsertUser() throws Exception {
		//Mock data
		String jsonStr = "{\"spotifyUserId\":\"1\","
				+ "\"spotifyUserUri\":\"URI1\","
				+ "\"spotifyDisplayName\":\"displayName1\","
				+ "\"spotifyEmail\":\"user1@email.com\""
				+ "}";
		User user = new User("1", "URI1", "displayName1", "user1@email.com");
		
		mockMvc.perform(post("/insertUser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.spotifyUserId", Matchers.is("1")))
				.andExpect(jsonPath("$.spotifyUserUri", Matchers.is("URI1")))
				.andExpect(jsonPath("$.spotifyDisplayName", Matchers.is("displayName1")))
				.andExpect(jsonPath("$.spotifyEmail", Matchers.is("user1@email.com")))
				.andDo(print());
		
		verify(userService).insertUser(user);
	}
	
	@Test
	public void testGetUserById() throws Exception {
		//Mock data
		User user = new User("1", "URI1", "displayName1", "user1@email.com");
		
		Mockito.when(userService.getUserById("1")).thenReturn(user);
		
		mockMvc.perform(get("/getUser/1")
				.content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.spotifyUserId", Matchers.is("1")))
				.andExpect(jsonPath("$.spotifyUserUri", Matchers.is("URI1")))
				.andExpect(jsonPath("$.spotifyDisplayName", Matchers.is("displayName1")))
				.andExpect(jsonPath("$.spotifyEmail", Matchers.is("user1@email.com")))
				.andDo(print());
		
		verify(userService).getUserById("1");
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		//Mock data
		String jsonStr = "{\"spotifyUserId\":\"1\","
				+ "\"spotifyUserUri\":\"URI1\","
				+ "\"spotifyDisplayName\":\"displayName1\","
				+ "\"spotifyEmail\":\"user1@email.com\""
				+ "}";
		User user = new User("1", "URI1", "displayName1", "user1@email.com");
		
		mockMvc.perform(put("/updateUser")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonStr))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.spotifyUserId", Matchers.is("1")))
				.andExpect(jsonPath("$.spotifyUserUri", Matchers.is("URI1")))
				.andExpect(jsonPath("$.spotifyDisplayName", Matchers.is("displayName1")))
				.andExpect(jsonPath("$.spotifyEmail", Matchers.is("user1@email.com")))
				.andDo(print());
		
		verify(userService).updateUser(user);
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		mockMvc.perform(delete("/deleteUser")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted())
				.andDo(print());
	}
}
