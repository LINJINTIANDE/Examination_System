package com.ljt.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ljt.bean.CourseCustom;
import com.ljt.bean.PagingVo;
import com.ljt.bean.SelectedCourseCustom;
import com.ljt.bean.StudentCustom;
import com.ljt.exception.CustomException;
import com.ljt.service.CourseService;
import com.ljt.service.SelectedCourseService;
import com.ljt.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	CourseService courseService;
	
	@Autowired
	SelectedCourseService selectedCourseService;
	
	@Autowired
	StudentService studentService;
	
	
	//搜索课程
	@RequestMapping(value="selectCourse",method= {RequestMethod.POST})
	public String selectCourse(String findByName,Model model) throws Exception {
		List<CourseCustom> list = courseService.findByName(findByName);
		
		model.addAttribute("courseList", list);
		
		 return "admin/showCourse";
	}
	
	
	
	//所有的课程
	@RequestMapping("/showCourse")
	public String stuCourseShow(Model model,Integer page)throws Exception {
		List<CourseCustom> list=null;
		
		//页码对象
		PagingVo pagingVo =new PagingVo();
		//设置总页数
		pagingVo.setTotalCount(courseService.getCountCouse());
		if(page == null ||page==0) {
			pagingVo.setToPageNo(1);
			list=courseService.findBypaging(1);
		}else {
			pagingVo.setToPageNo(page);
			list=courseService.findBypaging(page);
		}
		
		model.addAttribute("courseList", list);
		model.addAttribute("pagingVO", pagingVo);
		
		return "student/showCourse";
	}
	
	//选课操作
	 @RequestMapping(value = "/stuSelectedCourse")
	public String stuSelectedCourse(Integer id,Model model) throws Exception{
		System.out.println("aaaa");
		 //获取当前用户名
		Subject subject = SecurityUtils.getSubject();
		String usernaem=(String)subject.getPrincipal();
		
		SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
		selectedCourseCustom.setCourseid(id);
		selectedCourseCustom.setStudentid(Integer.parseInt(usernaem));
		
		
		SelectedCourseCustom s = selectedCourseService.findOne(selectedCourseCustom);
		
		if(s==null) {
			selectedCourseService.save(selectedCourseCustom);
			model.addAttribute("selectedCourseList",s);
		}else {
			throw new CustomException("该门课程你已经选了，不能再选");
		}
		
		return "redirect:/student/selectCourse";
		
	}
	 //退课操作
	 @RequestMapping(value="/outCourse")
	 public String outCourse(int id) throws Exception{
		 Subject subject = SecurityUtils.getSubject();
		 String username=(String) subject.getPrincipal();
		 
		 SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
		 selectedCourseCustom.setCourseid(id);
		 selectedCourseCustom.setStudentid(Integer.parseInt(username));
		 
		 selectedCourseService.remove(selectedCourseCustom);
		 
		 return "redirect:/student/selectedCourse";
	 }
	 
	 //已选课程
	 @RequestMapping("/selectedCourse")
	 public String selectedCourse(Model model) throws Exception{
		 Subject subject = SecurityUtils.getSubject();
		 String name=(String)subject.getPrincipal();
		 System.out.println(name);
		
		StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String)subject.getPrincipal());
		
		List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();
		
		model.addAttribute("selectedCourseList",list);
		
		return "student/selectCourse";
	 }
	 
	 //已修课程
	 @RequestMapping("/overCourse")
	 public String overCourse(Model model) throws Exception{
		 
		 //获取当前用户名
		 Subject subject = SecurityUtils.getSubject();
		 StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String)subject.getPrincipal());
		 
		 List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();
		 model.addAttribute("selectedCourseList", list);
		 
		 return "student/overCourse";
	 }
	
	 //修改密码
	 @RequestMapping(value="/passwordRest")
	 public String passwordRest() throws Exception{
		 return "student/passwordRest";
	 }
	  
}
