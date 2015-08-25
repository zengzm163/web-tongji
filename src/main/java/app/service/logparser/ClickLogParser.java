package app.service.logparser;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import app.common.GsonUtil;
import app.ddl.ClickLog;
import app.ddl.Log;

import com.google.gson.reflect.TypeToken;

/**
 * 页面点击日志解析器
 * @author Administrator
 *
 */
public class ClickLogParser extends LogParser {

	@Override
	public Log parse(HttpServletRequest request) {		
		String u = request.getParameter("u");
		String ep = request.getParameter("ep");
		
		ClickLog clickLog = new ClickLog();
		super.setCommonData(request, clickLog);
		if(StringUtils.isNotEmpty(u)) {
			clickLog.setUrl(u);
		} else {
			clickLog.setUrl(request.getHeader("Referer"));
		}
		
		List<Map<String, Object>> infos = GsonUtil.parse2Object(ep, new TypeToken<List<Map<String, Object>>>(){});
		if(infos != null && infos.size() > 0) {
			Map<String, Object> infoM = infos.get(0);
			if(infoM.get("x") != null) {
				clickLog.setX(Double.parseDouble(infoM.get("x").toString()));
			}
			
			if(infoM.get("y") != null) {
				clickLog.setY(Double.parseDouble(infoM.get("y").toString()));
			}
			
			if(infoM.get("t") != null) {
				clickLog.setTag(infoM.get("t").toString());
			}
			
			if(infoM.get("u") != null) {
				try {
					clickLog.setTagUrl(URLDecoder.decode(infoM.get("u").toString(), "utf-8"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		return clickLog;
	}

}
