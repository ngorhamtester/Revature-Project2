package com.revature.proj2.service;

import com.revature.proj2.model.User;
import com.revature.proj2.repository.UserRepository;
import com.revature.proj2.repository.UserRepositoryImpl;

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

public class UserService {
	//Private variables
	private UserRepository userRepository = new UserRepositoryImpl();
	
	//CREATE METHODS
	/**
	 * Insert new User into DB
	 * 
	 * @param user User new user to insert into DB
	 */
	public void insertUser(User user) {
		userRepository.insertUser(user);
	}
	
	//READ METHODS
	/**
	 * Get User from DB by Spotify user id
	 * 
	 * @param id String Spotify user id
	 * @return user User Spotify user
	 */
	User getUserById(String userId) {
		return userRepository.getUserById(userId);
	}
	
	/**
	 * Get User from DB by Spotify user URI
	 * 
	 * @param userUri String Spotify user URI
	 */
	User getUserByUri(String userUri) {
		return userRepository.getUserByUri(userUri);
	}
	
	//UPDATE METHODS
	/**
	 * Update User in DB
	 * 
	 * @param user User update Spotify user
	 */
	void updateUser(User user) {
		userRepository.updateUser(user);
	}
	
	//DELETE METHODS
	/**
	 * Delete User from DB by User 
	 * 
	 * @param int id User id
	 */
	void deleteUser(int id) {
		userRepository.deleteUser(id);
	}
}
