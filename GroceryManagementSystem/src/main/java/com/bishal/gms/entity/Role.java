package com.bishal.gms.entity;

import java.util.Set;

public enum Role {
	ADMIN(Set.of(Permissions.PRODUCT_READ,Permissions.PRODUCT_WRITE,Permissions.PRODUCT_DELETE)),
	CUSTOMER(Set.of(Permissions.PRODUCT_READ)),
	STOREMANAGER(Set.of(Permissions.PRODUCT_READ,Permissions.PRODUCT_WRITE));
	
	private final Set<Permissions> permissions;

	private Role(Set<Permissions> permissions) {
		this.permissions = permissions;
	}

	public Set<Permissions> getPermissions() {
		return permissions;
	}
	
}
