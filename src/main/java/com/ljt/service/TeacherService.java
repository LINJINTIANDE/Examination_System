package com.ljt.service;

import java.util.List;

import com.ljt.bean.TeacherCustom;

public interface TeacherService {

	
	//获取全部教师
	List<TeacherCustom> findAll() throws Exception;
	
	//获取老师的总数
	int getCountTeacher() throws Exception;
	
	//获取分页查询老师信息
	List<TeacherCustom> findByPaing(Integer toPageNo) throws Exception;
	
	//保存老师信息
	Boolean save(TeacherCustom teacherCustom) throws Exception;
	
	//通过ID删除教师
	void removeById(Integer id)throws Exception;

	//通过id查找教师
	TeacherCustom findById(Integer id);
	
	//根据teacherCustom更新老师信息
	void updateByteacherCustom(TeacherCustom teacherCustom);
	
	//通过名字来查找教师
	List<TeacherCustom> findByName(String name)throws Exception;
}
