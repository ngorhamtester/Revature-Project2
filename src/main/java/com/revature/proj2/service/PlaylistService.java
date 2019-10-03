package com.revature.proj2.service;

import java.util.List;

import com.revature.proj2.model.Playlist;
import com.revature.proj2.repository.PlaylistRepository;
import com.revature.proj2.repository.PlaylistRepositoryImpl;

/**
 * Revature Proj2: Sortify
 * Package: Service
 * PlaylistService.java
 * Service
 * Purpose: Playlist DAO service
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/03/2019
 */

public class PlaylistService {
	//Private variables
	private PlaylistRepository playlistRepository = new PlaylistRepositoryImpl();
	
	//CREATE METHODS
	/**
	 * Insert new Playlist into DB
	 * 
	 * @param playlist Playlist Spotify playlist
	 */
	void insertPlaylist(Playlist playlist) {
		playlistRepository.insertPlaylist(playlist);
	}
	
	//READ METHODS
	/**
	 * Get all User Playlists
	 * 
	 * @return playlists List list of User playlists
	 */
	List<Playlist> getAllPlaylists() {
		return playlistRepository.getAllPlaylists();
	}
	
	/**
	 * Get User Playlist by id
	 * 
	 * @param id String Spotify playlist id
	 * @return playlist Playlist User playlist
	 */
	Playlist getPlaylistById(String id) {
		return playlistRepository.getPlaylistById(id);
	}
	
	/**
	 * Get User Playlist by URI
	 * 
	 * @param uri URI Spotify Playlist URI
	 * @return playlist Playlist User playlist
	 */
	Playlist getPlaylistByUri(String uri) {
		return playlistRepository.getPlaylistByUri(uri);
	}
	
	//UPDATE METHODS
	/**
	 * Update User Playlist
	 * 
	 * @param playlist Playlist User playlist
	 */
	void updatePlaylist(Playlist playlist) {
		playlistRepository.updatePlaylist(playlist);
	}
	
	//DELETE METHODS
	/**
	 * Delete User Playlist
	 * 
	 * @param id int playlist id
	 */
	void deletePlaylist(int id) {
		playlistRepository.deletePlaylist(id);
	}
}
