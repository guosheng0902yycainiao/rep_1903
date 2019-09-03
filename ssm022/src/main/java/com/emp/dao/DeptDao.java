package com.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.emp.entity.Dept;

public interface DeptDao {
	//作业:
	//依据部门编号查询部门下所有的员工
	@Select("select d.deptno,d.dname,d.location, "
			+ " e.empno,e.ename,e.esex,e.eage,"
			+ " e.esalary,e.deptno eno,e.mgrno"
			+ " from t_dept d left join"
			+ " t_emp e on d.deptno=e.deptno "
			+ " where d.deptno=#{deptno}")
	@Results(id="deptMapper",value={
			@Result(id=true,column="deptno",property="deptno"),
			@Result(column="dname",property="dname"),
			@Result(column="location",property="location"),
			@Result(column="empno",property="emp.empno"),
			@Result(column="ename",property="emp.ename"),
			@Result(column="esex",property="emp.esex"),
			@Result(column="eage",property="emp.eage"),
			@Result(column="esalary",property="emp.esalary"),
	})
	List<Dept> queryListDeptno(@Param("deptno")String deptno);
	//查询所有部门
	@Select("select deptno,dname,location from t_dept")
	@ResultMap("deptMapper")
	List<Dept> queryAll();
	
	//根据部门编号查询
	@Select("select deptno,dname,location from t_dept where deptno=#{deptno}")
	@ResultMap("deptMapper")
	Dept queryByDeptno(@Param("deptno")String deptno);
	//增
	//增加部门
	@Insert("insert into t_dept values(#{deptno},#{dname},#{location})")
	void addDept(Dept dept);
	//删
	//根据部门编号删除数据
	@Delete("delete from t_dept where deptno=#{deptno}")
	void deleteDept(@Param("deptno")String deptno);
	//改
	//根据部门编号修改数据
	@Update("update t_dept set dname=#{dname},location=#{location}"
			+ " where deptno=#{deptno}")
	void updateDept(Dept dept);
		//完成部门Dao DeptDao的编写
		//查询所有,依据编号查询,增,删,改

}
