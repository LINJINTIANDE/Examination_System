package com.ljt.service;

import java.util.List;

import com.ljt.bean.StudentCustom;

public interface StudentService {

	//获取学生总数
	int getCountStudent() throws Exception;
	
	//获取分页查询学生信息
	List<StudentCustom> findByPaging(Integer toPageNo) throws Exception;
	
	//保存学生信息
	Boolean save(StudentCustom studentCustom) throws Exception;

	//根据id获取学生信息
	StudentCustom findById(Integer id) throws Exception;
	
	//根据id更学生信息
	void updateBystudentCustom(StudentCustom studentCustom) throws Exception;

	//根据id删学生
	void removeByID(Integer id) throws Exception;
	
	//根据名字模糊查询
	List<StudentCustom> findByname(String name);
	
	//一对多查询，查询该学生的选课信息
	StudentCustom findStudentAndSelectCourseListByName(String name) throws Exception;
}
