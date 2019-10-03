package com.revature.proj2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Revature Proj2: Sortify
 * Package: Model
 * Playlist.java
 * Java Bean
 * Purpose: Storage and access of Spotify user's playlist properties
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/01/2019
 */

@Entity
@Table(name="sortify.playlists")
public class Playlist {
	//Private variables
	@Id @Column(name="spotify_list_id")
	private String spotifyPlaylistId;
	@Column(name="spotify_playlist_name")
	private String spotifyPlaylistName;
	@Column(name="spotify_playlist_uri")
	private String spotifyPlaylistUri;
	
	/**
	 * Default no arg Constructor
	 */
	public Playlist() {}
	
	/**
	 * Loaded Constructor
	 * 
	 * @param spotifyPlaylistId String Spotify playlist id
	 * @param spotifyPlaylistName String Spotify playlist name
	 * @param spotifyPlaylistUri String Spotify playlist URI
	 */
	public Playlist(String spotifyPlaylistId, String spotifyPlaylistName, 
			String spotifyPlaylistUri) {
		super();
		this.spotifyPlaylistId = spotifyPlaylistId;
		this.spotifyPlaylistName = spotifyPlaylistName;
		this.spotifyPlaylistUri = spotifyPlaylistUri;
	}

	/**
	 * Get Spotify playlist id
	 * 
	 * @return spotifyPlaylistId String Spotify playlist id
	 */
	public String getSpotifyPlaylistId() {
		return spotifyPlaylistId;
	}

	/**
	 * Set Spotify playlist id
	 * 
	 * @param spotifyPlaylistId String Spotify playlist id
	 */
	public void setSpotifyPlaylistId(String spotifyPlaylistId) {
		this.spotifyPlaylistId = spotifyPlaylistId;
	}

	/**
	 * Get Spotify playlist name
	 * 
	 * @return spotifyPlaylistName String Spotify playlist name
	 */
	public String getSpotifyPlaylistName() {
		return spotifyPlaylistName;
	}

	/**
	 * Set Spotify playlist name
	 * 
	 * @param spotifyPlaylistName String Spotify playlist name
	 */
	public void setSpotifyPlaylistName(String spotifyPlaylistName) {
		this.spotifyPlaylistName = spotifyPlaylistName;
	}

	/**
	 * Get Spotify playlist URI
	 * 
	 * @return spotifyPlaylistURI String Spotify playlist URI
	 */
	public String getSpotifyPlaylistUri() {
		return spotifyPlaylistUri;
	}

	/**
	 * Set Spotify playlist URI
	 * 
	 * @param spotifyPlaylistUri String Spotify playlist URI
	 */
	public void setSpotifyPlaylistUri(String spotifyPlaylistUri) {
		this.spotifyPlaylistUri = spotifyPlaylistUri;
	}

	/**
	 * Calculate unique has code
	 * 
	 * @return result int unique has code calculation
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((spotifyPlaylistId == null) ? 0 : spotifyPlaylistId.hashCode());
		result = prime * result + ((spotifyPlaylistName == null) ? 0 : spotifyPlaylistName.hashCode());
		result = prime * result + ((spotifyPlaylistUri == null) ? 0 : spotifyPlaylistUri.hashCode());
		return result;
	}

	/**
	 * Compare two User objects for equality
	 * 
	 * @return boolean of the comparison
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Playlist other = (Playlist) obj;
		if (spotifyPlaylistId == null) {
			if (other.spotifyPlaylistId != null)
				return false;
		} else if (!spotifyPlaylistId.equals(other.spotifyPlaylistId))
			return false;
		if (spotifyPlaylistName == null) {
			if (other.spotifyPlaylistName != null)
				return false;
		} else if (!spotifyPlaylistName.equals(other.spotifyPlaylistName))
			return false;
		if (spotifyPlaylistUri == null) {
			if (other.spotifyPlaylistUri != null)
				return false;
		} else if (!spotifyPlaylistUri.equals(other.spotifyPlaylistUri))
			return false;
		return true;
	}

	/**
	 * Get the String representation of the Playlist
	 * 
	 * @return String representation of the Playlist
	 */
	@Override
	public String toString() {
		return "Playlist [" 
				+ "\nspotifyPlaylistId = " + spotifyPlaylistId 
				+ "\nspotifyPlaylistName = " + spotifyPlaylistName
				+ "\nspotifyPlaylistUri = " + spotifyPlaylistUri 
				+ "\n]";
	}
}
