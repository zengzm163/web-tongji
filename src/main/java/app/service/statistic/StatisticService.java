package app.service.statistic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import app.common.Constant;
import app.common.DateUtil;
import app.common.PageResult;
import app.dao.statistic.StatisticDao;
import app.ddl.VisitPage;

@Service("statisticService")
public class StatisticService {
	
	@Resource(name="statisticDao")
	private StatisticDao statisticDao;
	
	/**
	 * 实时访问统计（最近30分钟）
	 * @param statPoints
	 * @return
	 */
	public Map<String, Integer> visitCurrStat(List<String> statPoints) {
		int endTime =  DateUtil.parseDateToInt(new Date());
		int startTime = endTime - 1800;
		Map<String, Integer> data = new HashMap<String, Integer>();
		for(String statPoint : statPoints) {
			int count = 0;
			if(Constant.STAT_POINT_PV .equals(statPoint)) {
				count = statisticDao.visitPV(startTime, endTime);
			} else if(Constant.STAT_POINT_UV.equals(statPoint)) {
				count = statisticDao.visitUV(startTime, endTime);
			} else if(Constant.STAT_POINT_NEW_UV.equals(statPoint)) {
				count = statisticDao.visitNewUV(startTime, endTime);
			} else if(Constant.STAT_POINT_IP.equals(statPoint)) {
				count = statisticDao.visitIP(startTime, endTime);
			}
			data.put(statPoint,  count);
		}
		return data;
	}
	
	public Map<String, Integer> visitRangeStat(List<String> statPoints, int startTime, int endTime) {
		Map<String, Integer> data = new HashMap<String, Integer>();
		for(String statPoint : statPoints) {
			int count = 0;
			if(Constant.STAT_POINT_PV .equals(statPoint)) {
				count = statisticDao.visitPV(startTime, endTime);
			} else if(Constant.STAT_POINT_UV.equals(statPoint)) {
				count = statisticDao.visitUV(startTime, endTime);
			} else if(Constant.STAT_POINT_NEW_UV.equals(statPoint)) {
				count = statisticDao.visitNewUV(startTime, endTime);
			} else if(Constant.STAT_POINT_IP.equals(statPoint)) {
				count = statisticDao.visitIP(startTime, endTime);
			}
			data.put(statPoint,  count);
		}
		return data;
	}
	
	/**
	 * 访问量时间范围统计
	 * @param statPoint 统计指标：PV、UV、IP
	 * @param spiltType 统计切分维度：小时、天、周、月、季度、年
	 * @return
	 */
	public List<Map<String, Object>> visitRangeStat(List<String> statPoints, List<Map<String, Integer>> timeRanges,  int spiltType) {

		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for(Map<String, Integer> m : timeRanges) {
			int startTime =  m.get("startTime");
			int endTime = m.get("endTime");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("timeText", getTimeText(startTime, spiltType));
			for(String statPoint : statPoints) {
				int count = 0;
				if(Constant.STAT_POINT_PV .equals(statPoint)) {
					count = statisticDao.visitPV(startTime, endTime);
				} else if(Constant.STAT_POINT_UV.equals(statPoint)) {
					count = statisticDao.visitUV(startTime, endTime);
				} else if(Constant.STAT_POINT_NEW_UV.equals(statPoint)) {
					count = statisticDao.visitNewUV(startTime, endTime);
				} else if(Constant.STAT_POINT_IP.equals(statPoint)) {
					count = statisticDao.visitIP(startTime, endTime);
				}
				data.put(statPoint,  count);
			}
			result.add(data);
		}		
		return result;
	}
	
	/**
	 * 根据统计时间类型，获取时间范围
	 * @param statTimeType
	 * @return
	 */
	public List<Map<String, Integer>> getTimeRanges( int statTimeType) {
		if(Constant.STAT_TIME_TYPE_TODAY == statTimeType) {
			return DateUtil.split2Hous(new Date());
		} else if(Constant.STAT_TIME_TYPE_YESTERDAY == statTimeType) {
			Date yestDate =DateUtil.addDay(new Date(), 1*-1) ;
			return DateUtil.split2Hous(yestDate);
		} else if(Constant.STAT_TIME_TYPE_LASTEST_7 == statTimeType) {
			return DateUtil.split2Day(6);
		} else if(Constant.STAT_TIME_TYPE_LASTEST_30 == statTimeType) {
			return DateUtil.split2Day(29);
		} else {
			return Collections.emptyList();
		}
	}
	
	/**
	 * 切分时间片
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String, Integer>> getTimeRanges(Date startDate, Date endDate) {
		int unixStartDate = DateUtil.parseDateToInt(startDate);
		int unixEndDate = DateUtil.parseDateToInt(endDate);
		
		int spiltType = 0;		
		if(unixEndDate - unixStartDate <= 48*3600) {
			//如果开始时间~结束时间之间的范围在48小时内，按小时进行切分
			spiltType = DateUtil.DATE_SPILT_TYPE_HOUR;
		} else {
			//否则按天进行切分
			spiltType = DateUtil.DATE_SPILT_TYPE_DAY;
		}
		
		return DateUtil.spiltDate(startDate, endDate, spiltType);
	}
	
	/**
	 * 
	 * @param time
	 * @param spiltType
	 * @return
	 */
	private String getTimeText(int time, int spiltType) {
		if( DateUtil.DATE_SPILT_TYPE_HOUR == spiltType ) {
			return DateUtil.formatDateToString(DateUtil.unixTime2Date(time), "yyyy-MM-dd HH:mm");
		}  else if(DateUtil.DATE_SPILT_TYPE_DAY == spiltType ) {
			return DateUtil.formatDateToString(DateUtil.unixTime2Date(time), "yyyy-MM-dd");
		}  else {
			return "";
		}
	}
	
	/**
	 * 页面统计
	 * @param startTime
	 * @param endTime
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageResult<Map<String, Object>> pageStat(List<String> statPoints, int startTime, 
			int endTime, int offset, int limit) {
		PageResult<Map<String, Object>> pageResult = new PageResult<Map<String,Object>>();
		int total = statisticDao.pageCount();
		pageResult.setTotal(total);
		if(total <= 0) {
			pageResult.setRows(Collections.<Map<String, Object>> emptyList());
			return pageResult;
		}

		List<VisitPage> pages = statisticDao.listByPage(offset, limit);
		if(pages ==null || pages.isEmpty()) {
			pageResult.setRows(Collections.<Map<String, Object>> emptyList());
			return pageResult;
		}
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		
		for(VisitPage page : pages) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("pageUrl", page.getPageUrl());
			long pageId = page.getId();
			for(String statPoint : statPoints) {
				int count = 0;
				if(Constant.STAT_POINT_PV .equals(statPoint)) {
					count = statisticDao.pageStatPV(pageId, startTime, endTime);
				} else if(Constant.STAT_POINT_UV.equals(statPoint)) {
					count = statisticDao.pageStatUV(pageId, startTime, endTime);
				} else if(Constant.STAT_POINT_IP.equals(statPoint)) {
					count = statisticDao.pageStatIP(pageId, startTime, endTime);
				}
				m.put(statPoint,  count);
			}
			result.add(m);
		}
		pageResult.setRows(result);
		return pageResult;
	}
	
	/**
	 * 浏览器统计
	 * @param statPoints
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<Map<String, Object>> browserStat(List<String> statPoints, int startTime, int endTime) {
		List<String> browsers = statisticDao.findBrowsers(startTime, endTime);
		if(browsers == null || browsers.isEmpty()) {
			return Collections.emptyList();
		}
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for(String browser : browsers) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("browser", browser);
			for(String statPoint : statPoints) {
				int count = 0;
				if(Constant.STAT_POINT_PV .equals(statPoint)) {
					count = statisticDao.browserPV(startTime, endTime, browser);
				} else if(Constant.STAT_POINT_UV.equals(statPoint)) {
					count = statisticDao.browserUV(startTime, endTime, browser);
				} else if(Constant.STAT_POINT_IP.equals(statPoint)) {
					count = statisticDao.browserIP(startTime, endTime, browser);
				}
				m.put(statPoint,  count);
			}
			result.add(m);
		}
		return result;
	}
	
	/**
	 * 操作系统统计
	 * @param statPoints
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<Map<String, Object>> osStat(List<String> statPoints, int startTime, int endTime) {
		List<String> oses = statisticDao.findOS(startTime, endTime);
		if(oses == null || oses.isEmpty()) {
			return Collections.emptyList();
		}
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for(String os : oses) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("os", os);
			for(String statPoint : statPoints) {
				int count = 0;
				if(Constant.STAT_POINT_PV .equals(statPoint)) {
					count = statisticDao.osPV(startTime, endTime, os);
				} else if(Constant.STAT_POINT_UV.equals(statPoint)) {
					count = statisticDao.osUV(startTime, endTime, os);
				} else if(Constant.STAT_POINT_IP.equals(statPoint)) {
					count = statisticDao.osIP(startTime, endTime, os);
				}
				m.put(statPoint,  count);
			}
			result.add(m);
		}
		return result;
	}
	
}
