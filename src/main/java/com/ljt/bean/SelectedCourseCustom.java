package com.ljt.bean;

public class SelectedCourseCustom extends Selectedcourse{

	//新增student对象字段
	private StudentCustom studentCustom;
	
	//扩展课程信息对象
	private CourseCustom courseCustom;
	
	//判断该学生是否已经完成该课程
	private Boolean over = false;

	public StudentCustom getStudentCustom() {
		return studentCustom;
	}

	public void setStudentCustom(StudentCustom studentCustom) {
		this.studentCustom = studentCustom;
	}

	public CourseCustom getCourseCustom() {
		return courseCustom;
	}

	public void setCourseCustom(CourseCustom courseCustom) {
		this.courseCustom = courseCustom;
	}

	public Boolean getOver() {
		return over;
	}

	public void setOver(Boolean over) {
		this.over = over;
	}

	@Override
	public String toString() {
		return "SelectedCourseCustom [studentCustom=" + studentCustom + ", courseCustom=" + courseCustom + ", over="
				+ over + ", getCourseid()=" + getCourseid() + ", getStudentid()=" + getStudentid() + ", getMark()="
				+ getMark() + "]";
	}
}
