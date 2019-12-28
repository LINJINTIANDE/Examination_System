package com.ljt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljt.bean.College;
import com.ljt.bean.CollegeExample;
import com.ljt.bean.CollegeExample.Criteria;
import com.ljt.dao.CollegeMapper;
import com.ljt.service.CollegeService;

@Service("collegeService")
public class CollegeServiceImpl implements CollegeService{

	@Autowired
	private CollegeMapper collegeMapper;
	
	public List<College> finAll() throws Exception {
		CollegeExample collegeExample = new CollegeExample();
		Criteria createCriteria = collegeExample.createCriteria();
		
		createCriteria.andCollegeidIsNotNull();
		return collegeMapper.selectByExample(collegeExample);
	}

}
