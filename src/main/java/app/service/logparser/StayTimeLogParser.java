package app.service.logparser;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import app.ddl.Log;
import app.ddl.StayTimeLog;

/**
 * 页面停留时间日志解析器
 * @author Administrator
 *
 */
public class StayTimeLogParser extends LogParser{

	@Override
	public Log parse(HttpServletRequest request) {
		String u = request.getParameter("u");
		String ep = request.getParameter("ep");
		
		StayTimeLog stayTimeLog = new StayTimeLog();
		super.setCommonData(request, stayTimeLog);
		if(StringUtils.isNotEmpty(ep)) {
			String[] arr = ep.split(",");
			if(arr != null && arr.length == 2) {
				stayTimeLog.setTime1(Long.valueOf(arr[0]));
				stayTimeLog.setTime2(Long.valueOf(arr[1]));
			}
		}			
		
		if(StringUtils.isEmpty(u)) {
			stayTimeLog.setOutPage(StayTimeLog.OUT_PAGE_YES);
			stayTimeLog.setUrl(request.getHeader("Referer"));
		} else {
			stayTimeLog.setOutPage(StayTimeLog.OUT_PAGE_NO);
			stayTimeLog.setUrl(u);
		}
		return stayTimeLog;
	}

}
