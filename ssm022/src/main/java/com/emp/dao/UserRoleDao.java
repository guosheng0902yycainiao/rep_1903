package com.emp.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.emp.entity.UserRole;

public interface UserRoleDao {
	
	/**
	 * �����û�id��ѯ�����û���ɫid
	 * @param user_id
	 * @return
	 */
	@Select("select user_id,role_id "
			+ " from sh_user_role "
			+ " where user_id=#{user_id}")
	Set<String> queryByUserRole(int user_id);
	
	/**
	 * �����û�id��ѯ�û���ɫ����
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
	 * �����û�id����û���ɫ(��ͨ�û�)
	 * @param user_id
	 * @return
	 */
	@Insert("insert into sh_user_role "
			+ " values(#{user_id},1)")
	int addUserRole(int user_id);
	
	/**
	 * �����û�id�޸��û���ɫid
	 * @param userRole
	 * @return
	 */
	@Update("update sh_user_role "
			+ " set role_id=#{role_id} "
			+ " where user_id=#{user_id}")
	int updateRole(UserRole userRole);
	
	/**
	 * �����û�idɾ���û���ɫ
	 * @param user_id
	 * @return
	 */
	@Delete("delete from sh_user_role "
			+ " where user_id = #{user_id}")
	int delUserRole(int user_id);

}
