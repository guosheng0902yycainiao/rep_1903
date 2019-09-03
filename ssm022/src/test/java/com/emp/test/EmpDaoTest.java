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
    //��ѯ����
	@Test
	public void testQueryAll() {
		List<Emp> emps=empDao.queryAll();
		for(Emp e:emps){
			System.out.println(e.getEname()+","
		                     +e.getDept().getDname()+","
					         +e.getMgr().getEname());
		}
		
	}
	//����Ա����Ų�ѯ
	@Test
	public void testQueryById(){
		Emp emp=empDao.queryById("e001");
		System.out.println(emp.getEname()+","
		                     +emp.getDept().getDname()+","
					         +emp.getMgr().getEname());
	}
	//���ݲ��ű�Ų�ѯ����������Ա����Ϣ
	@Test
	public void testQueryByDeptno(){
		List<Emp> emp=empDao.queryListDeptno("d001");
		for(Emp e:emp){
			System.out.println(e);
		}
	}
	//����Ա������ģ����ѯ
	@Test
	public void testQueryListName(){
		List<Emp> emps=empDao.queryListName("��");
		for(Emp e:emps){
			System.out.println(e.getEname()+","
                    +e.getDept().getDname()+","
			         +e.getMgr().getEname());
		}
	}
	@Test
	public void testAddEmp(){
		//����һ��Ա������
		Emp emp=new Emp();
		emp.setEmpno("e668");
		emp.setEname("�ŷ�");
		emp.setEsex("��");
		emp.setEage(60);
		emp.setEsalary(30000F);
		//����һ�����Ŷ���
		Dept dept=new Dept();
		dept.setDeptno("d001");
		emp.setDept(dept);
		//����һ���������
		Emp mgr=new Emp();
		mgr.setEmpno("e001");
		emp.setMgr(mgr);
		//��e���󱣴浽���ݿ���
		empDao.addEmp(emp);
		System.out.println("����ɹ�!");
	}
	@Test
	public void testUpdateEmp(){
		Emp e=empDao.queryById("e668");
		System.out.println(e);
		e.setEage(20);
		e.setEsalary(20000F);
		empDao.updateEmp(e);
		System.out.println("�޸ĳɹ�!");
		Emp e2=empDao.queryById("e668");
		System.out.println(e2);
	}
	@Test
	public void testDeleteEmp(){
		empDao.deleteEmp("e668");
		System.out.println("ɾ���ɹ�!");
	}
	//��ѯ���в��ž���
	@Test
	public void testQueryMgrs(){
		List<Emp> emp=empDao.queryMgrs();
		for(Emp e:emp){
			System.out.println(e);
		}
	}
	
	@Test//����������     ��������Ǳ���ͻ��ջ���(StackOverflowError)
	public void testLazy(){
		Emp e=empLazyDao.queryById("e001");
		//System.out.println(e.getEname());
		System.out.println("================");
		//System.out.println(e.getDept().getDname());
		System.out.println(e);
	}
	@Test//������2
	public void testLazyAll(){
		List<Emp> es=empLazyDao.queryAll();
		for(Emp e:es){
			if(e.getDept()!=null)
		    System.out.println(e.getEname()+","+e.getDept().getDname());
		}
	}
	

}
