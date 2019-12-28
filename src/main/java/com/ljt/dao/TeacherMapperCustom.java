package com.ljt.dao;

import java.util.List;

import com.ljt.bean.PagingVo;
import com.ljt.bean.TeacherCustom;

public interface TeacherMapperCustom {

	//分页查询老师信息
	List<TeacherCustom> findByPaging(PagingVo pagingVo) throws Exception;
}
