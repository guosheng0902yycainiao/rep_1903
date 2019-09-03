package com.emp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.emp.dao.EmpDao;
import com.emp.entity.Emp;
import com.emp.service.EmpService;
import com.emp.utils.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class EmpServiceImpl implements EmpService{
	//注入员工Dao
	@Resource
	private EmpDao empDao;

	@Override//分页查询 --配置分页助手
	public PageBean<Emp> queryByPage(Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<Emp> list=empDao.queryAll();
		//总记录数
		PageInfo<Emp> pageInfo = new PageInfo<Emp>(list);
		//创建一个pageBean对象
		PageBean<Emp> pageBean=new PageBean<Emp>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setList(pageInfo.getList());
		pageBean.setTotalCount((int)(pageInfo.getTotal()));
		return pageBean;
	}

	@Override//条件分页查询
	public PageBean<Emp> queryByCondition(Integer pageNo, Integer pageSize, String ename) {
		PageHelper.startPage(pageNo,pageSize);
		List<Emp> list=empDao.queryListName(ename);
		//总记录数
		PageInfo<Emp> pageInfo = new PageInfo<Emp>(list);
		//创建一个pageBean对象
		PageBean<Emp> pageBean=new PageBean<Emp>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setList(pageInfo.getList());
		pageBean.setTotalCount((int)(pageInfo.getTotal()));
		return pageBean;
	}

	@Override//根据编号查询员工
	public Emp queryEmpById(String empno) {
		return empDao.queryById(empno);
	}

	@Override//增
	public void addEmp(Emp emp) {
		empDao.addEmp(emp);
	}

	@Override//改
	public void updateEmp(Emp emp) {
		empDao.updateEmp(emp);
	}

	@Override//删
	public void deleteEmp(String empno) {
		empDao.deleteEmp(empno);
	}

	@Override//查询所有的经理
	public List<Emp> queryMgrs() {
		return empDao.queryMgrs();
	}

}
