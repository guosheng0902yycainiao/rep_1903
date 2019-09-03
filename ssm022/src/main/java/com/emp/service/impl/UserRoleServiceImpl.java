package com.emp.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.emp.dao.UserRoleDao;
import com.emp.entity.UserRole;
import com.emp.service.UserRoleService;
@Service
public class UserRoleServiceImpl implements UserRoleService{

	@Resource
	private UserRoleDao userRoleDao;
	
	@Override
	public Set<String> queryByUserRole(int user_id) {
		return userRoleDao.queryByUserRole(user_id);
	}

	@Override
	public Set<String> queryByRole(int user_id) {
		return userRoleDao.queryByRole(user_id);
	}

	@Override
	public int addUserRole(int user_id) {
		return userRoleDao.addUserRole(user_id);
	}

	@Override
	public int updateRole(UserRole userRole) {
		return userRoleDao.updateRole(userRole);
	}

	@Override
	public int delUserRole(int user_id) {
		return userRoleDao.delUserRole(user_id);
	}

}
