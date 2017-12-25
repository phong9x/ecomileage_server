package org.ecomileage.bean;

public class UserItem {
	
	private Integer 	id;
	private String 		username;
	private Integer 	roleId;
	private Integer 	roleName;
	private long        expiration;
	
	
	public long getExpiration() {
		return expiration;
	}

	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleName() {
		return roleName;
	}

	public void setRoleName(Integer roleName) {
		this.roleName = roleName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
