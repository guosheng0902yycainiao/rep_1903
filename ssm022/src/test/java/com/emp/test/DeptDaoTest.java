package com.emp.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dao.DeptDao;
import com.emp.dao.DeptLazyDao;
import com.emp.entity.Dept;
import com.emp.entity.Emp;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class DeptDaoTest {
	@Resource
	private DeptDao deptDao;
	@Resource
	private DeptLazyDao deptLazyDao;
    //查询所有部门
	@Test
	public void testQueryAll() {
		List<Dept> dept=deptDao.queryAll();
		for(Dept d:dept){
			System.out.println(d);
		}
	}
	//根据部门编号查询部门下的员工信息
	@Test
	public void testQueryListDept(){
		List<Dept> dept=deptDao.queryListDeptno("d001");
		for(Dept d:dept){
			System.out.println(d);
		}
	}
	//根据部门编号查询
	@Test
	public void testQueryDeptno(){
		Dept d=deptDao.queryByDeptno("e0014");
		System.out.println(d);
	}
	//增
	@Test
	public void testAddDept(){
		Dept dept=new Dept();
		dept.setDeptno("e0014");
		dept.setDname("侦查部");
		dept.setLocation("大兴安岭");
		deptDao.addDept(dept);
		System.out.println("添加成功!");
	}
	//改
	@Test
	public void testUpdateDept(){
		Dept d=deptDao.queryByDeptno("e0014");
		System.out.println(d);
		d.setDname("侦察小组");
		deptDao.updateDept(d);
		System.out.println(d);
    }
	//删
	@Test
	public void testDeleteDept(){
		deptDao.deleteDept("e0014");
		System.out.println("删除成功!");
	}
	
	//懒加载
	@Test
	public void testLazy(){
		Dept dept=deptLazyDao.queryById("d001");
		System.out.println(dept.getDname());
		/*for(Emp e:dept.getEmps()){
			System.out.println(e.getEname());
		}*/
		System.out.println(dept.getEmp());
	}
	
	//懒加载
	@Test
	public void testLazyAll(){
		List<Dept> depts=deptLazyDao.queryAll();
		for(Dept d:depts){
			System.out.println(d.getDname());
			System.out.println("******************");
			//打印部门下所有的员工姓名
			List<Emp> es=d.getEmps();
			for(Emp e:es){
				System.out.println(e.getEname());
			}
			System.out.println("******************");
		}
	}
	
	
	
	
	

}
