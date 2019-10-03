package com.revature.proj2.repository;

import com.revature.proj2.model.User;

/**
 * Revature Proj2: Sortify
 * Package: Repository
 * UserRepositoryImpl.java
 * Implementation
 * Purpose: Declares Data Access Object
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/02/2019
 */

public class UserRepositoryImpl implements UserRepository {
	//CREATE METHODS
	/**
	 * Insert new User into DB
	 * 
	 * @param user User new user to insert into DB
	 */
	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		
	}
	
	//READ METHODS
	/**
	 * Get User from DB by Spotify user id
	 * 
	 * @param id String Spotify user id
	 * @return user User Spotify user
	 */
	@Override
	public User getUserById(String userId) {
		User user = null;
		return user;
	}

	/**
	 * Get User from DB by Spotify user URI
	 * 
	 * @param userUri String Spotify user URI
	 */
	@Override
	public User getUserByUri(String userUri) {
		User user = null;
		return user;
	}

	//UPDATE METHODS
	/**
	 * Update User in DB
	 * 
	 * @param user User update Spotify user
	 */	
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	//DELETE METHODS
	/**
	 * Delete User from DB by User 
	 * 
	 * @param user User delete Spotify user
	 */
	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
