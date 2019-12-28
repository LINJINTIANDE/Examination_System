package com.ljt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljt.bean.Role;
import com.ljt.dao.RoleMapper;
import com.ljt.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleMapper roleMapper;
	
	public Role findById(Integer id) throws Exception {
		
		return roleMapper.selectByPrimaryKey(id);
	}

}
