package com.emp.controller;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emp.entity.User;
import com.emp.service.UserRoleService;
import com.emp.service.UserService;

@Controller
public class UserController {
	//ע��ҵ������
	@Resource
	private UserService userService;
	@Resource
	private UserRoleService userRoleService;
	
	/**
	 * ��ת��δ��Ȩҳ��
	 * @return
	 */
	@RequestMapping("/user/unauthorized")
	public String index(){
		return "Unauthorized";
	}
	
	//ע���û�
	@RequestMapping("/user/register")
	public String register(User user){
		userService.save(user);
		//��ѯ��ǰ�û���id
		int user_id=userService.queryById(user.getUsername());
		//���õ�ǰ�û���Ĭ�Ͻ�ɫ(��ͨ�û�)
		userRoleService.addUserRole(user_id);
		//�ض��򵽵�¼ҳ��
		return "redirect:/user/toLogin";
	}
	
	//��ת��ע��ҳ��
	@RequestMapping("/user/toRegister")
	public String toEnroll(){
		return "Register";
	}
	
	//��ת����¼ҳ��
	@RequestMapping("/user/toLogin")
	public String toLogin(){
		return "Login";
	}
	
	@RequestMapping("/user/login")
	public String login(User user,@RequestParam(value="remenmberMe",required=false,defaultValue="0")Integer remenmberMe,Model model){
		System.out.println("����login"+user);
		//��ȡ��ǰ�û�   Subject����   ����"/user/login"������Ķ���(�û�,����)
		Subject subject=SecurityUtils.getSubject();
		//����һ�����ƶ���
		UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
		try {
			//�ж��Ƿ�ʹ�ü�ס��
			if(remenmberMe==1){
				//ʹ�ü�ס�ҹ���
				token.setRememberMe(true);
			}
			//Ϊ��ǰ�û�������֤,��Ȩ  ��¼�ɹ����Զ������ݷ���session��
			subject.login(token);
			//subject.logout();//�ǳ�(�˳�)
			//model.addAttribute("user",user);
			//�ض�����ҳԱ���б�
			return "redirect:/emp/conditionList";
		} catch (Exception e) {
			e.printStackTrace();
			//model.addAttribute("user",user);
			model.addAttribute("errorMsg","�û������������!");
			return "Login";
		}
	}
	
	

}
