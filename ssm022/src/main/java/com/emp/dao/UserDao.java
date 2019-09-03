package com.emp.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.emp.entity.User;

public interface UserDao {
	/**
	 * 通过用户名查找用户
	 * @param username
	 * @return User
	 */
	@Select("select id,username,password"
			+ " from sh_user where username=#{username}")
	User getByUserName(@Param("username")String username);
	
	/**
	 * 注册
	 * 添加用户
	 * @param user
	 * @return
	 */
	@Insert("insert into sh_user(username,password) "
			+ " values(#{username},#{password})")
	void save(User user);
	
	/**
	 * 根据用户名查询用户id
	 * @param username
	 * @return
	 */
	@Select("select id "
			+ " from sh_user "
			+ " where username=#{username}")
	Integer queryById(@Param("username")String username);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	@Select("select id,username,password "
			+ " from sh_user "
			+ " where id=#{id}")
	User queryUser(int id);
	
	/**
	 * 通过用户名查找该用户所有的角色并保存在set集合中
	 * @param username
	 * @return Set<String>
	 */
	@Select("select r.rolename "
			+ " from sh_user u inner join sh_user_role ur "
			+ " on u.id=ur.user_id "
			+ " inner join sh_role r"
			+ " on ur.role_id=r.id"
			+ " where u.username=#{username}")
	Set<String> getRoles(@Param("username")String username);
	
	/**
	 * 通过用户名查找该用户所有的权限并保存在set集合中  distinct去重
	 * @param username   
	 * @return Set<String>
	 */
	@Select("select DISTINCT p.permission_name "
			+ " from sh_user u inner join sh_user_role ur "
			+ " on u.id=ur.user_id "
			+ " inner join sh_role r"
			+ " on ur.role_id=r.id "
			+ " inner join sh_role_permission rp "
			+ " on r.id=rp.role_id "
			+ " inner join sh_permissions p "
			+ " on rp.permission_id=p.id "
			+ " where u.username=#{username}")
	Set<String> getPermission(@Param("username")String username);
	
	/**
	 * 根据用户名查询用户所有权限
	 * @param username
	 * @return
	 */
	@Select(" select p.permission_name "
			+ " from sh_user u inner join sh_user_role ur "
			+ " on u.id=ur.user_id "
			+ " inner join sh_role r "
			+ " on ur.role_id=r.id "
			+ " inner join sh_role_permission rp "
			+ " on r.id=rp.role_id "
			+ " inner join sh_permissions p "
			+ " on rp.permission_id=p.id "
			+ " where u.username=#{username}")
	Set<String> queryPermissions(@Param("username")String username);
	/**
	 * 根据用户名修改用户密码
	 * @param user
	 * @return
	 */
	@Update("update sh_user "
			+ " set password=#{password} "
			+ " where username=#{username}")
	int updateUserPass(User user);
	
	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
	@Delete("delete "
			+ " from sh_user "
			+ " where id=#{id}")
	int delById(int id);

	/**
	 * 根据用户名删除用户
	 * @param username
	 * @return
	 */
	@Delete("delete "
			+ " from sh_user "
			+ " where username=#{username}")
	int delByUserName(String username);
}
