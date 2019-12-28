package com.ljt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljt.bean.College;
import com.ljt.bean.Course;
import com.ljt.bean.CourseCustom;
import com.ljt.bean.CourseExample;
import com.ljt.bean.CourseExample.Criteria;
import com.ljt.bean.PagingVo;
import com.ljt.bean.Selectedcourse;
import com.ljt.bean.SelectedcourseExample;
import com.ljt.dao.CollegeMapper;
import com.ljt.dao.CourseMapper;
import com.ljt.dao.CourseMapperCustom;
import com.ljt.dao.SelectedcourseMapper;
import com.ljt.service.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService{

	@Autowired
	CollegeMapper collegeMapper;
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Autowired
	private CourseMapperCustom courseMapperCustom;
	
	@Autowired
	SelectedcourseMapper selectedcourseMapper;
	
	public int getCountCouse() throws Exception {
		//自定义查询对象
		CourseExample courseExample = new CourseExample();
		//通过criteria构造查询条件
		Criteria createCriteria = courseExample.createCriteria();
		createCriteria.andCoursenameIsNotNull();
		return (int) courseMapper.countByExample(courseExample);
	}

	public List<CourseCustom> findBypaging(Integer toPageNo) throws Exception {
		PagingVo pagingVo = new PagingVo();
		pagingVo.setToPageNo(toPageNo);
		
		List<CourseCustom> list = courseMapperCustom.findByPaging(pagingVo);
		
		return list;
	}

	public Boolean save(CourseCustom courseCustom) throws Exception {
		Course course = courseMapper.selectByPrimaryKey(courseCustom.getCourseid());
		if(course == null) {
			courseMapper.insert(courseCustom);
			return true;
		}
		return false;
	}

	public CourseCustom findById(Integer id) throws Exception {
	
		Course course = courseMapper.selectByPrimaryKey(id);
	
		CourseCustom courseCustom = null;
		if(course !=null) {
			courseCustom = new CourseCustom();
			BeanUtils.copyProperties(course, courseCustom);
		}
		
		return courseCustom;
	}

	public void updateBycourseCustom(CourseCustom courseCustom) throws Exception {
		courseMapper.updateByPrimaryKey(courseCustom);
		
	}

	public Boolean removeById(Integer id) throws Exception {
		SelectedcourseExample example = new SelectedcourseExample();
		com.ljt.bean.SelectedcourseExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andCourseidEqualTo(id);
		List<Selectedcourse> list = selectedcourseMapper.selectByExample(example);
		
		if(list.size()==0) {
			courseMapper.deleteByPrimaryKey(id);
			return true;
		}
		return false;
	}

	public List<CourseCustom> findByName(String name) throws Exception {
		CourseExample courseExample = new CourseExample();
		//自定义查询条件
		Criteria criteria = courseExample.createCriteria();
		
		criteria.andCoursenameLike("%"+name+"%");
		
		List<Course> list = courseMapper.selectByExample(courseExample);
		
		List<CourseCustom> courseCustomList = null;
		
		if(list!=null) {
			courseCustomList = new ArrayList<CourseCustom>();
			for (Course c : list) {
				CourseCustom courseCustom = new CourseCustom();
				
				//类拷贝
				BeanUtils.copyProperties(c, courseCustom);
				
				//获取课堂名
				College college = collegeMapper.selectByPrimaryKey(c.getCollegeid());
				courseCustom.setCollegeName(college.getCollegename());
			
				courseCustomList.add(courseCustom);
			}
			
		}
		
		return courseCustomList;
	}

	public List<CourseCustom> findByTeacherID(Integer id) throws Exception {
		
		CourseExample example = new CourseExample();
		Criteria criteria = example.createCriteria();
		criteria.andTeacheridEqualTo(id);
		List<Course> list = courseMapper.selectByExample(example);
		
		List<CourseCustom> customs=null;
		if(list.size()>0) {
			customs=new ArrayList<CourseCustom>();
			
			for (Course course : list) {
				CourseCustom courseCustom = new CourseCustom();
				BeanUtils.copyProperties(course, courseCustom);
				College college = collegeMapper.selectByPrimaryKey(courseCustom.getCollegeid());
				
				
				customs.add(courseCustom);
			}
			
		}
		return customs;
	}
	
	
	
}
