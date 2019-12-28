package com.ljt.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ljt.bean.Role;
import com.ljt.bean.Userlogin;
import com.ljt.service.RoleService;
import com.ljt.service.UserLoginService;

@Component
public class LoginRealm extends AuthorizingRealm{

	@Autowired
	RoleService roleService;
	
	@Autowired
	UserLoginService userLoginService;
	
	/**
	 * 获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息
	 * 当调同权限验证时，就会调用这个方法*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		String uername = (String)getAvailablePrincipal(principals);
		
		Role role = null;
		
		try {
			Userlogin userlogin = userLoginService.findByName(uername);
			//获取角色对象
			role = roleService.findById(userlogin.getRole());
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		//通过用户名从数据库获取权限/角色信息
		SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();
		Set<String> r = new HashSet<String>();
		if(role !=null) {
			r.add(role.getRolename());
			info.setRoles(r);
		}
		
		return info;
	}

	/**
	 * 在这个方法中，进行身份验证
	 * 		login时调用*/
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//用户名
		String username = (String)token.getPrincipal();
		//密码
		String password = new String ((char[])token.getCredentials());
		
		Userlogin userlogin = null;
		try {
			 userlogin = userLoginService.findByName(username);
		}catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(password+"-------"+userlogin.getPassword());
		
		  if(userlogin.getUsername() == null) { //没有用户名 
			  throw new UnknownAccountException();
		  } else
		  if(!password.equals(userlogin.getPassword())) { //密码错误 
			  throw new IncorrectCredentialsException();
		  
		  }
		 
		
		//身份验证通过，返回一个身份信息
		AuthenticationInfo info = new SimpleAuthenticationInfo(username,password,getName());
		System.out.println(username+"=="+password);
		
		return info;
	}

}
