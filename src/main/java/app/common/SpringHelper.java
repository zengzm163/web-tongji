package app.common;

import org.springframework.web.context.WebApplicationContext;

/**
 * spring框架帮助类
 * @author Administrator
 *
 */
public class SpringHelper {
	
	private static WebApplicationContext webApplicationContext;

	public static void setWebApplicationContext(
			WebApplicationContext webApplicationContext) {
		SpringHelper.webApplicationContext = webApplicationContext;
	}
	
	public static Object getBean(String beanName) {
		return webApplicationContext.getBean(beanName);
	}
	
	public static <T> T getBean(Class<T> clazz) {
		return webApplicationContext.getBean(clazz);
	}
	
}
