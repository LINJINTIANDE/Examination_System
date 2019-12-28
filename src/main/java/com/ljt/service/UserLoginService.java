package com.ljt.service;

import com.ljt.bean.Userlogin;

public interface UserLoginService {

	//根据名字查找用户
	Userlogin findByName(String name) throws Exception;

	//保存用户登录信息
	void save(Userlogin userlogin) throws Exception;
	
	//根据名字删除
	void removeByName(String name) throws Exception;
	
	//根据用户名更新
	void updateByName(String name,Userlogin userlogin) throws Exception;
}
