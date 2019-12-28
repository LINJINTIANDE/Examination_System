package com.ljt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljt.bean.College;
import com.ljt.bean.PagingVo;
import com.ljt.bean.SelectedCourseCustom;
import com.ljt.bean.Student;
import com.ljt.bean.StudentCustom;
import com.ljt.bean.StudentExample;
import com.ljt.bean.StudentExample.Criteria;
import com.ljt.dao.CollegeMapper;
import com.ljt.dao.StudentMapper;
import com.ljt.dao.StudentMapperCustom;
import com.ljt.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentMapper studentMapper;
	
	@Autowired
	StudentMapperCustom studentMapperCustom;
	
	@Autowired
	CollegeMapper collegeMapper;
	//返回学生总数
	public int getCountStudent() throws Exception {
		//自定义查询对象
		StudentExample studentExample = new StudentExample();
		//通过criteria构造查询对象
		Criteria criteria = studentExample.createCriteria();
		criteria.andUseridIsNotNull();
		
		return (int) studentMapper.countByExample(studentExample);
	}

	public List<StudentCustom> findByPaging(Integer toPageNo) throws Exception {
		PagingVo pagingVo = new PagingVo();
		pagingVo.setToPageNo(toPageNo);
		
		List<StudentCustom> list = studentMapperCustom.findByPaging(pagingVo);
		
		return list;
	}

	public Boolean save(StudentCustom studentCustom) throws Exception {
		Student stu = studentMapper.selectByPrimaryKey(studentCustom.getUserid());
		if(stu==null) {
			studentMapper.insert(studentCustom);
			return true;
		}
		
		return false;
	}

	public StudentCustom findById(Integer id) throws Exception {
		Student student = studentMapper.selectByPrimaryKey(id);
		
		StudentCustom studentCustom = null;
		if(student!=null) {
			studentCustom = new StudentCustom();
			
			//类拷贝
			BeanUtils.copyProperties(student, studentCustom);
		}
		return studentCustom;
	}

	public void updateBystudentCustom( StudentCustom studentCustom) throws Exception {
		studentMapper.updateByPrimaryKey(studentCustom);
		
	}

	public void removeByID(Integer id) throws Exception {
		
		 studentMapper.deleteByPrimaryKey(id);
	}

	public List<StudentCustom> findByname(String name) {
		StudentExample example = new StudentExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameLike("%"+name+"%");
		
		List<Student> list = studentMapper.selectByExample(example);
		List<StudentCustom> studentCustoms=null;
		if(list !=null) {
			studentCustoms = new ArrayList<StudentCustom>();
			for (Student s : list) {
				StudentCustom custom  =new StudentCustom();
				BeanUtils.copyProperties(s, custom);
				College college = collegeMapper.selectByPrimaryKey(s.getCollegeid());
				
				custom.setCollegeName(college.getCollegename());
				studentCustoms.add(custom);
			}
			
		}
		return studentCustoms;
	}

	public StudentCustom findStudentAndSelectCourseListByName(String name) throws Exception {
		StudentCustom studentCustom = studentMapperCustom.findStudentAndSelectCourseListById(Integer.parseInt(name));
		List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();
		
		for (SelectedCourseCustom s : list) {
			if(s.getMark()!=null) {
				s.setOver(true);
			}
		}
		return studentCustom;
	}

}
