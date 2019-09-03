package com.emp.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.emp.entity.UserRole;

public interface UserRoleDao {
	
	/**
	 * 根据用户id查询所有用户角色id
	 * @param user_id
	 * @return
	 */
	@Select("select user_id,role_id "
			+ " from sh_user_role "
			+ " where user_id=#{user_id}")
	Set<String> queryByUserRole(int user_id);
	
	/**
	 * 根据用户id查询用户角色名称
	 * @param user_id
	 * @return
	 */
	@Select("select r.rolename "
			+ " from sh_user_role as ur "
			+ " inner join sh_role as r "
			+ " on ur.role_id=r.id "
			+ " where ur.user_id=#{user_id}")
	Set<String> queryByRole(int user_id);
	
	/**
	 * 根据用户id添加用户角色(普通用户)
	 * @param user_id
	 * @return
	 */
	@Insert("insert into sh_user_role "
			+ " values(#{user_id},1)")
	int addUserRole(int user_id);
	
	/**
	 * 根据用户id修改用户角色id
	 * @param userRole
	 * @return
	 */
	@Update("update sh_user_role "
			+ " set role_id=#{role_id} "
			+ " where user_id=#{user_id}")
	int updateRole(UserRole userRole);
	
	/**
	 * 根据用户id删除用户角色
	 * @param user_id
	 * @return
	 */
	@Delete("delete from sh_user_role "
			+ " where user_id = #{user_id}")
	int delUserRole(int user_id);

}
