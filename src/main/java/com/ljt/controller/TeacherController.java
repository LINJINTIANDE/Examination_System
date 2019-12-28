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
import com.ljt.bean.SelectedCourseCustom;
import com.ljt.service.CourseService;
import com.ljt.service.SelectedCourseService;

@Controller
@RequestMapping("teacher")
public class TeacherController {

	@Autowired
	CourseService courseService;
	
	@Autowired 
	SelectedCourseService selectedCourseService;
	
	
	
	//搜索课程
	//因为shiro admin 权限 改为在teacher路径下
	@RequestMapping(value="selectCourse",method= {RequestMethod.POST})
	public String selectCourse(String findByName,Model model) throws Exception {
		List<CourseCustom> list = courseService.findByName(findByName);
		
		model.addAttribute("courseList", list);
		
		 return "admin/showCourse";
	}
	
	
	
	//显示课程
	@RequestMapping("/showCourse")
	public String stuCourseShow(Model model) throws Exception{
		
		Subject subject = SecurityUtils.getSubject();
		String username = (String)subject.getPrincipal();
		
		List<CourseCustom> list = courseService.findByTeacherID(Integer.parseInt(username));
		model.addAttribute("courseList", list);
		
		return "teacher/showCourse";
	}
	
	//显示成绩
	@RequestMapping("gradeCourse")
	public String gradeCourse(Integer id,Model model)throws Exception {
		
		if(id==null) {
			return "";
		}
		
		List<SelectedCourseCustom> list = selectedCourseService.findBycourseID(id);
		System.out.println(list);
		model.addAttribute("selectedCourseList", list);
		return "teacher/showGrade";
	}
	
	//打分页面
	@RequestMapping(value="/mark",method=RequestMethod.GET)
	public String markUI(SelectedCourseCustom scc,Model model)throws Exception {
		SelectedCourseCustom selectedCourseCustom = selectedCourseService.findOne(scc);
		
		model.addAttribute("selectedCourse", selectedCourseCustom);
		
		return "teacher/mark";
	}
	
	//打分操作
	 @RequestMapping(value = "/mark", method = {RequestMethod.POST})
	public String mark(SelectedCourseCustom scc) throws Exception{
		 selectedCourseService.updateOne(scc);

	        return "redirect:/teacher/gradeCourse?id="+scc.getCourseid();
	}

    //修改密码
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "teacher/passwordRest";
    }
}
