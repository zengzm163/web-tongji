package app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.controller.base.BaseController;

@Controller
@RequestMapping("")
public class IndexController extends BaseController {
	@RequestMapping(value="/")
	public ModelAndView index() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		return responseView("/index", modelMap);
	}
}
