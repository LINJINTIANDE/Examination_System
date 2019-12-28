package mvc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ljt.controller.LoginController;

public class test {

	@SuppressWarnings("resource")
	@Test
	public void mvc() {
		ClassPathXmlApplicationContext ctx =
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		/*
		 * LoginController bean = ctx.getBean(LoginController.class);
		 * System.out.println("aaaaaaa"); System.out.println(bean);
		 */
	}
	
}
