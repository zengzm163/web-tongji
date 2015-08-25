package app.dto;

/**
 * 统计参数
 * @author Administrator
 *
 */
public class StatisticParam {
	
	/**
	 * 统计指标：PV UV IP 等
	 */
	public int point;
	
	/**
	 * 周期类型：进30分钟、当天、近7天、近30天、自由选择设计
	 */
	public int periodType;
	
	/**
	 * 统计开始时间
	 */
	public int startTime;
	
	/**
	 * 统计结束时间
	 */
	public int endTime;
	
}
