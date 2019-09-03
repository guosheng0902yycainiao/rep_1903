package com.emp.entity;

/**
 * 
 * @author me 
 * 权限实体类
 *
 */
public class Permissions {

	private Integer id;
	private String perminssion_name;

	public Permissions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Permissions(Integer id, String perminssion_name) {
		super();
		this.id = id;
		this.perminssion_name = perminssion_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPerminssion_name() {
		return perminssion_name;
	}

	public void setPerminssion_name(String perminssion_name) {
		this.perminssion_name = perminssion_name;
	}

	@Override
	public String toString() {
		return "Permissions [id=" + id + ", perminssion_name=" + perminssion_name + "]";
	}

}
