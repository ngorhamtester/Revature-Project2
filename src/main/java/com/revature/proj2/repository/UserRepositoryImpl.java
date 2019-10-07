package com.revature.proj2.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.proj2.model.Playlist;
import com.revature.proj2.model.User;
import com.revature.proj2.util.SortifySessionFactory;

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
		//Hibernate Session
		Session s = null;
		//Hibernate Transaction
		Transaction tx = null;
		try {
			s = SortifySessionFactory.getSession();
			tx = s.beginTransaction();
			//Can use save() or persist() method here
			//Both methods will persist an object but do not work the same
			//save() promises to assign unique id and immediately save the object
			//persist() only guarantees that object will persisted some time befoe session ends
			s.save(user);
			tx.commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}	
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
		//Hibernate session
		Session s = null;
		//Hibernate Transaction
		Transaction tx = null;
		try {
			s = SortifySessionFactory.getSession();
			tx = s.beginTransaction();
			user = s.get(User.class, userId);
			tx.commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}
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
		//Hibernate session
		Session s = null;
		//Hibernate Transaction
		Transaction tx = null;
		try {
			s = SortifySessionFactory.getSession();
			tx = s.beginTransaction();
			user = s.get(User.class, userUri);
			tx.commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}
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
		//Hibernate session
		Session s = null;
		//Hibernate Transaction
		Transaction tx = null;
		try {
			s = SortifySessionFactory.getSession();
			tx = s.beginTransaction();
			//Three ways to update: merge(), update(), saveOrUpdate()
			//merge() updates a persistent entity with fields values from a detached instance
			//update() transitions a passed object from a detached to persistent; cannot be called on
			//transient objects
			//saveOrUpdate() make an object persistent regardless of its state(transient or detached)
			s.merge(user);
			tx.commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}	
	}

	//DELETE METHODS
	/**
	 * Delete User from DB by User 
	 * 
	 * @param user User delete Spotify user
	 */
	@Override
	public void deleteUser(int id) {
		//Hibernate session
		Session s = null;
		//Hibernate Transaction
		Transaction tx = null;
		try {
			s = SortifySessionFactory.getSession();
			tx = s.beginTransaction();
			User user = s.get(User.class, id);
			if(user != null) {
				s.delete(user);
			}
			tx.commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}	
	}

}
