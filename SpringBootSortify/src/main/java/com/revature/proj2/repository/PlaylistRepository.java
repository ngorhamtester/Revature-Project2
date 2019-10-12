package com.revature.proj2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

@Repository(value="playlistRepository")
public interface PlaylistRepository extends JpaRepository<Playlist, String>{
	//CREATE METHODS
	/**
	 * Insert new Playlist into DB
	 * 
	 * @param playlist Playlist Spotify playlist
	 */
	<S extends Playlist>S save(Playlist playlist);
	
	//READ METHODS
	/**
	 * Get all User Playlists by Spotify User id
	 * 
	 * @return playlists List list of User playlists
	 */
	List<Playlist> findAllBySpotifyPlaylistId(String id);
	
	/**
	 * Get User Playlist by Spotify User id
	 * 
	 * @param id String Spotify playlist id
	 * @return playlist Playlist User playlist
	 */
	Playlist findBySpotifyPlaylistId(String id);
	
	//UPDATE METHODS
	/**
	 * Update User Playlist
	 * 
	 * @param playlist Playlist User playlist
	 */
	//void updatePlaylist(Playlist playlist);
	
	//DELETE METHODS
	/**
	 * Delete User Playlist
	 * 
	 * @param playlist Playlist User playlist to be deleted
	 */
	void delete(Playlist playlist);
}
