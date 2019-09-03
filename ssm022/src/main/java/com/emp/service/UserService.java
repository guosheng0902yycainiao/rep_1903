package com.emp.service;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.emp.entity.User;

public interface UserService {
	/**
	 * ͨ���û��������û�
	 * 
	 * @param username
	 * @return User
	 */
	User getByUserName(String username);

	/**
	 * ͨ���û������Ҹ��û����еĽ�ɫ��������set������
	 * 
	 * @param username
	 * @return Set<String>
	 */
	Set<String> getRoles(String username);

	/**
	 * ͨ���û������Ҹ��û����е�Ȩ�޲�������set������
	 * 
	 * @param username
	 * @return Set<String>
	 */
	Set<String> getPermission(String username);
	
	/**
	 * ע��
	 * @param user
	 * @return
	 */
	void save(User user);
	/**
	 * �����û�����ѯid
	 * @param username
	 * @return
	 */
	Integer queryById(@Param("username")String username);

}
