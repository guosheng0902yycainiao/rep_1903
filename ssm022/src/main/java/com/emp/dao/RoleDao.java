package com.emp.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.emp.entity.Role;

public interface RoleDao {
	
	/**
	 * �����û���ɫ
	 * @param role
	 * @return
	 */
	@Insert("insert into sh_role "
			+ " values(#{id},#{rolename})")
	int addRole(Role role);
	
	/**
	 * ����idɾ����ɫ
	 * @param id
	 * @return
	 */
	@Delete("delete from sh_role "
			+ " where id=#{id}")
	int delRole(int id);
	
	/**
	 * ����id�޸Ľ�ɫ
	 * @param role
	 * @return
	 */
	@Update("update sh_role "
			+ " set rolename=#{rolename} "
			+ " where id=#{id}")
	int updateRole(Role role);
	
	/**
	 * ����id��ѯ��ɫ
	 * @param id
	 * @return
	 */
	@Select("select id,rolename "
			+ " from sh_role "
			+ " where id=#{id}")
	Role queryById(int id);

}
