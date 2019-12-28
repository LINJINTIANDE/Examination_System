package com.ljt.bean;

import java.util.List;

public class StudentCustom extends Student{

	//所属院系名
	private String collegeName;
	
	//选课列表
	private List<SelectedCourseCustom> selectedCourseList;

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public List<SelectedCourseCustom> getSelectedCourseList() {
		return selectedCourseList;
	}

	public void setSelectedCourseList(List<SelectedCourseCustom> selectedCourseList) {
		this.selectedCourseList = selectedCourseList;
	}

	@Override
	public String toString() {
		return "StudentCustom [collegeName=" + collegeName + ", selectedCourseList=" + selectedCourseList
				+ ", getUserid()=" + getUserid() + ", getUsername()=" + getUsername() + ", getSex()=" + getSex()
				+ ", getBirthyear()=" + getBirthyear() + ", getGrade()=" + getGrade() + ", getCollegeid()="
				+ getCollegeid() + "]";
	}
}
