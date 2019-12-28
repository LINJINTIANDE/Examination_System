package com.ljt.dao;

import java.util.List;

import com.ljt.bean.PagingVo;
import com.ljt.bean.StudentCustom;

public interface StudentMapperCustom {

	//分页查询学生信息
	List<StudentCustom> findByPaging(PagingVo pagingVo) throws Exception;

	//查询学生信息，和其选课信息
	StudentCustom findStudentAndSelectCourseListById(Integer id) throws Exception;
}
