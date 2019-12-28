package com.ljt.service;

import java.util.List;

import com.ljt.bean.SelectedCourseCustom;

public interface SelectedCourseService {
	
	//查询指定学生成绩
	SelectedCourseCustom findOne(SelectedCourseCustom selectedCourseCustom);

	//选课
	void save(SelectedCourseCustom selectedCourseCustom) throws Exception;

	//退课
	void remove(SelectedCourseCustom selectedCourseCustom) throws Exception;

	//根据课程ID查询课程
	List<SelectedCourseCustom> findBycourseID(Integer id) throws Exception;
	
	
	//打分
	void updateOne(SelectedCourseCustom selectedCourseCustom) throws Exception;
}
