package mysql;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ljt.bean.College;
import com.ljt.bean.Course;
import com.ljt.dao.CollegeMapper;
import com.ljt.dao.CourseMapper;

public class test {

	
	
	@SuppressWarnings("resource")
	@Test
	public void mysql() throws SQLException {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		
		
		
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
		
		CourseMapper bean = ctx.getBean(CourseMapper.class);
		Course selectByPrimaryKey = bean.selectByPrimaryKey(1);
		System.out.println(selectByPrimaryKey);
		
	}
}
