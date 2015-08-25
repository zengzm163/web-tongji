package app.common;

public class Constant {
	
	public static final String JF_TONGJI_SID = "JF_TONGJI_SID";
	
	/**************统计指标*****************/
	/**
	 * 统计指标：PV
	 */
	public static final String STAT_POINT_PV = "pv";
	
	/**
	 * 统计指标：UV
	 */
	public static final String STAT_POINT_UV = "uv";
	
	/**
	 * 统计指标：新访问
	 */
	public static final String STAT_POINT_NEW_UV = "new_uv";
	
	/**
	 * 统计指标：IP数
	 */
	public static final String STAT_POINT_IP = "ip";

	
	/**************统计时间类型*****************/
	
	/**
	 * 统计时间：实时
	 */
	public static final int STAT_TIME_TYPE_NOW = 0;
	
	/**
	 * 统计时间：当天
	 */
	public static final int STAT_TIME_TYPE_TODAY = 1;
	
	/**
	 * 统计时间：昨天
	 */
	public static final int STAT_TIME_TYPE_YESTERDAY = 2;
	
	/**
	 * 统计时间：最近7天
	 */
	public static final int STAT_TIME_TYPE_LASTEST_7 = 3;
	
	/**
	 * 统计时间：最近30天
	 */
	public static final int STAT_TIME_TYPE_LASTEST_30 = 4;
}
