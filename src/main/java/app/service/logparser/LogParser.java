package app.service.logparser;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import eu.bitwalker.useragentutils.UserAgent;

import app.common.Constant;
import app.common.CookieUtil;
import app.common.DateUtil;
import app.ddl.BaseLog;
import app.ddl.Log;

public abstract class LogParser {
	
	abstract public Log parse(HttpServletRequest request);
	
	protected void setCommonData(HttpServletRequest request, BaseLog log) {
		//String cc = request.getParameter("cc");
		//String ck = request.getParameter("ck");
		String cl = request.getParameter("cl");
		String ds = request.getParameter("ds");
		//String ep = request.getParameter("ep");
		//String et = request.getParameter("et");
		//String fl = request.getParameter("fl");
		//String ja = request.getParameter("ja");
		//String ln = request.getParameter("ln");
		//String lo = request.getParameter("lo");
		//String lt = request.getParameter("lt");
		String rnd = request.getParameter("rnd");
		//String si = request.getParameter("si");
		//String st = request.getParameter("st");
		//String v = request.getParameter("v");
		//String lv = request.getParameter("lv");
		//String u = request.getParameter("u");
		String su = request.getParameter("su");
		log.setCl(cl);
		log.setDs(ds);
		log.setReferer(su);
		log.setVisitTime(DateUtil.parseDateToInt(new Date()));
		log.setIp(getClientIP(request));
		String ua = request.getHeader("User-Agent");
		log.setUa(ua);
		UserAgent userAgent = new UserAgent(ua);
		log.setBrowser(userAgent.getBrowser().getName());
		log.setOs(userAgent.getOperatingSystem().getName());
		log.setRnd(Long.valueOf(rnd));
		Cookie cookie = CookieUtil.getCookie(request, Constant.JF_TONGJI_SID);
		if(cookie != null) {
			log.setSid(cookie.getValue());
		}
	}
	
	/*** 
	 * 获取客户端IP地址;这里通过了Nginx获取;X-Real-IP, 
	 * @param request 
	 * @return 
	 */  
	private  String getClientIP(HttpServletRequest request) {  
	    //String fromSource = "X-Real-IP";  
	    String ip = request.getHeader("X-Real-IP");  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("X-Forwarded-For");  
	        //fromSource = "X-Forwarded-For";  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	        //fromSource = "Proxy-Client-IP";  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	        //fromSource = "WL-Proxy-Client-IP";  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	        //fromSource = "request.getRemoteAddr";  
	    }  
	    return ip;  
	}  
	
}
