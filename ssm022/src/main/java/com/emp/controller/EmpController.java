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
	//ע��ҵ������
	@Resource
	private EmpService empService;
	@Resource
	private DeptService deptService;
	//��������
	public void loadData(HttpSession session){
		//���������Ա�radio��map
		Map<String,String> map=new HashMap<String,String>();
		map.put("��", "��");
		map.put("Ů", "Ů");
		session.setAttribute("map", map);
		//���ɲ�������
		List<Dept> depts=deptService.queryDeptAll();
		session.setAttribute("depts", depts);
		//���ɾ��������
		List<Emp> mgrs=empService.queryMgrs();
		session.setAttribute("mgrs", mgrs);
		
	}
	
	//��ҳ��ѯ
	@RequestMapping("/emp/list")
	public String queryByPage(
			@RequestParam(value="pageNo",required=false,defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="10")Integer pageSize,
			Model model){
		PageBean<Emp> pageBean=empService.queryByPage(pageNo, pageSize);
		//��pageBean�ŵ���������
		model.addAttribute("pageBean",pageBean);
		return "ListEmp";
	}
	
	//������ҳ��ѯ
	@RequestMapping("/emp/conditionList")
	@RequiresPermissions(value={"emp:query","emp:del"},logical=Logical.AND)
	//@RequiresRoles(value={"��ͨ�û�","����Ա"},logical=Logical.AND)
	public String queryByCondition(
			@RequestParam(value="pageNo",required=false,defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="10")Integer pageSize,
			@RequestParam(value="cd",required=false,defaultValue="")String cd,
			HttpSession session){
		//ȥ���ո�
		cd=cd.trim();
		PageBean<Emp> pageBean=empService.queryByCondition(pageNo, pageSize, cd);
		//��pageBean��ename�ŵ���������
		session.setAttribute("pageBean",pageBean);
		session.setAttribute("cd",cd);
		return "ListEmp";
	}
	//����Ա����Ų�ѯԱ��
	@RequestMapping(value="/emp/{empno}",method=RequestMethod.GET)
	@ResponseBody
	public Emp queryById(@PathVariable("empno")String empno){
		Emp emp=empService.queryEmpById(empno);
		return emp;
	}
	
	//��ת�����Ա��ҳ��
	@RequestMapping("/emp/addEmpTo")
	public String addEmp(@ModelAttribute("emp")Emp emp,HttpSession session){
		loadData(session);
		System.out.println("���뵽addEmp!!");
		return "addEmp";
	}
	//�ύ��ҵ���ص�Ա��ҳ��
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String submitAddEmp(Emp emp){
		System.out.println("���뵽submitAddEmp!!");
		String empno=UUID.randomUUID().toString();
		emp.setEmpno(empno);
		empService.addEmp(emp);
		System.out.println("��ӳɹ�!!");
		return "redirect:/emp/conditionList";
	}
	//��ת���޸�ҳ��
	@RequestMapping("/emp/update")
	public String updateEmpJsp(@RequestParam("empno")String empno,
			@ModelAttribute("emp")Emp emp,
			HttpSession session,Model model){
		loadData(session);
		System.out.println("���뵽updateEmpJsp!!");
		emp=empService.queryEmpById(empno);
		model.addAttribute("emp",emp);
		return "updateEmp";
	}
	//�޸ĺ��ύ����ҳ��
	@RequestMapping(value="/emp",method=RequestMethod.PUT)
	public String updateEmp(@ModelAttribute("emp")Emp emp,HttpSession session){
		System.out.println("����updateEmp!!");
		//�����޸���Ϣ�����ݿ���
		empService.updateEmp(emp);
		System.out.println("�޸ĳɹ�!");
		//��session��ȡ��pageBean�Ͳ�ѯ����cd
		PageBean<Emp> pageBean=(PageBean<Emp>)session.getAttribute("pageBean");
		String cd=(String)session.getAttribute("cd");
		//����һ��pageBean��list
		pageBean=empService.queryByCondition(pageBean.getPageNo(), pageBean.getPagaSize(), cd);
		//��pageBean�������·Ż�session��
		session.setAttribute("pageBean", pageBean);
		return "ListEmp";
	}
	//�޸ĺ�put�������ת����
	//return "redirect:/emp/rdList";
	//�ض���ת��post����ת��
	/*@RequestMapping("/emp/rdList")
	public String toEmpList(){
		return "ListEmp";
	}*/
	
	//ɾ��
	@RequestMapping(value="/emp/{empno}",method=RequestMethod.DELETE)
	public String deleteEmp(@PathVariable("empno")String empno){
		System.out.println("���뵽deleteEmp!!");
		empService.deleteEmp(empno);
		System.out.println("ɾ���ɹ�!!");
		return "redirect:/emp/conditionList";
	}

}
