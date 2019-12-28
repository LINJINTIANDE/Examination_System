package com.ljt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ljt.bean.College;
import com.ljt.bean.CourseCustom;
import com.ljt.bean.PagingVo;
import com.ljt.bean.StudentCustom;
import com.ljt.bean.TeacherCustom;
import com.ljt.bean.Userlogin;
import com.ljt.exception.CustomException;
import com.ljt.service.CollegeService;
import com.ljt.service.CourseService;
import com.ljt.service.StudentService;
import com.ljt.service.TeacherService;
import com.ljt.service.UserLoginService;
import com.sun.javafx.sg.prism.NGShape.Mode;
import com.sun.org.apache.regexp.internal.RE;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	CourseService courseService ;
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	CollegeService collegeService;
	
	@Autowired
	UserLoginService userLoginService;
	
	
	 /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<其他操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	//普通用户账号密码重置页面
	@RequestMapping("userPasswordRest")
	public String userPasswordRestUI() throws Exception{
		return "admin/userPasswordRest";
	}
	
	//普通用户账号密码重置处理
	@RequestMapping(value="/userPasswordRest", method=RequestMethod.POST)
	public String userPasswordRest(Userlogin userlogin) throws Exception{
		Userlogin name = userLoginService.findByName(userlogin.getUsername());
		if(name!=null) {
			if(name.getRole()==0) {
				throw new CustomException("该账户为管理员账户");
			}
			name.setPassword(userlogin.getPassword());
			userLoginService.updateByName(userlogin.getUsername(), name);
		}else {
			throw new CustomException("没有找到该用户");
		}
		return "admin/userPasswordRest";
		
	}
	//本账户密码重置
	@RequestMapping("/passwordRest")
	public String passwordRestUI() throws Exception{
		return "admin/passwordRest";
	}
	
	 /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<教师操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	//教师页面显示
	@RequestMapping("/showTeacher")
	public String showTeacher(Model model,Integer page)throws Exception{
		List<TeacherCustom> list = null;
		PagingVo pagingVo = new PagingVo();
		//设置总页数
		pagingVo.setTotalCount(teacherService.getCountTeacher());
		if(page==null||page==0) {
			pagingVo.setToPageNo(1);
			list=teacherService.findByPaing(1);
		}else {
			pagingVo.setToPageNo(page);
			list=teacherService.findByPaing(page);
		}
		model.addAttribute("teacherList", list);
		model.addAttribute("pagingVO", pagingVo);
		
		return "admin/showTeacher";
	}
	
	//添加教师信息
	@RequestMapping(value="/addTeacher",method=RequestMethod.GET)
	public String addTeacherUI(Model model) throws Exception{
		List<College> list = collegeService.finAll();
		model.addAttribute("collegeList", list);
		
		return "admin/addTeacher";
	}
	
	//添加教师信息处理
	@RequestMapping(value="/addTeacher",method=RequestMethod.POST)
	public String addTeacher(TeacherCustom teacherCustom,Model model) throws Exception{
		Boolean result = teacherService.save(teacherCustom);
		
		if(!result) {
			model.addAttribute("message", "工号重复");
			return "error";
		}
		//添加成功后，也添加到登录表中
		Userlogin userlogin = new Userlogin();
		userlogin.setUsername(teacherCustom.getUserid().toString());
		userlogin.setPassword("123");
		userlogin.setRole(1);
		userLoginService.save(userlogin);
		
		return "redirect:/admin/showTeacher";
	}
	
	//修改教师信息页面显示
	@RequestMapping(value="editTeacher",method=RequestMethod.GET)
	public String editTeacherUI(Integer id,Model model) throws Exception{
		if(id==null) {
			return "redirect:./admin/showTeacher";
		}
		TeacherCustom teacherCustom = teacherService.findById(id);
		if(teacherCustom==null) {
			throw new CustomException("未找到该名教师 ");
		}
		
		List<College> college = collegeService.finAll();
		
		model.addAttribute("collegeList", college);
		model.addAttribute("teacher", teacherCustom);
		
		return "admin/editTeacher";
	}
	
	//修改教师信息页面处理
	@RequestMapping(value="editTeacher",method=RequestMethod.POST)
	public String editTeacher(TeacherCustom teacherCustom)throws Exception{
		teacherService.updateByteacherCustom(teacherCustom);
		
		return "redirect:/admin/showTeacher";
	}
	
	//删除教师
	@RequestMapping("/removeTeacher")
	public String removeTeacher(Integer id) throws Exception{
		if(id==null) {
			return "admin/showTeacher";
		}
		teacherService.removeById(id);
		userLoginService.removeByName(id.toString());
		return "redirect:/admin/showTeacher";
	}
	
	//搜索教师
	@RequestMapping(value="selectTeacher",method=RequestMethod.POST)
	public String selectTeacher(String findByName,Model model)throws Exception {
			List<TeacherCustom> list = teacherService.findByName(findByName);
			
			model.addAttribute("teacherList", list);
			return "admin/showTeacher";
			
		}
	/*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<学生操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	
	//显示学生信息
	@RequestMapping("/showStudent")
	public String showStudent(Model model,Integer page)throws Exception{
		
	
		List<StudentCustom> list= null;
		//页码对象
		PagingVo pagingVo = new PagingVo();
		//设置总页码
		pagingVo.setTotalCount(studentService.getCountStudent());
		if(page == null||page==0) {
			pagingVo.setToPageNo(1);;
			list=studentService.findByPaging(1);
		}else {
			pagingVo.setToPageNo(page);
			list=studentService.findByPaging(page);
		}
		model.addAttribute("studentList", list);
		model.addAttribute("pagingVO", pagingVo);
		
		return "admin/showStudent";
	}
	
	//添加学生信息页面显示
	@RequestMapping(value="/addStudent",method= {RequestMethod.GET})
	public String addStudentUI(Model model) throws Exception{
		List<College> finAll = collegeService.finAll();
		
		model.addAttribute("collegeList", finAll);
		
		return "admin/addStudent";
	}
	
	//添加学生信息操作
	@RequestMapping(value = "/addStudent", method = {RequestMethod.POST})
	public String addStudent(StudentCustom studentCustom,Model model) throws Exception{
		Boolean result = studentService.save(studentCustom);
		
		if(!result) {
			model.addAttribute("message","学号重复");
			return "error";
		}
		
		//添加成功后，也添加到登录表
		Userlogin userlogin = new Userlogin();
		userlogin.setUsername(studentCustom.getUserid().toString());
		userlogin.setPassword("123");
		userlogin.setRole(2);
		userLoginService.save(userlogin);
		//重定向
		return "redirect:/admin/showStudent";
	}
	
	//修改学生信息页面显示
	@RequestMapping(value="/editStudent",method= {RequestMethod.GET})
	public String editStudentUI(Integer id,Model model) throws Exception{
		if(id==null) {
			//加入没有带学生id就进来的话返回学生显示页面
			return "redirect:/admin/showStudent";
		}
		
		StudentCustom studentCustom = studentService.findById(id);
		if(studentCustom==null) {
			throw new CustomException("未找到该名学生");
		}
		List<College> list = collegeService.finAll();
		
		model.addAttribute("collegeList", list);
		model.addAttribute("student", studentCustom);
		
		return "admin/editStudent";
	}
	
	//修改学生信息处理
	@RequestMapping(value="/editStudent",method= {RequestMethod.POST})
	public String editStudent(StudentCustom studentCustom) throws Exception {
		studentService.updateBystudentCustom(studentCustom);
		
		return "redirect:/admin/showStudent";
	}
	
	//删除学生
	@RequestMapping(value="/removeStudent", method= {RequestMethod.GET})
	public String removeStudent(Integer id) throws Exception{
		if(id==null) {
		//没有加入学生id就进来的话就返回学生页面
			return "admin/showStudent";
		}
		studentService.removeByID(id);
		userLoginService.removeByName(id.toString());
		
		return "redirect:/admin/showStudent";
	}
	
	//搜索学生
	@RequestMapping(value="selectStudent",method= {RequestMethod.POST})
	public String selectStudent(String findByName,Model model) throws Exception{
		List<StudentCustom> list = studentService.findByname(findByName);
		model.addAttribute("studentList", list);
		
		return "admin/showStudent";
	}
	
	 /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<课程操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	
	//课程信息显示
	@RequestMapping("/showCourse")
	public String  showCourse(Model model,Integer page) throws Exception {
	
		List<CourseCustom> list = null;
		//页码对象
		PagingVo pagingVo  = new PagingVo();
		//设置总页数
		
		pagingVo.setTotalCount(courseService.getCountCouse());
		if(page == null ||page==0) {
			pagingVo.setToPageNo(1);
			list=courseService.findBypaging(1);
		}else {
			pagingVo.setToPageNo(page);
			list =courseService.findBypaging(page);
		}
		
		model.addAttribute("courseList",list);
		model.addAttribute("pagingVO",pagingVo);
		
		return "admin/showCourse";
	}
	//添加课程
	@RequestMapping(value="/addCourse", method= {RequestMethod.GET})
	public String addCourseUI(Model model) throws Exception{
		List<TeacherCustom> list =teacherService.findAll();
		List<College> collegeList = collegeService.finAll();
		
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("teacherList",list);
		
		return "admin/addCourse";
	}
	
	//添加课程信息处理
	@RequestMapping(value="/addCourse",method= {RequestMethod.POST})
	public String addCourse(CourseCustom courseCustom,Model model ) throws Exception {
		Boolean result = courseService.save(courseCustom);
		
		if(!result) {
			model.addAttribute("message","课程号重复");
			return "error";
		}
		return "redirect:/admin/showCourse";
	}
	
	//修改课程信息页面显示
	@RequestMapping(value="/editCourse", method= {RequestMethod.GET})
	public String editCourseUI(Integer id,Model model) throws Exception {
		if(id == null) {
			return "redirect:/admin/showCourse";
		}
		
		CourseCustom courseCustom = courseService.findById(id);
		if(courseCustom==null) {
			throw new CustomException("未找到改课程");
		}
		List<TeacherCustom> list = teacherService.findAll();
		List<College> collegeList = collegeService.finAll();
	
		
		model.addAttribute("teacherList", list);
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("course", courseCustom);
		
		return "admin/editCourse";
	}
	//修改课程信息页面处理
	@RequestMapping(value="/editCourse",method= {RequestMethod.POST})
	public String editCourse(CourseCustom courseCustom)throws Exception{
		courseService.updateBycourseCustom(courseCustom);
		
		return "redirect:/admin/showCourse";
	}
	//删除课程
	@RequestMapping("/removeCourse")
	public String removeCourse(Integer id) throws Exception{
		if(id==null) {
			//没有加入带教师id就进来的话就返回教师显示页面
			return "admin/showCourse";
		}
		  Boolean removeById = courseService.removeById(id);
		 
		return "redirect:/admin/showCourse";
	}
	
	//搜索课程
	@RequestMapping(value="selectCourse",method= {RequestMethod.POST})
	public String selectCourse(String findByName,Model model) throws Exception {
		List<CourseCustom> list = courseService.findByName(findByName);
		
		model.addAttribute("courseList", list);
		
		 return "admin/showCourse";
	}
}
