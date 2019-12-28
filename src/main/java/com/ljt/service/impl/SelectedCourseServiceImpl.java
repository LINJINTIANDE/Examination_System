package com.ljt.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljt.bean.SelectedCourseCustom;
import com.ljt.bean.Selectedcourse;
import com.ljt.bean.SelectedcourseExample;
import com.ljt.bean.SelectedcourseExample.Criteria;
import com.ljt.bean.Student;
import com.ljt.bean.StudentCustom;
import com.ljt.dao.SelectedcourseMapper;
import com.ljt.dao.StudentMapper;
import com.ljt.service.SelectedCourseService;

@Service("selectedCourseService")
public class SelectedCourseServiceImpl<E> implements SelectedCourseService{

	@Autowired
	SelectedcourseMapper selectedcourseMapper;
	
	@Autowired
	StudentMapper studentMapper;
	
	//查询指定学生成绩
	public SelectedCourseCustom findOne(SelectedCourseCustom selectedCourseCustom) {
		
		SelectedcourseExample example = new  SelectedcourseExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andCourseidEqualTo(selectedCourseCustom.getCourseid());
		criteria.andStudentidEqualTo(selectedCourseCustom.getStudentid());
		
		List<Selectedcourse> list = selectedcourseMapper.selectByExample(example);
		
		
		if(list.size()>0) {
			SelectedCourseCustom sc = new SelectedCourseCustom();
			BeanUtils.copyProperties(list.get(0), sc);
			
			
			Student student = studentMapper.selectByPrimaryKey(selectedCourseCustom.getStudentid());
			
			StudentCustom studentCustom  =new StudentCustom();
			BeanUtils.copyProperties(student, studentCustom);
			System.out.println("==="+studentCustom);
			sc.setStudentCustom(studentCustom);
			
			System.out.println(sc);
			return sc;
		}
		
		return null;
	}

	public void save(SelectedCourseCustom selectedCourseCustom) throws Exception {
		selectedcourseMapper.insert(selectedCourseCustom);
		
	}

	public void remove(SelectedCourseCustom selectedCourseCustom) throws Exception {
		SelectedcourseExample example = new SelectedcourseExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andCourseidEqualTo(selectedCourseCustom.getCourseid());
		criteria.andStudentidEqualTo(selectedCourseCustom.getStudentid());
		
		selectedcourseMapper.deleteByExample(example);
		
	}

	public List<SelectedCourseCustom> findBycourseID(Integer id) throws Exception {
		SelectedcourseExample example = new SelectedcourseExample();
		Criteria criteria = example.createCriteria();
		criteria.andCourseidEqualTo(id);
		
		List<Selectedcourse> list = selectedcourseMapper.selectByExample(example);
		List<SelectedCourseCustom> secList = new ArrayList<SelectedCourseCustom>();
		
		for (Selectedcourse s : list) {
			SelectedCourseCustom sec = new SelectedCourseCustom();
			BeanUtils.copyProperties(s, sec);
			if(sec.getMark()==null) {
				//判断是否完成该类课程
				if(sec.getMark()!=null) {
					sec.setOver(true);
				}
				Student student = studentMapper.selectByPrimaryKey(sec.getStudentid());
				StudentCustom studentCustom = new StudentCustom();
				BeanUtils.copyProperties(student, studentCustom);
				
				sec.setStudentCustom(studentCustom);
				secList.add(sec);
				
			}
		}
		return secList;
	}

	public void updateOne(SelectedCourseCustom selectedCourseCustom) throws Exception {
		SelectedcourseExample example = new SelectedcourseExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andCourseidEqualTo(selectedCourseCustom.getCourseid());
		criteria.andStudentidEqualTo(selectedCourseCustom.getStudentid());
		
		selectedcourseMapper.updateByExample(selectedCourseCustom, example);
		
	}

}
