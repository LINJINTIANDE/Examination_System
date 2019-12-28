package com.ljt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljt.bean.Course;
import com.ljt.bean.CourseExample;
import com.ljt.bean.PagingVo;
import com.ljt.bean.Teacher;
import com.ljt.bean.TeacherCustom;
import com.ljt.bean.TeacherExample;
import com.ljt.bean.TeacherExample.Criteria;
import com.ljt.dao.CourseMapper;
import com.ljt.dao.TeacherMapper;
import com.ljt.dao.TeacherMapperCustom;
import com.ljt.exception.CustomException;
import com.ljt.service.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	TeacherMapper teacherMapper;
	
	@Autowired
	TeacherMapperCustom teacherMapperCustom;
	
	@Autowired
	CourseMapper courseMapper;
	
	public List<TeacherCustom> findAll() throws Exception {
		TeacherExample teacherExample = new TeacherExample();
		Criteria createCriteria = teacherExample.createCriteria();
		
		createCriteria.andUsernameIsNotNull();
		
		List<Teacher> list =teacherMapper.selectByExample(teacherExample);
		List<TeacherCustom> teacherCustomsList=null;
		if(list!=null) {
			teacherCustomsList =  new ArrayList<TeacherCustom>();
			
			for (Teacher t : list) {
				TeacherCustom teacherCustom = new TeacherCustom();
				BeanUtils.copyProperties(t, teacherCustom);
				teacherCustomsList.add(teacherCustom);
				
			}
		}
		return teacherCustomsList;
	}

	public int getCountTeacher() throws Exception {
		//自定义查询对象
		TeacherExample example = new TeacherExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridIsNotNull();
		return (int) teacherMapper.countByExample(example);
	}

	public List<TeacherCustom> findByPaing(Integer toPageNo) throws Exception {
		PagingVo pagingVo = new PagingVo();
		pagingVo.setToPageNo(toPageNo);
		
		List<TeacherCustom> list = teacherMapperCustom.findByPaging(pagingVo);
		return list;
	}

	public Boolean save(TeacherCustom teacherCustom) throws Exception {
		Teacher teacher = teacherMapper.selectByPrimaryKey(teacherCustom.getUserid());
		System.out.println(teacher);
		if(teacher==null) {
			teacherMapper.insert(teacherCustom);
			return true;
		}
		return false;
	}

	public void removeById(Integer id) throws Exception {
		CourseExample courseExample = new CourseExample();
		com.ljt.bean.CourseExample.Criteria createCriteria = courseExample.createCriteria();
		createCriteria.andTeacheridEqualTo(id);
		List<Course> list = courseMapper.selectByExample(courseExample);
		
		if(list.size()!=0) {
			throw new CustomException("请先删除该名老师所教授的课程");
		}
		teacherMapper.deleteByPrimaryKey(id);
		
		
	}

	public TeacherCustom findById(Integer id) {
		Teacher teacher = teacherMapper.selectByPrimaryKey(id);
		TeacherCustom teacherCustom=null;
		if(teacher!=null) {
			teacherCustom = new TeacherCustom();
			BeanUtils.copyProperties(teacher, teacherCustom);
		
		}
		return teacherCustom;
	}

	public void updateByteacherCustom(TeacherCustom teacherCustom) {
		teacherMapper.updateByPrimaryKey(teacherCustom);
		
	}

	public List<TeacherCustom> findByName(String name) throws Exception {
		TeacherExample teacherExample = new TeacherExample();
		Criteria createCriteria = teacherExample.createCriteria();
		createCriteria.andUsernameLike("%"+name+"%");
		
		List<Teacher> list = teacherMapper.selectByExample(teacherExample);
		TeacherCustom teacherCustom =null;
		 List<TeacherCustom> teacherCustomsList = null;
		if(list!=null) {
			teacherCustomsList = new ArrayList<TeacherCustom>();
			for (Teacher t : list) {
				teacherCustom=new TeacherCustom();
				BeanUtils.copyProperties(t, teacherCustom);
				  teacherCustomsList.add(teacherCustom);
			}
			
			
		}
		return teacherCustomsList;
	}


}
