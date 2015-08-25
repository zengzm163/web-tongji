package app.controller.base;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;



public class BaseController {
	
	/**
	 * 跳转到指定视图进行response
	 * @param viewName
	 * @param modelMap
	 * @return
	 */
	protected ModelAndView responseView(String viewName, Map<String, Object> modelMap) {
		ModelAndView modelAndView = new ModelAndView(viewName, modelMap);
		//modelAndView.addAllObjects(modelMap);
		return modelAndView;
	}
	
}
