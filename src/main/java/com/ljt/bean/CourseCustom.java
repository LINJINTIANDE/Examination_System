package com.ljt.bean;

public class CourseCustom extends Course{

	private String collegeName;

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	@Override
	public String toString() {
		return "CourseCustom [collegeName=" + collegeName + ", getCourseid()=" + getCourseid() + ", getCoursename()="
				+ getCoursename() + ", getTeacherid()=" + getTeacherid() + ", getCoursetime()=" + getCoursetime()
				+ ", getClassroom()=" + getClassroom() + ", getCourseweek()=" + getCourseweek() + ", getCoursetype()="
				+ getCoursetype() + ", getCollegeid()=" + getCollegeid() + ", getScore()=" + getScore()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}
