package com.revature.proj2.model;

/**
 * Revature Proj2: Sortify
 * Package: Model
 * TokensResponse.java
 * Java Bean
 * Purpose: Storage and access of Spotify Tokens response properties
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/10/2019
 */

public class TokensResponse {
	//Private variables
	private String access_token;
	private String token_type;
	private String scope;
	private int expires_in;
	private String refresh_token;
	
	/**
	 * Default no arg Constructor
	 */
	public TokensResponse() {}
	
	/**
	 * Loaded Constructor
	 * 
	 * @param access_token
	 * @param token_type
	 * @param scope
	 * @param expires_in
	 * @param refresh_token
	 */
	public TokensResponse(String access_token, String token_type, String scope, int expires_in, String refresh_token) {
		super();
		this.access_token = access_token;
		this.token_type = token_type;
		this.scope = scope;
		this.expires_in = expires_in;
		this.refresh_token = refresh_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((access_token == null) ? 0 : access_token.hashCode());
		result = prime * result + expires_in;
		result = prime * result + ((refresh_token == null) ? 0 : refresh_token.hashCode());
		result = prime * result + ((scope == null) ? 0 : scope.hashCode());
		result = prime * result + ((token_type == null) ? 0 : token_type.hashCode());
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
		TokensResponse other = (TokensResponse) obj;
		if (access_token == null) {
			if (other.access_token != null)
				return false;
		} else if (!access_token.equals(other.access_token))
			return false;
		if (expires_in != other.expires_in)
			return false;
		if (refresh_token == null) {
			if (other.refresh_token != null)
				return false;
		} else if (!refresh_token.equals(other.refresh_token))
			return false;
		if (scope == null) {
			if (other.scope != null)
				return false;
		} else if (!scope.equals(other.scope))
			return false;
		if (token_type == null) {
			if (other.token_type != null)
				return false;
		} else if (!token_type.equals(other.token_type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TokensResponse [access_token=" + access_token + ", token_type=" + token_type + ", scope=" + scope
				+ ", expires_in=" + expires_in + ", refresh_token=" + refresh_token + "]";
	}
}
