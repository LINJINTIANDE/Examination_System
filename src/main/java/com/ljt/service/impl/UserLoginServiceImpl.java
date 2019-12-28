package com.ljt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljt.bean.Userlogin;
import com.ljt.bean.UserloginExample;
import com.ljt.bean.UserloginExample.Criteria;
import com.ljt.dao.UserloginMapper;
import com.ljt.service.UserLoginService;

@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService{

	@Autowired
	UserloginMapper userloginMapper;
	
	public Userlogin findByName(String name) throws Exception {
		UserloginExample example = new UserloginExample();
		
		Criteria createCriteria = example.createCriteria();
		
		createCriteria.andUsernameEqualTo(name);
		
		List<Userlogin> selectByExample = userloginMapper.selectByExample(example);
		
		
		return selectByExample.get(0);
	}

	public void save(Userlogin userlogin) throws Exception {
		userloginMapper.insert(userlogin);
		
	}

	public void removeByName(String name) throws Exception {
		UserloginExample example = new UserloginExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(name);
		
		userloginMapper.deleteByExample(example);
		
	}

	public void updateByName(String name, Userlogin userlogin) throws Exception {
		UserloginExample userloginExample = new UserloginExample();
		Criteria createCriteria = userloginExample.createCriteria();
		createCriteria.andUsernameEqualTo(name);
		userloginMapper.updateByExample(userlogin, userloginExample);
		
	}

}
