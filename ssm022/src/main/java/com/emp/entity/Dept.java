package com.emp.entity;

import java.util.List;

public class Dept {
	private String deptno;//���ű��
	private String dname;//��������
	private String location;//��ַ
	
	private List<Emp> emps;//ĳ�����������е�Ա��  many=@Many
	
	public List<Emp> getEmps() {
		return emps;
	}

	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}

	private Emp emp;//Ա������

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public String getDeptno() {
		return deptno;
	}

	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
	public Dept(String deptno, String dname, String location, Emp emp,List<Emp> emps) {
		super();
		this.deptno = deptno;
		this.dname = dname;
		this.location = location;
		this.emp = emp;
		this.emps = emps;
	}

	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", dname=" + dname + ", location=" + location + ", emps=" + emps + ", emp="
				+ emp + "]";
	}

	

	

	

	

}
