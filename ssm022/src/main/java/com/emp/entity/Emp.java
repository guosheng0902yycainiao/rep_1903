package com.emp.entity;
/**
 * 
 * @author me
 * 员工类
 *
 */
public class Emp {
	private String empno;//员工编号
	//正则2~4个字的中文字符
	/*
	 * public @interface Pattern{
	 *   String regexp();
	 *   String message() default "不匹配"+regexp()
	 * }
	 * @RangeCheck(range={"张三","李四","王五"},message="错误的名字")
	 * */
	private String ename;//员工姓名
	//只能是男或者女
	private String esex;//性别
	//18~55
	private Integer eage;//年龄
	//3000~50000
	private Float esalary;//薪资
	/*//只能是d001 d002 d003
	private String deptno;//部门编号
	//只能是e001 e004 e007
	private String mgrno;//经理编号
*/	
	private Dept dept;//部门  关联属性
	
	private Emp mgr;//经理   关联属性
	
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Emp getMgr() {
		return mgr;
	}
	public void setMgr(Emp mgr) {
		this.mgr = mgr;
	}
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEsex() {
		return esex;
	}
	public void setEsex(String esex) {
		this.esex = esex;
	}
	public Integer getEage() {
		return eage;
	}
	public void setEage(Integer eage) {
		this.eage = eage;
	}
	public Float getEsalary() {
		return esalary;
	}
	public void setEsalary(Float esalary) {
		this.esalary = esalary;
	}
	/*public String getDeptno() {
		return deptno;
	}
	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}
	public String getMgrno() {
		return mgrno;
	}
	public void setMgrno(String mgrno) {
		this.mgrno = mgrno;
	}*/
	
	public Emp() {
		super();
	}
	public Emp(String empno, String ename, String esex, Integer eage, Float esalary, Dept dept, Emp mgr) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.esex = esex;
		this.eage = eage;
		this.esalary = esalary;
		this.dept = dept;
		this.mgr = mgr;
	}
	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", esex=" + esex + ", eage=" + eage + ", esalary=" + esalary
				+ ", dept=" + dept + ", mgr=" + mgr + "]";
	}
	

}
