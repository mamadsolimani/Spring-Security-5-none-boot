package ir.com.domain.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Permission implements GrantedAuthority {
	
	// because just see pages
	ADMIN,
	USER,
	// because just see pages
	
	CREATE_USER,
	EDIT_USER,
	REMOVE_USER,
	GET_USER,
	SEE_USERS,
	LOCK_USER,;

	@Override
	public String getAuthority() {
		return this.name();
	}
	
}
