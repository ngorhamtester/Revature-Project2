package com.revature.proj2.model;

/**
 * Revature Proj2: Sortify
 * Package: Model
 * TokensRequest.java
 * Java Bean
 * Purpose: Storage and access of Spotify Tokens request properties
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/10/2019
 */

public class TokensRequest {
	//Private variables
	private String grantType;
	private String code;
	private String redirectUri;
	
	/**
	 * Loaded Constructor
	 * 
	 * @param grantType String Spotify authorization type
	 * @param code String Spotify authorization code
	 * @param redirectUri String Spotify redirect with tokens json
	 */
	public TokensRequest(String grantType, String code, String redirectUri) {
		super();
		this.grantType = grantType;
		this.code = code;
		this.redirectUri = redirectUri;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((grantType == null) ? 0 : grantType.hashCode());
		result = prime * result + ((redirectUri == null) ? 0 : redirectUri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokensRequest other = (TokensRequest) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (grantType == null) {
			if (other.grantType != null)
				return false;
		} else if (!grantType.equals(other.grantType))
			return false;
		if (redirectUri == null) {
			if (other.redirectUri != null)
				return false;
		} else if (!redirectUri.equals(other.redirectUri))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "grant_type=" + grantType + "&code=" + code + "&redirect_uri=" + redirectUri;
	}
}
