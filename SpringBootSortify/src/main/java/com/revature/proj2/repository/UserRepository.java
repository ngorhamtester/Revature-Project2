package com.revature.proj2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.proj2.model.User;

/**
 * Revature Proj2: Sortify
 * Package: Repository
 * UserRepository.java
 * Interface
 * Purpose: Declares Data Access Object methods to be defined
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/02/2019
 */

@Repository(value="userRepository")
public interface UserRepository extends JpaRepository<User, String> {
	//CREATE METHODS
	/**
	 * Insert new User into DB
	 * 
	 * @param user User new user to insert into DB
	 */
	<S extends User>S save(User user);
	
	//READ METHODS
	/**
	 * Get User from DB by Spotify user id
	 * 
	 * @param id String Spotify user id
	 * @return user User Spotify user
	 */
	User findBySpotifyUserId(String id);
	
	//UPDATE METHODS
	/**
	 * Update User in DB
	 * 
	 * @param user User update Spotify user
	 */
	//void updateUser(User user);
	
	//DELETE METHODS
	/**
	 * Delete User from DB by User 
	 * 
	 * @param user User user to be deleted
	 */
	void delete(User user);
}
