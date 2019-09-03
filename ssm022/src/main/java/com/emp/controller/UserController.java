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
	//注入业务层对象
	@Resource
	private UserService userService;
	@Resource
	private UserRoleService userRoleService;
	
	/**
	 * 跳转到未授权页面
	 * @return
	 */
	@RequestMapping("/user/unauthorized")
	public String index(){
		return "Unauthorized";
	}
	
	//注册用户
	@RequestMapping("/user/register")
	public String register(User user){
		userService.save(user);
		//查询当前用户的id
		int user_id=userService.queryById(user.getUsername());
		//设置当前用户的默认角色(普通用户)
		userRoleService.addUserRole(user_id);
		//重定向到登录页面
		return "redirect:/user/toLogin";
	}
	
	//跳转到注册页面
	@RequestMapping("/user/toRegister")
	public String toEnroll(){
		return "Register";
	}
	
	//跳转到登录页面
	@RequestMapping("/user/toLogin")
	public String toLogin(){
		return "Login";
	}
	
	@RequestMapping("/user/login")
	public String login(User user,@RequestParam(value="remenmberMe",required=false,defaultValue="0")Integer remenmberMe,Model model){
		System.out.println("进入login"+user);
		//获取当前用户   Subject主体   调用"/user/login"的请求的东西(用户,程序)
		Subject subject=SecurityUtils.getSubject();
		//创建一个令牌对象
		UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
		try {
			//判断是否使用记住我
			if(remenmberMe==1){
				//使用记住我功能
				token.setRememberMe(true);
			}
			//为当前用户进行认证,授权  登录成功会自动把数据放在session中
			subject.login(token);
			//subject.logout();//登出(退出)
			//model.addAttribute("user",user);
			//重定向到主页员工列表
			return "redirect:/emp/conditionList";
		} catch (Exception e) {
			e.printStackTrace();
			//model.addAttribute("user",user);
			model.addAttribute("errorMsg","用户名或密码错误!");
			return "Login";
		}
	}
	
	

}
