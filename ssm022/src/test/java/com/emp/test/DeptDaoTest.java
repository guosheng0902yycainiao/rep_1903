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
    //��ѯ���в���
	@Test
	public void testQueryAll() {
		List<Dept> dept=deptDao.queryAll();
		for(Dept d:dept){
			System.out.println(d);
		}
	}
	//���ݲ��ű�Ų�ѯ�����µ�Ա����Ϣ
	@Test
	public void testQueryListDept(){
		List<Dept> dept=deptDao.queryListDeptno("d001");
		for(Dept d:dept){
			System.out.println(d);
		}
	}
	//���ݲ��ű�Ų�ѯ
	@Test
	public void testQueryDeptno(){
		Dept d=deptDao.queryByDeptno("e0014");
		System.out.println(d);
	}
	//��
	@Test
	public void testAddDept(){
		Dept dept=new Dept();
		dept.setDeptno("e0014");
		dept.setDname("��鲿");
		dept.setLocation("���˰���");
		deptDao.addDept(dept);
		System.out.println("��ӳɹ�!");
	}
	//��
	@Test
	public void testUpdateDept(){
		Dept d=deptDao.queryByDeptno("e0014");
		System.out.println(d);
		d.setDname("���С��");
		deptDao.updateDept(d);
		System.out.println(d);
    }
	//ɾ
	@Test
	public void testDeleteDept(){
		deptDao.deleteDept("e0014");
		System.out.println("ɾ���ɹ�!");
	}
	
	//������
	@Test
	public void testLazy(){
		Dept dept=deptLazyDao.queryById("d001");
		System.out.println(dept.getDname());
		/*for(Emp e:dept.getEmps()){
			System.out.println(e.getEname());
		}*/
		System.out.println(dept.getEmp());
	}
	
	//������
	@Test
	public void testLazyAll(){
		List<Dept> depts=deptLazyDao.queryAll();
		for(Dept d:depts){
			System.out.println(d.getDname());
			System.out.println("******************");
			//��ӡ���������е�Ա������
			List<Emp> es=d.getEmps();
			for(Emp e:es){
				System.out.println(e.getEname());
			}
			System.out.println("******************");
		}
	}
	
	
	
	
	

}
