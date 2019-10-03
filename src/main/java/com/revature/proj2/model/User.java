package com.revature.proj2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Revature Proj2: Sortify
 * Package: Model
 * User.java
 * Java Bean
 * Purpose: Storage and access of Spotify user properties
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/01/2019
 */

@Entity
@Table(name="sortify.users")
public class User {
	//Private variables
	@Id @Column(name="spotify_user_id")
	private String spotifyUserId;
	@Column(name="spotify_user_uri")
	private String spotifyUserUri;
	@Column(name="spotify_display_name")
	private String spotifyDisplayName;
	@Column(name="spotify_email")
	private String spotifyEmail;
	
	/**
	 * Default no arg Constructor
	 */
	public User() {}
	
	/**
	 * Loaded Constructor
	 * 
	 * @param spotifyUserId String Spotify user id
	 * @param spotifyUserUri String Spotify user URI
	 * @param spotifyDisplayName String Spotify user diplay name
	 * @param spotifyEmail Spotify user email
	 */
	public User(String spotifyUserId, String spotifyUserUri, 
			String spotifyDisplayName, String spotifyEmail) {
		super();
		this.spotifyUserId = spotifyUserId;
		this.spotifyUserUri = spotifyUserUri;
		this.spotifyDisplayName = spotifyDisplayName;
		this.spotifyEmail = spotifyEmail;
	}

	/**
	 * Get Spotify user id
	 * 
	 * @return spotifyUserId String Spotify user id
	 */
	public String getSpotifyUserId() {
		return spotifyUserId;
	}

	/**
	 * Set Spotify user id
	 * 
	 * @param spotifyUserId String Spotify user id
	 */
	public void setSpotifyUserId(String spotifyUserId) {
		this.spotifyUserId = spotifyUserId;
	}

	/**
	 * Get Spotify user URI
	 * 
	 * @return spotifyUserUri String Spotify user URI
	 */
	public String getSpotifyUserUri() {
		return spotifyUserUri;
	}

	/**
	 * Set Spotify user URI
	 * 
	 * @param spotifyUserUri String Spotify user URI
	 */
	public void setSpotifyUserUri(String spotifyUserUri) {
		this.spotifyUserUri = spotifyUserUri;
	}

	/**
	 * Get Spotify user display name
	 * 
	 * @return spotifyDisplayName String Spotify user display name
	 */
	public String getSpotifyDisplayName() {
		return spotifyDisplayName;
	}

	/**
	 * Set Spotify user display name
	 * 
	 * @param spotifyDisplayName String Spotify user display name
	 */
	public void setSpotifyDisplayName(String spotifyDisplayName) {
		this.spotifyDisplayName = spotifyDisplayName;
	}

	/**
	 * Get Spotify user email
	 * 
	 * @return spotifyEmail String Spotify user email
	 */
	public String getSpotifyEmail() {
		return spotifyEmail;
	}

	/**
	 * Set Spotify user email
	 * 
	 * @param spotifyEmail String Spotify user email
	 */
	public void setSpotifyEmail(String spotifyEmail) {
		this.spotifyEmail = spotifyEmail;
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
		result = prime * result + ((spotifyDisplayName == null) ? 0 : spotifyDisplayName.hashCode());
		result = prime * result + ((spotifyEmail == null) ? 0 : spotifyEmail.hashCode());
		result = prime * result + ((spotifyUserId == null) ? 0 : spotifyUserId.hashCode());
		result = prime * result + ((spotifyUserUri == null) ? 0 : spotifyUserUri.hashCode());
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
		User other = (User) obj;
		if (spotifyDisplayName == null) {
			if (other.spotifyDisplayName != null)
				return false;
		} else if (!spotifyDisplayName.equals(other.spotifyDisplayName))
			return false;
		if (spotifyEmail == null) {
			if (other.spotifyEmail != null)
				return false;
		} else if (!spotifyEmail.equals(other.spotifyEmail))
			return false;
		if (spotifyUserId == null) {
			if (other.spotifyUserId != null)
				return false;
		} else if (!spotifyUserId.equals(other.spotifyUserId))
			return false;
		if (spotifyUserUri == null) {
			if (other.spotifyUserUri != null)
				return false;
		} else if (!spotifyUserUri.equals(other.spotifyUserUri))
			return false;
		return true;
	}

	/**
	 * Get the String representation of the User
	 * 
	 * @return String representation of the User
	 */
	@Override
	public String toString() {
		return "User [" 
				+ "\nspotifyUserId = " + spotifyUserId 
				+ "\nspotifyUserUri = " + spotifyUserUri 
				+ "\nspotifyDisplayName = " + spotifyDisplayName 
				+ "\nspotifyEmail = " + spotifyEmail 
				+ "\n]";
	}
}
