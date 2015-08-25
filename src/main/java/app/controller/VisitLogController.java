package app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.controller.base.BaseController;
import app.ddl.Log;
import app.service.logparser.LogParser;
import app.service.logparser.LogParserFactory;
import app.service.logpool.LogPool;
@Controller
@RequestMapping("/vistiLog")
public class VisitLogController extends BaseController {
	
	
	
	@RequestMapping("/record")
	public @ResponseBody String record(HttpServletRequest request, HttpServletResponse response) {
		
		/*Map<String, String[]> m = request.getParameterMap();
		for(String key : m.keySet()) {			
			System.out.print(key + "：" + org.springframework.util.StringUtils.arrayToCommaDelimitedString(m.get(key))  + "  " );			
		}
		System.out.println();*/
		
		String et = request.getParameter("et");
		String u = request.getParameter("u");
		String ep = request.getParameter("ep");
		
		//根据et，判断日志类型
		if(StringUtils.isEmpty(et)) {
			//System.out.println("日志时间类型为空，跳过");
			return "";
		}
		
		//创建日志解析器
		LogParser logParser = LogParserFactory.createParser(et);
		if(logParser == null) {
			//System.out.println("无法识别的日志类型，跳过");
			return "";
		}
		
		//解析日志
		Log log = logParser.parse(request);
		//将日志丢进缓冲池
		LogPool.getInstance().push(log);
		
		return "";
	}
	
	
	
	
	public static void main(String[] args) {
		/*System.out.println(URLDecoder.decode("http://localhost:8080/tongji/vistiLog/record?cc=1&ck=1&cl=24-bit&ds=1366x768&ep=399507%2C6079&et=3&" +
				"fl=15.0&ja=1&ln=zh-CN&lo=0&lt=1436783172&nv=0&rnd=1502622804&si=cc7c68ca6e0732df36608aed8e72564c&st=4&v=1.0.94&lv=2&" +
				"u=http%3A%2F%2Flocalhost%3A8080%2Ftongji%2Fstatistic%2Findex"));
		System.out.println(URLDecoder.decode("http://localhost:8080/tongji/vistiLog/record?cc=1&ck=1&cl=24-bit&ds=1366x768&et=0&fl=15.0&ja=1&ln=zh-CN&lo=0&lt=1436783172&nv=0&rnd=420512&si=cc7c68ca6e0732df36608aed8e72564c&st=4&v=1.0.94&lv=2"));

		System.out.println(URLDecoder.decode("http://localhost:8080/tongji/vistiLog/record?cc=1&ck=1&cl=24-bit&ds=1366x768&ep=%7B%22netAll%22%3A860%2C%22netDns%22%3A0%2C%22netTcp%22%3A0%2C%22srv%22%3A6%2C%22dom%22%3A1545%2C%22loadEvent%22%3A2160%2C%22qid%22%3A%22%22%2C%22bdDom%22%3A0%2C%22bdRun%22%3A0%2C%22bdDef%22%3A0%7D&et=87&fl=15.0&ja=1&ln=zh-CN&lo=0&lt=1436783172&nv=0&rnd=565925094&si=cc7c68ca6e0732df36608aed8e72564c&st=4&v=1.0.94&lv=2"));
		https://log.hm.baidu.com/hm.gif?cc=1&ck=1&cl=24-bit&ds=1366x768&ep=%5B%7Bx%3A286%2Cy%3A34%2Ct%3Aa%2Cu%3Ahttps%253A%252F%252Fwww.jointforce.com%252Fregister%252Findex%7D%5D&et=2&fl=15.0&ja=1&ln=zh-CN&lo=0&lt=1436799258&nv=0&rnd=1724123356&si=cc7c68ca6e0732df36608aed8e72564c&st=4&v=1.0.94&lv=3&u=https%3A%2F%2Fwww.jointforce.com%2Fhome
			System.out.println(URLDecoder.decode("https://log.hm.baidu.com/hm.gif?cc=1&ck=1&cl=24-bit&ds=1366x768&ep=%5B%7Bx%3A286%2Cy%3A34%2Ct%3Aa%2Cu%3Ahttps%253A%252F%252Fwww.jointforce.com%252Fregister%252Findex%7D%5D&et=2&fl=15.0&ja=1&ln=zh-CN&lo=0&lt=1436799258&nv=0&rnd=1724123356&si=cc7c68ca6e0732df36608aed8e72564c&st=4&v=1.0.94&lv=3&u=https%3A%2F%2Fwww.jointforce.com%2Fhome"));
	*/
		/*System.out.println(UUID.randomUUID().toString());
		System.out.println(UUID.randomUUID().toString());
		System.out.println(UUID.randomUUID().toString());
		System.out.println(UUID.randomUUID().toString());
		System.out.println(UUID.randomUUID().toString());*/
		System.out.println(Integer.parseInt("-587"));
	}
	
}
