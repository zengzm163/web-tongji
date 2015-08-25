package app.service.logpool;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import app.common.DateUtil;
import app.dao.log.LogDao;
import app.ddl.ClickLog;
import app.ddl.LoadTimeLog;
import app.ddl.Log;
import app.ddl.StayTimeLog;
import app.ddl.VisitLog;
import app.ddl.VisitPage;
import app.ddl.VisitorLog;

@Service("logService")
public class LogService {
	
	@Resource(name="logDao")
	private LogDao logDao;
	
	public void saveLog(Log log) {
		if(log instanceof VisitLog) {
			VisitLog visitLog = (VisitLog)log;
			long pageId = 0;
			if(!logDao.updateVisitPage(visitLog.getUrl())) {
				//新页面，插入
				VisitPage visitPage = new VisitPage();
				visitPage.setPageUrl(visitLog.getUrl());
				visitPage.setVisitCount(1);
				pageId = logDao.createVisitPage(visitPage);
			} else {
				pageId = logDao.findPageIdByUrl(visitLog.getUrl());
			}
			
			visitLog.setPageId(pageId);
			logDao.createVisitLog(visitLog);
			
			//处理访客信息			
			//尝试更新最后一次访问时间
			if(StringUtils.isNotEmpty(visitLog.getSid()) && 
					logDao.updateLastVisitTime(visitLog.getSid()) <= 0) {
				//没有更新到记录，认为是首次访问，创建访客信息
				VisitorLog visitorLog = new VisitorLog();
				visitorLog.setUid(visitLog.getSid());
				visitorLog.setIp(visitLog.getIp());
				visitorLog.setLastVisitTime(DateUtil.parseDateToInt(new Date()));
				visitorLog.setFirstVisitTime(DateUtil.parseDateToInt(new Date()));
				logDao.createVisitorLog(visitorLog);
			}
			
			
			
		} else if(log instanceof StayTimeLog) {
			StayTimeLog stayTimeLog = (StayTimeLog)log;
			
			if(stayTimeLog.getOutPage() == StayTimeLog.OUT_PAGE_NO) {
				logDao.updateStayTimeLogByRnd(stayTimeLog.getRnd());
			} else {
				stayTimeLog.setPageId(logDao.findPageIdByUrl(stayTimeLog.getUrl()));
				logDao.createStayTimeLog(stayTimeLog);
			}
		} else if(log instanceof ClickLog) {
			ClickLog clickLog = (ClickLog)log;
			clickLog.setPageId(logDao.findPageIdByUrl(clickLog.getUrl()));
			logDao.createClickLog(clickLog);
		} else if(log instanceof LoadTimeLog) {
			LoadTimeLog loadTimeLog = (LoadTimeLog)log;
			loadTimeLog.setPageId(logDao.findPageIdByUrl(loadTimeLog.getUrl()));
			logDao.createLoadTimeLog(loadTimeLog);
		}
	}
	
}
