package com.emp.entity;

/**
 * 
 * @author me 
 * 用户角色实体类
 * 
 */
public class UserRole {

	private Integer user_id;
	private Integer role_id;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public UserRole(Integer user_id, Integer role_id) {
		super();
		this.user_id = user_id;
		this.role_id = role_id;
	}

	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserRole [user_id=" + user_id + ", role_id=" + role_id + "]";
	}

}
