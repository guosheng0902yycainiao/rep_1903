package com.emp.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import com.emp.dao.UserDao;
import com.emp.entity.User;
import com.emp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	/**
	 * ע��Dao����
	 */
	@Resource
	private UserDao userDao;
    
	/**
	 * ͨ���û��������û�
	 */
	@Override
	public User getByUserName(String username) {
		return userDao.getByUserName(username);
	}
    
	/**
	 * ͨ���û������Ҹ��û����еĽ�ɫ��������set������
	 */
	@Override
	public Set<String> getRoles(String username) {
		return userDao.getRoles(username);
	}

	/**
	 * ͨ���û������Ҹ��û����е�Ȩ�޲�������set������
	 */
	@Override
	public Set<String> getPermission(String username) {
		return userDao.getPermission(username);
	}

	@Override
	public void save(User user) {
		//����,���ܵ�Ч��
		//���ü��ܷ���MD5
		//��������  "MD5"�㷨����   user.getPassword() ��Ҫ���ܵ�����+ user.getUsername()���û���Ϊ����  ����1024��
        String password 
        = new SimpleHash("MD5", user.getPassword(), user.getUsername(), 1024).toString();
		//password���Ǽ��ܺ������
        //�ü��ܺ�������û�ԭ����ҳ�洫��������
        user.setPassword(password);
        //��user���浽���ݿ�
        userDao.save(user);
	}

	@Override
	public Integer queryById(String username) {
		return userDao.queryById(username);
	}

}
