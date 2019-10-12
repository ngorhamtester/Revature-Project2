package com.revature.proj2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.proj2.model.User;
import com.revature.proj2.repository.UserRepository;

/**
 * Revature Proj2: Sortify
 * Package: Service
 * UserService.java
 * Service
 * Purpose: User DAO service
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/03/2019
 */

@Service(value="userService")
public class UserService {
	//Private variables
	private UserRepository userRepository;
	
	//Spring setter injection
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//CREATE METHODS
	/**
	 * Insert new User into DB
	 * 
	 * @param user User new user to insert into DB
	 */
	public void insertUser(User user) {
		userRepository.save(user);
	}
	
	//READ METHODS
	/**
	 * Get User from DB by Spotify user id
	 * 
	 * @param id String Spotify user id
	 * @return user User Spotify user
	 */
	public User getUserById(String userId) {
		return userRepository.findBySpotifyUserId(userId);
	}
	
	//UPDATE METHODS
	/**
	 * Update User in DB
	 * 
	 * @param user User update Spotify user
	 */
	public void updateUser(User user) {
		userRepository.save(user);
	}
	
	//DELETE METHODS
	/**
	 * Delete User from DB by User 
	 * 
	 * @param int id User id
	 */
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
}
