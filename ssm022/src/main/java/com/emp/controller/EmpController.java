package com.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emp.entity.Dept;
import com.emp.entity.Emp;
import com.emp.service.DeptService;
import com.emp.service.EmpService;
import com.emp.utils.PageBean;

@Controller
public class EmpController {
	//注入业务层对象
	@Resource
	private EmpService empService;
	@Resource
	private DeptService deptService;
	//加载数据
	public void loadData(HttpSession session){
		//创建生成性别radio的map
		Map<String,String> map=new HashMap<String,String>();
		map.put("男", "男");
		map.put("女", "女");
		session.setAttribute("map", map);
		//生成部门数据
		List<Dept> depts=deptService.queryDeptAll();
		session.setAttribute("depts", depts);
		//生成经理的数据
		List<Emp> mgrs=empService.queryMgrs();
		session.setAttribute("mgrs", mgrs);
		
	}
	
	//分页查询
	@RequestMapping("/emp/list")
	public String queryByPage(
			@RequestParam(value="pageNo",required=false,defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="10")Integer pageSize,
			Model model){
		PageBean<Emp> pageBean=empService.queryByPage(pageNo, pageSize);
		//将pageBean放到作用域中
		model.addAttribute("pageBean",pageBean);
		return "ListEmp";
	}
	
	//条件分页查询
	@RequestMapping("/emp/conditionList")
	@RequiresPermissions(value={"emp:query","emp:del"},logical=Logical.AND)
	//@RequiresRoles(value={"普通用户","管理员"},logical=Logical.AND)
	public String queryByCondition(
			@RequestParam(value="pageNo",required=false,defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="10")Integer pageSize,
			@RequestParam(value="cd",required=false,defaultValue="")String cd,
			HttpSession session){
		//去除空格
		cd=cd.trim();
		PageBean<Emp> pageBean=empService.queryByCondition(pageNo, pageSize, cd);
		//将pageBean和ename放到作用域中
		session.setAttribute("pageBean",pageBean);
		session.setAttribute("cd",cd);
		return "ListEmp";
	}
	//依据员工编号查询员工
	@RequestMapping(value="/emp/{empno}",method=RequestMethod.GET)
	@ResponseBody
	public Emp queryById(@PathVariable("empno")String empno){
		Emp emp=empService.queryEmpById(empno);
		return emp;
	}
	
	//跳转到添加员工页面
	@RequestMapping("/emp/addEmpTo")
	public String addEmp(@ModelAttribute("emp")Emp emp,HttpSession session){
		loadData(session);
		System.out.println("进入到addEmp!!");
		return "addEmp";
	}
	//提交与业务后回到员工页面
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String submitAddEmp(Emp emp){
		System.out.println("进入到submitAddEmp!!");
		String empno=UUID.randomUUID().toString();
		emp.setEmpno(empno);
		empService.addEmp(emp);
		System.out.println("添加成功!!");
		return "redirect:/emp/conditionList";
	}
	//跳转到修改页面
	@RequestMapping("/emp/update")
	public String updateEmpJsp(@RequestParam("empno")String empno,
			@ModelAttribute("emp")Emp emp,
			HttpSession session,Model model){
		loadData(session);
		System.out.println("进入到updateEmpJsp!!");
		emp=empService.queryEmpById(empno);
		model.addAttribute("emp",emp);
		return "updateEmp";
	}
	//修改后提交返回页面
	@RequestMapping(value="/emp",method=RequestMethod.PUT)
	public String updateEmp(@ModelAttribute("emp")Emp emp,HttpSession session){
		System.out.println("进入updateEmp!!");
		//更新修改信息到数据库中
		empService.updateEmp(emp);
		System.out.println("修改成功!");
		//从session中取出pageBean和查询条件cd
		PageBean<Emp> pageBean=(PageBean<Emp>)session.getAttribute("pageBean");
		String cd=(String)session.getAttribute("cd");
		//更新一下pageBean的list
		pageBean=empService.queryByCondition(pageBean.getPageNo(), pageBean.getPagaSize(), cd);
		//将pageBean对象重新放回session中
		session.setAttribute("pageBean", pageBean);
		return "ListEmp";
	}
	//修改后put请求的跳转报错
	//return "redirect:/emp/rdList";
	//重定向转成post请求转发
	/*@RequestMapping("/emp/rdList")
	public String toEmpList(){
		return "ListEmp";
	}*/
	
	//删除
	@RequestMapping(value="/emp/{empno}",method=RequestMethod.DELETE)
	public String deleteEmp(@PathVariable("empno")String empno){
		System.out.println("进入到deleteEmp!!");
		empService.deleteEmp(empno);
		System.out.println("删除成功!!");
		return "redirect:/emp/conditionList";
	}

}
