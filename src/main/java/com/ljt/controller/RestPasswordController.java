package com.ljt.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ljt.bean.Userlogin;
import com.ljt.exception.CustomException;
import com.ljt.service.UserLoginService;

@Controller
public class RestPasswordController {

	@Autowired
	UserLoginService userLoginService;
	
	@RequestMapping(value="/passwordRest",method=RequestMethod.POST)
	public String passwordRest(String oldPassword,String password1) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		String username = (String)subject.getPrincipal();
		
		Userlogin userlogin = userLoginService.findByName(username);
		if(!oldPassword.equals(userlogin.getPassword())) {
			throw new CustomException("旧密码不正确");
		}else {
			userlogin.setPassword(password1);
			userLoginService.updateByName(username, userlogin);
		}
		return "redirect:/logout";
	}
}
