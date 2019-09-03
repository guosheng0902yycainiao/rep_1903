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
	//ע��Ա��Dao
	@Resource
	private EmpDao empDao;

	@Override//��ҳ��ѯ --���÷�ҳ����
	public PageBean<Emp> queryByPage(Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<Emp> list=empDao.queryAll();
		//�ܼ�¼��
		PageInfo<Emp> pageInfo = new PageInfo<Emp>(list);
		//����һ��pageBean����
		PageBean<Emp> pageBean=new PageBean<Emp>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setList(pageInfo.getList());
		pageBean.setTotalCount((int)(pageInfo.getTotal()));
		return pageBean;
	}

	@Override//������ҳ��ѯ
	public PageBean<Emp> queryByCondition(Integer pageNo, Integer pageSize, String ename) {
		PageHelper.startPage(pageNo,pageSize);
		List<Emp> list=empDao.queryListName(ename);
		//�ܼ�¼��
		PageInfo<Emp> pageInfo = new PageInfo<Emp>(list);
		//����һ��pageBean����
		PageBean<Emp> pageBean=new PageBean<Emp>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setList(pageInfo.getList());
		pageBean.setTotalCount((int)(pageInfo.getTotal()));
		return pageBean;
	}

	@Override//���ݱ�Ų�ѯԱ��
	public Emp queryEmpById(String empno) {
		return empDao.queryById(empno);
	}

	@Override//��
	public void addEmp(Emp emp) {
		empDao.addEmp(emp);
	}

	@Override//��
	public void updateEmp(Emp emp) {
		empDao.updateEmp(emp);
	}

	@Override//ɾ
	public void deleteEmp(String empno) {
		empDao.deleteEmp(empno);
	}

	@Override//��ѯ���еľ���
	public List<Emp> queryMgrs() {
		return empDao.queryMgrs();
	}

}
