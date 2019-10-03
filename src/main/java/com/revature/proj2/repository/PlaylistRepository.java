package com.revature.proj2.repository;

import java.util.List;

import com.revature.proj2.model.Playlist;

/**
 * Revature Proj2: Sortify
 * Package: Repository
 * PlaylistRepository.java
 * Interface
 * Purpose: Declares Data Access Object methods to be defined
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/02/2019
 */

public interface PlaylistRepository {
	//CREATE METHODS
	/**
	 * Insert new Playlist into DB
	 * 
	 * @param playlist Playlist Spotify playlist
	 */
	void insertPlaylist(Playlist playlist);
	
	//READ METHODS
	/**
	 * Get all User Playlists
	 * 
	 * @return playlists List list of User playlists
	 */
	List<Playlist> getAllPlaylists();
	
	/**
	 * Get User Playlist by id
	 * 
	 * @param id String Spotify playlist id
	 * @return playlist Playlist User playlist
	 */
	Playlist getPlaylistById(String id);
	
	/**
	 * Get User Playlist by URI
	 * 
	 * @param uri URI Spotify Playlist URI
	 * @return playlist Playlist User playlist
	 */
	Playlist getPlaylistByUri(String uri);
	
	//UPDATE METHODS
	/**
	 * Update User Playlist
	 * 
	 * @param playlist Playlist User playlist
	 */
	void updatePlaylist(Playlist playlist);
	
	//DELETE METHODS
	/**
	 * Delete User Playlist
	 * 
	 * @param id int playlist id
	 */
	void deletePlaylist(int id);
}
