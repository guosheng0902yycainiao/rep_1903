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
	 * 注入Dao对象
	 */
	@Resource
	private UserDao userDao;
    
	/**
	 * 通过用户名查找用户
	 */
	@Override
	public User getByUserName(String username) {
		return userDao.getByUserName(username);
	}
    
	/**
	 * 通过用户名查找该用户所有的角色并保存在set集合中
	 */
	@Override
	public Set<String> getRoles(String username) {
		return userDao.getRoles(username);
	}

	/**
	 * 通过用户名查找该用户所有的权限并保存在set集合中
	 */
	@Override
	public Set<String> getPermission(String username) {
		return userDao.getPermission(username);
	}

	@Override
	public void save(User user) {
		//加盐,加密的效果
		//常用加密方法MD5
		//加密密码  "MD5"算法加密   user.getPassword() 需要加密的密码+ user.getUsername()以用户名为加盐  加密1024次
        String password 
        = new SimpleHash("MD5", user.getPassword(), user.getUsername(), 1024).toString();
		//password就是加密后的密码
        //用加密后的密码置换原来从页面传来的密码
        user.setPassword(password);
        //将user保存到数据库
        userDao.save(user);
	}

	@Override
	public Integer queryById(String username) {
		return userDao.queryById(username);
	}

}
