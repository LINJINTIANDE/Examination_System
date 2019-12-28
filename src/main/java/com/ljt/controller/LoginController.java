package com.ljt.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ljt.bean.Userlogin;

@Controller
public class LoginController {
	
	
	
	
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(Userlogin userlogin) {
		
		//shiro登录
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userlogin.getUsername(),userlogin.getPassword());
		
		Subject subject=SecurityUtils.getSubject();
		
		//如果获取不到用户登录就会失败，会抛出异常
		subject.login(usernamePasswordToken);
		
		System.out.println(subject.getPrincipal());
		System.out.println(subject.getPrincipals());

	     if (subject.hasRole("admin")) {
	            return "redirect:/admin/showStudent";
	        } else if (subject.hasRole("teacher")) {
	            return "redirect:/teacher/showCourse";
	        } else if (subject.hasRole("student")) {
	            return "redirect:/student/showCourse";
	        }

	        return "/login";
		
	}
	
//	@RequestMapping(value="/student/showCourse", method=RequestMethod.GET)
//	public String aa() {
//		return "/student/showCourse";
//	}
}
