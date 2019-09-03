package com.emp.service;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.emp.entity.User;

public interface UserService {
	/**
	 * 通过用户名查找用户
	 * 
	 * @param username
	 * @return User
	 */
	User getByUserName(String username);

	/**
	 * 通过用户名查找该用户所有的角色并保存在set集合中
	 * 
	 * @param username
	 * @return Set<String>
	 */
	Set<String> getRoles(String username);

	/**
	 * 通过用户名查找该用户所有的权限并保存在set集合中
	 * 
	 * @param username
	 * @return Set<String>
	 */
	Set<String> getPermission(String username);
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	void save(User user);
	/**
	 * 根据用户名查询id
	 * @param username
	 * @return
	 */
	Integer queryById(@Param("username")String username);

}
