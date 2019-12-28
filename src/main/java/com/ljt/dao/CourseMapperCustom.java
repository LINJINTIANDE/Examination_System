package com.ljt.dao;


import java.util.List;

import com.ljt.bean.CourseCustom;
import com.ljt.bean.PagingVo;

/**
 * Created by Jacey on 2017/6/29.
 */
public interface CourseMapperCustom {

    //分页查询学生信息
    List<CourseCustom> findByPaging(PagingVo pagingVO) throws Exception;

}
