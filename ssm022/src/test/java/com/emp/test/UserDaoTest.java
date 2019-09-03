package com.emp.test;

import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.entity.User;
import com.emp.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class UserDaoTest {
	
	//注入业务层对象
	@Resource
	private UserService userService;
	
	@Test
	public void testGetUserName(){
		User user=userService.getByUserName("ls");
		System.out.println(user);
	}
	
	@Test
	public void testGetRoles(){
		Set<String> roles=userService.getRoles("zs");
		System.out.println(roles);
	}
	
	@Test
	public void testGetPermissions(){
		Set<String> pers=userService.getPermission("ls");
		System.out.println(pers);
	}
	
	@Test
	public void testSave(){
		User user=new User();
		user.setUsername("ww");
		user.setPassword("123456");
	    userService.save(user);
	   User u=userService.getByUserName("ww");
	   System.out.println(u);
	}

}
