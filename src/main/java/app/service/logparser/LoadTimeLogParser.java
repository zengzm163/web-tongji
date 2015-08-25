package app.service.logparser;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.reflect.TypeToken;

import app.common.GsonUtil;
import app.ddl.LoadTimeLog;
import app.ddl.Log;

/**
 * 页面加载时间日志解析器
 * @author Administrator
 *
 */
public class LoadTimeLogParser extends LogParser {

	@Override
	public Log parse(HttpServletRequest request) {
		//String u = request.getParameter("u");
		String ep = request.getParameter("ep");
		
		LoadTimeLog loadTimeLog = new LoadTimeLog();
		super.setCommonData(request, loadTimeLog);
		loadTimeLog.setUrl(request.getHeader("Referer"));
		Map<String, Object> info =  GsonUtil.parse2Object(ep, new TypeToken<Map<String, Object>>(){});
		if(info != null) {
			if(info.get("netAll") != null) {
				loadTimeLog.setNetAll(Double.valueOf(info.get("netAll").toString()).longValue());
			}
			
			if(info.get("netDns") != null) {
				loadTimeLog.setNetDns(Double.valueOf(info.get("netDns").toString()).longValue());
			}
			
			if(info.get("netTcp") != null) {
				loadTimeLog.setNetTcp(Double.valueOf(info.get("netTcp").toString()).longValue());
			}
			
			if(info.get("srv") != null) {
				loadTimeLog.setSrv(Double.valueOf(info.get("srv").toString()).longValue());
			}
			
			if(info.get("dom") != null) {
				loadTimeLog.setDom(Double.valueOf(info.get("dom").toString()).longValue());
			}
			
			if(info.get("loadEvent") != null) {
				loadTimeLog.setLoadEvent(Double.valueOf(info.get("loadEvent").toString()).longValue());
			}
			
			if(info.get("qid") != null) {
				loadTimeLog.setQid(info.get("qid").toString());
			}
			
			if(info.get("bdDom") != null) {
				loadTimeLog.setBdDom(Double.valueOf(info.get("bdDom").toString()).longValue());
			}
			
			if(info.get("bdRun") != null) {
				loadTimeLog.setBdRun(Double.valueOf(info.get("bdRun").toString()).longValue());
			}
			
			if(info.get("bdDef") != null) {
				loadTimeLog.setBdDef(Double.valueOf(info.get("bdDef").toString()).longValue());
			}
		}
		return loadTimeLog;
	}

}
