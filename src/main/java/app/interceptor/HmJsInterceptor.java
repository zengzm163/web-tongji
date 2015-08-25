package app.interceptor;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import app.common.Constant;
import app.common.CookieUtil;

/**
 * hm.js访问拦截器
 * @author Administrator
 *
 */
public class HmJsInterceptor implements  HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rsp,
			Object arg2) throws Exception {
		
		//判断请求cookie是否存在JF_TONGJI_SID值，如果不存在，认为是新访客，分配一个32位的uuid
		Cookie cookie = CookieUtil.getCookie(req, Constant.JF_TONGJI_SID);
		if(cookie == null) {
			System.out.println("新访客");
			Cookie newCookie = new Cookie(Constant.JF_TONGJI_SID, UUID.randomUUID().toString());
			newCookie.setMaxAge(Integer.MAX_VALUE);
			newCookie.setPath("/");
			rsp.addCookie(newCookie);
		}
		return true;
	}

}
