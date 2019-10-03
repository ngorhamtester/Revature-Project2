package com.revature.proj2.repository;

import java.util.List;

import com.revature.proj2.model.Playlist;

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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get User Playlist by URI
	 * 
	 * @param uri URI Spotify Playlist URI
	 * @return playlist Playlist User playlist
	 */
	@Override
	public Playlist getPlaylistByUri(String uri) {
		// TODO Auto-generated method stub
		return null;
	}

	//UPDATE METHODS
		/**
		 * Update User Playlist
		 * 
		 * @param playlist Playlist User playlist
		 */
	@Override
	public void updatePlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		
	}

	//DELETE METHODS
	/**
	 * Delete User Playlist
	 * 
	 * @param playlist Playlist User playlist
	 */
	@Override
	public void deletePlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		
	}

}
