package com.emp.entity;

/**
 * 
 * @author me 
 * 角色权限实体类
 *
 */
public class RolePermission {

	private Integer role_id;
	private Integer permission_id;

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public Integer getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(Integer permission_id) {
		this.permission_id = permission_id;
	}

	public RolePermission(Integer role_id, Integer permission_id) {
		super();
		this.role_id = role_id;
		this.permission_id = permission_id;
	}

	public RolePermission() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "RolePermission [role_id=" + role_id + ", permission_id=" + permission_id + "]";
	}

}
