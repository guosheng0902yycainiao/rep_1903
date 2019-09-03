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
	 * ͨ���û��������û�
	 * @param username
	 * @return User
	 */
	@Select("select id,username,password"
			+ " from sh_user where username=#{username}")
	User getByUserName(@Param("username")String username);
	
	/**
	 * ע��
	 * ����û�
	 * @param user
	 * @return
	 */
	@Insert("insert into sh_user(username,password) "
			+ " values(#{username},#{password})")
	void save(User user);
	
	/**
	 * �����û�����ѯ�û�id
	 * @param username
	 * @return
	 */
	@Select("select id "
			+ " from sh_user "
			+ " where username=#{username}")
	Integer queryById(@Param("username")String username);
	
	/**
	 * ����id��ѯ�û�
	 * @param id
	 * @return
	 */
	@Select("select id,username,password "
			+ " from sh_user "
			+ " where id=#{id}")
	User queryUser(int id);
	
	/**
	 * ͨ���û������Ҹ��û����еĽ�ɫ��������set������
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
	 * ͨ���û������Ҹ��û����е�Ȩ�޲�������set������  distinctȥ��
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
	 * �����û�����ѯ�û�����Ȩ��
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
	 * �����û����޸��û�����
	 * @param user
	 * @return
	 */
	@Update("update sh_user "
			+ " set password=#{password} "
			+ " where username=#{username}")
	int updateUserPass(User user);
	
	/**
	 * ����idɾ���û�
	 * @param id
	 * @return
	 */
	@Delete("delete "
			+ " from sh_user "
			+ " where id=#{id}")
	int delById(int id);

	/**
	 * �����û���ɾ���û�
	 * @param username
	 * @return
	 */
	@Delete("delete "
			+ " from sh_user "
			+ " where username=#{username}")
	int delByUserName(String username);
}
