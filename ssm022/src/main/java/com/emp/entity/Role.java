package com.emp.entity;

/**
 * 
 * @author me 
 * 角色实体类
 *
 */
public class Role {

	private Integer id;
	private String rolename;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Role(Integer id, String rolename) {
		super();
		this.id = id;
		this.rolename = rolename;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + "]";
	}

}
