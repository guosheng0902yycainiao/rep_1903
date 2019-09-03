package com.emp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.emp.dao.DeptDao;
import com.emp.entity.Dept;
import com.emp.service.DeptService;
@Service
public class DeptServiceImpl implements DeptService{
    //ע�벿��dao
	@Resource
	private DeptDao deptDao;
	@Override
	public List<Dept> queryDeptAll() {
		return deptDao.queryAll();
	}

}
