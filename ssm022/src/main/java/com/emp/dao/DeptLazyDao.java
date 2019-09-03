package com.emp.dao;


import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import com.emp.entity.Dept;

public interface DeptLazyDao {
	
	//依据编号查询部门信息
	@Select("select deptno,dname,location "
			+ " from t_dept "
			+ " where deptno=#{deptno}")
	@Results(id="deptMapper",value={
			@Result(id=true,column="deptno",property="deptno"),
			@Result(column="dname",property="dname"),
			@Result(column="location",property="location"),
			@Result(column="deptno",property="emp",
			many=@Many(select="com.emp.dao.DeptDao.queryListDeptno",
			fetchType=FetchType.LAZY)),
			@Result(column="deptno",property="emps",
			many=@Many(select="com.emp.dao.EmpDao.queryListDeptno",
			fetchType=FetchType.LAZY))
	})
	Dept queryById(@Param("deptno")String deptno);
    
	//查询所有的部门
	@Select("select deptno,dname,location from t_dept")
	@ResultMap("deptMapper")
	List<Dept> queryAll();
	
}
