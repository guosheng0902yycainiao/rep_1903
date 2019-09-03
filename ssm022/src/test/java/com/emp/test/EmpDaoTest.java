package com.emp.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dao.EmpDao;
import com.emp.dao.EmpLazyDao;
import com.emp.entity.Dept;
import com.emp.entity.Emp;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class EmpDaoTest {
	@Resource
	private EmpDao empDao;
	@Resource
	private EmpLazyDao empLazyDao;
    //查询所有
	@Test
	public void testQueryAll() {
		List<Emp> emps=empDao.queryAll();
		for(Emp e:emps){
			System.out.println(e.getEname()+","
		                     +e.getDept().getDname()+","
					         +e.getMgr().getEname());
		}
		
	}
	//根据员工编号查询
	@Test
	public void testQueryById(){
		Emp emp=empDao.queryById("e001");
		System.out.println(emp.getEname()+","
		                     +emp.getDept().getDname()+","
					         +emp.getMgr().getEname());
	}
	//根据部门编号查询部门下所有员工信息
	@Test
	public void testQueryByDeptno(){
		List<Emp> emp=empDao.queryListDeptno("d001");
		for(Emp e:emp){
			System.out.println(e);
		}
	}
	//根据员工姓名模糊查询
	@Test
	public void testQueryListName(){
		List<Emp> emps=empDao.queryListName("李");
		for(Emp e:emps){
			System.out.println(e.getEname()+","
                    +e.getDept().getDname()+","
			         +e.getMgr().getEname());
		}
	}
	@Test
	public void testAddEmp(){
		//创建一个员工对象
		Emp emp=new Emp();
		emp.setEmpno("e668");
		emp.setEname("张飞");
		emp.setEsex("男");
		emp.setEage(60);
		emp.setEsalary(30000F);
		//创建一个部门对象
		Dept dept=new Dept();
		dept.setDeptno("d001");
		emp.setDept(dept);
		//创建一个经理对象
		Emp mgr=new Emp();
		mgr.setEmpno("e001");
		emp.setMgr(mgr);
		//将e对象保存到数据库中
		empDao.addEmp(emp);
		System.out.println("插入成功!");
	}
	@Test
	public void testUpdateEmp(){
		Emp e=empDao.queryById("e668");
		System.out.println(e);
		e.setEage(20);
		e.setEsalary(20000F);
		empDao.updateEmp(e);
		System.out.println("修改成功!");
		Emp e2=empDao.queryById("e668");
		System.out.println(e2);
	}
	@Test
	public void testDeleteEmp(){
		empDao.deleteEmp("e668");
		System.out.println("删除成功!");
	}
	//查询所有部门经理
	@Test
	public void testQueryMgrs(){
		List<Emp> emp=empDao.queryMgrs();
		for(Emp e:emp){
			System.out.println(e);
		}
	}
	
	@Test//测试懒加载     如果经理是本身就会堆栈溢出(StackOverflowError)
	public void testLazy(){
		Emp e=empLazyDao.queryById("e001");
		//System.out.println(e.getEname());
		System.out.println("================");
		//System.out.println(e.getDept().getDname());
		System.out.println(e);
	}
	@Test//懒加载2
	public void testLazyAll(){
		List<Emp> es=empLazyDao.queryAll();
		for(Emp e:es){
			if(e.getDept()!=null)
		    System.out.println(e.getEname()+","+e.getDept().getDname());
		}
	}
	

}
