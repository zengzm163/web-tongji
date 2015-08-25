package app.service.logparser;

import javax.servlet.http.HttpServletRequest;

import app.ddl.Log;
import app.ddl.VisitLog;

/**
 * 访问日志解析器
 * @author Administrator
 *
 */
public class VisitLogParser extends LogParser {

	@Override
	public Log parse(HttpServletRequest request) {
		VisitLog visitLog = new VisitLog();
		super.setCommonData(request, visitLog);
		visitLog.setUrl(request.getHeader("Referer"));
		return visitLog;
	}

}
