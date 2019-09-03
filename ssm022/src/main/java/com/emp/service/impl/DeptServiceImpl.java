package com.emp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.emp.dao.DeptDao;
import com.emp.entity.Dept;
import com.emp.service.DeptService;
@Service
public class DeptServiceImpl implements DeptService{
    //×¢Èë²¿ÃÅdao
	@Resource
	private DeptDao deptDao;
	@Override
	public List<Dept> queryDeptAll() {
		return deptDao.queryAll();
	}

}
