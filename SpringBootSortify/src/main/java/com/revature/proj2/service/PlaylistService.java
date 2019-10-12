package com.revature.proj2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.proj2.model.Playlist;
import com.revature.proj2.repository.PlaylistRepository;

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

@Service(value="playlistService")
public class PlaylistService {
	//Private variables
	private PlaylistRepository playlistRepository;
	
	//Spring setting injection
	@Autowired
	public void setPlaylistRepository(PlaylistRepository playlistRepository) {
		this.playlistRepository = playlistRepository;
	}
	
	//CREATE METHODS
	/**
	 * Insert new Playlist into DB
	 * 
	 * @param playlist Playlist Spotify playlist
	 */
	public void insertPlaylist(Playlist playlist) {
		playlistRepository.save(playlist);
	}
	
	//READ METHODS
	/**
	 * Get all User Playlists by Spotify User id
	 * 
	 * @return playlists List list of User playlists
	 */
	public List<Playlist> getAllPlaylistsById(String id) {
		return playlistRepository.findAllBySpotifyPlaylistId(id);
	}
	
	/**
	 * Get User Playlist by id
	 * 
	 * @param id String Spotify playlist id
	 * @return playlist Playlist User playlist
	 */
	public Playlist getPlaylistById(String id) {
		return playlistRepository.findBySpotifyPlaylistId(id);
	}
	
	//UPDATE METHODS
	/**
	 * Update User Playlist
	 * 
	 * @param playlist Playlist User playlist
	 */
	public void updatePlaylist(Playlist playlist) {
		playlistRepository.save(playlist);
	}
	
	//DELETE METHODS
	/**
	 * Delete User Playlist
	 * 
	 * @param playlist Playlist User playlist to be deleted
	 */
	public void deletePlaylist(Playlist playlist) {
		playlistRepository.delete(playlist);
	}
}
