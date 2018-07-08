package org.sobakaisti.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

/**
* ENUM wrapper class
*/
public class Authority implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;
	
	private Role role;
	
	public Authority(Role role) {
		this.role = role;
	}	

	public enum Role {
		ROLE_ADMIN(Permission.values()),
		ROLE_EDITOR(Permission.READ_POST, Permission.WRITE_POST, Permission.EDIT_POST, Permission.DELETE_POST),
		ROLE_WRITER(Permission.READ_POST, Permission.WRITE_POST, Permission.EDIT_POST),		
		ROLE_ANONYMOUS;
		
		private Permission[] permissions;
		private Role(Permission... permissions) {
			this.permissions = permissions;
		}
		
		public Permission[] getPermissions() {
			return permissions;
		}	
		
		public List<Permission> getPermissionList() {			
			return Arrays.asList(this.permissions);
		}
		
		public List<String> getPermissionNameList() {
			List<String> permissions = null;
			if(this.permissions != null && this.permissions.length > 0) {
				permissions = new ArrayList<>(this.permissions.length);
				for(int i=0; i<this.permissions.length; i++)
					permissions.add(this.permissions[i].name());
			}
			return permissions;
		}
	}
	
	
	public enum Permission {
		READ_POST, WRITE_POST, EDIT_POST, DELETE_POST,
		LEAVE_COMMENT;
	}


	@Override
	public String getAuthority() {
		return this.role.name();
	}
	
	

}
