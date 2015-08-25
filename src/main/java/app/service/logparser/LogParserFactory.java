package app.service.logparser;

/**
 * 日志解析器工厂
 * @author Administrator
 *
 */
public abstract class LogParserFactory {
	public static LogParser createParser(String et) {
		if("0".equals(et)) {
			return new VisitLogParser();
		} else if("3".equals(et)) {
			return new StayTimeLogParser();
		} else if("2".equals(et)) {
			return new ClickLogParser();
		} else if("87".equals(et)) {
			return new LoadTimeLogParser();
		} else {
			System.out.println("无法识别的日志类型，et：" + et);
			return null;
		}
	}
}
