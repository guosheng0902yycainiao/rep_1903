package com.emp.service;

import java.util.Set;

import com.emp.entity.UserRole;

public interface UserRoleService {
	/**
	 * �����û�id��ѯ�����û���ɫid
	 * @param user_id
	 * @return
	 */
	Set<String> queryByUserRole(int user_id);
	
	/**
	 * �����û�id��ѯ�û���ɫ����
	 * @param user_id
	 * @return
	 */
	Set<String> queryByRole(int user_id);
	
	/**
	 * �����û�id����û���ɫ(��ͨ�û�)
	 * @param user_id
	 * @return
	 */
	int addUserRole(int user_id);
	
	/**
	 * �����û�id�޸��û���ɫid
	 * @param userRole
	 * @return
	 */
	int updateRole(UserRole userRole);
	
	/**
	 * �����û�idɾ���û���ɫ
	 * @param user_id
	 * @return
	 */
	int delUserRole(int user_id);

}
