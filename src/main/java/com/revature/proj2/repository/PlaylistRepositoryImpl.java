package com.revature.proj2.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.revature.proj2.model.Playlist;
import com.revature.proj2.util.SortifySessionFactory;

/**
 * Revature Proj2: Sortify
 * Package: Repository
 * PlaylistRepositoryImpl.java
 * Implementation
 * Purpose: Declares Data Access Object
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/02/2019
 */

public class PlaylistRepositoryImpl implements PlaylistRepository {
	//CREATE METHODS
	/**
	 * Insert new Playlist into DB
	 * 
	 * @param playlist Playlist Spotify playlist
	 */
	@Override
	public void insertPlaylist(Playlist playlist) {
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
			s.save(playlist);
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
	 * Get all User Playlists
	 * 
	 * @return playlists List list of User playlists
	 */
	@Override
	public List<Playlist> getAllPlaylists() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get User Playlist by id
	 * 
	 * @param id String Spotify playlist id
	 * @return playlist Playlist User playlist
	 */
	@Override
	public Playlist getPlaylistById(String id) {
		Playlist playlist = null;
		//Hibernate session
		Session s = null;
		//Hibernate Transaction
		Transaction tx = null;
		try {
			s = SortifySessionFactory.getSession();
			tx = s.beginTransaction();
			playlist = s.get(Playlist.class, id);
			tx.commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}
		return playlist;
	}

	/**
	 * Get User Playlist by URI
	 * 
	 * @param uri URI Spotify Playlist URI
	 * @return playlist Playlist User playlist
	 */
	@Override
	public Playlist getPlaylistByUri(String uri) {
		Playlist playlist = null;
		//Hibernate session
		Session s = null;
		//Hibernate Transaction
		Transaction tx = null;
		try {
			s = SortifySessionFactory.getSession();
			tx = s.beginTransaction();
			playlist = s.get(Playlist.class, uri);
			tx.commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}
		return playlist;
	}

	//UPDATE METHODS
		/**
		 * Update User Playlist
		 * 
		 * @param playlist Playlist User playlist
		 */
	@Override
	public void updatePlaylist(Playlist playlist) {
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
			s.merge(playlist);
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
	 * Delete User Playlist
	 * 
	 * @param id int playlist id
	 */
	@Override
	public void deletePlaylist(int id) {
		//Hibernate session
		Session s = null;
		//Hibernate Transaction
		Transaction tx = null;
		try {
			s = SortifySessionFactory.getSession();
			tx = s.beginTransaction();
			Playlist playlist = s.get(Playlist.class, id);
			if(playlist != null) {
				s.delete(playlist);
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
