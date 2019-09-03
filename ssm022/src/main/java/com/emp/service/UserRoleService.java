package com.emp.service;

import java.util.Set;

import com.emp.entity.UserRole;

public interface UserRoleService {
	/**
	 * 根据用户id查询所有用户角色id
	 * @param user_id
	 * @return
	 */
	Set<String> queryByUserRole(int user_id);
	
	/**
	 * 根据用户id查询用户角色名称
	 * @param user_id
	 * @return
	 */
	Set<String> queryByRole(int user_id);
	
	/**
	 * 根据用户id添加用户角色(普通用户)
	 * @param user_id
	 * @return
	 */
	int addUserRole(int user_id);
	
	/**
	 * 根据用户id修改用户角色id
	 * @param userRole
	 * @return
	 */
	int updateRole(UserRole userRole);
	
	/**
	 * 根据用户id删除用户角色
	 * @param user_id
	 * @return
	 */
	int delUserRole(int user_id);

}
