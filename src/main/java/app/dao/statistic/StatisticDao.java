package app.dao.statistic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import app.common.Constant;
import app.dao.BaseDao;
import app.ddl.VisitPage;

@Repository("statisticDao")
public class StatisticDao extends BaseDao  {
	
	/**
	 * 访问量-PV
	 * @param param
	 */
	public int visitPV(int startTime, int endTime) {				
		String sql = " SELECT COUNT(1) " +
				" FROM PAGE_VISIT_LOG t " +
				" WHERE t.VISIT_TIME>=:startTime " +
				" AND t.VISIT_TIME<=:endTime ";
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		return getNamedParameterJdbcTemplate().queryForInt(sql, param);
	}
	
	/**
	 * 访问量-UV
	 * @param param
	 */
	public int visitUV(int startTime, int endTime) {
		String sql = " SELECT COUNT(DISTINCT t.SID) " +
				" FROM PAGE_VISIT_LOG t " +
				" WHERE t.VISIT_TIME>=:startTime " +
				" AND t.VISIT_TIME<=:endTime";
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		return getNamedParameterJdbcTemplate().queryForInt(sql, param);
	}
	
	/**
	 * 新访客数
	 * @param param
	 * @return
	 */
	public int visitNewUV(int startTime, int endTime) {
		String sql = " SELECT COUNT(1) " +
				" FROM PAGE_VISITOR_LOG t " +
				" WHERE t.FIRST_VISIT_TIME>=:startTime " +
				" AND t.FIRST_VISIT_TIME<=:endTime";
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		return getNamedParameterJdbcTemplate().queryForInt(sql, param);
	}
	
	/**
	 * 访问量-IP数
	 * @param param
	 */
	public int visitIP(int startTime, int endTime) {
		String sql = " SELECT COUNT(DISTINCT t.IP) " +
				" FROM PAGE_VISIT_LOG t " +
				" WHERE t.VISIT_TIME>=:startTime " +
				" AND t.VISIT_TIME<=:endTime ";
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		return getNamedParameterJdbcTemplate().queryForInt(sql, param);
	}
	
	/**
	 * 统计页面的PV
	 * @param pageId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int pageStatPV(long pageId, int startTime, int endTime) {
		String sql = " select count(1) from PAGE_VISIT_LOG t" +
				" WHERE t.VISIT_TIME>=:startTime " +
				" AND t.VISIT_TIME<=:endTime " +
				" AND t.PAGE_ID=:pageId";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("pageId", pageId);
		return getNamedParameterJdbcTemplate().queryForInt(sql, param);
	}
	
	/**
	 * 统计页面的PV
	 * @param pageId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int pageStatUV(long pageId, int startTime, int endTime) {		
		String sql = " select count(DISTINCT t.SID) from PAGE_VISIT_LOG t" +
				" WHERE t.VISIT_TIME>=:startTime " +
				" AND t.VISIT_TIME<=:endTime " +
				" AND t.PAGE_ID=:pageId";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("pageId", pageId);
		return getNamedParameterJdbcTemplate().queryForInt(sql, param);
	}
	
	/**
	 * 统计页面的IP
	 * @param pageId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int pageStatIP(long pageId, int startTime, int endTime) {
		String sql = " select count(DISTINCT t.IP) from PAGE_VISIT_LOG t" +
				" WHERE t.VISIT_TIME>=:startTime " +
				" AND t.VISIT_TIME<=:endTime " +
				" AND t.PAGE_ID=:pageId";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("pageId", pageId);
		return getNamedParameterJdbcTemplate().queryForInt(sql, param);
	}
	
	/**
	 * 入口页面次数统计，refer为空或者refer的域非目标网站
	 * @param pageId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int pageStatEnterCount(long pageId, int startTime, int endTime) {
		String sql = " select count(1) from PAGE_VISIT_LOG t " +
						   " WHERE t.VISIT_TIME>=:startTime " +
						   " AND t.VISIT_TIME<=:endTime " +
						   " AND t.PAGE_ID=:pageId " +
						   " AND (t.REFERER is null or t.REFERER not like '%" + Constant.TARGET_SITE_DOMAIN +"%')";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("pageId", pageId);
		return getNamedParameterJdbcTemplate().queryForInt(sql, param);
	}
	
	/**
	 * 页面跳出次数
	 * @param pageId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int pageStatOutCount(long pageId, int startTime, int endTime) {
		String sql = " select count(1) from page_stay_time_log t" +
				" WHERE t.VISIT_TIME>=:startTime " +
				" AND t.VISIT_TIME<=:endTime " +
				" AND t.PAGE_ID=:pageId " +
				" AND t.OUT_PAGE=1";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("pageId", pageId);
		return getNamedParameterJdbcTemplate().queryForInt(sql, param);
	}
	
	/**
	 * 页面平均加载时间
	 * @param pageId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public double pageAvgLoadTime(long pageId, int startTime, int endTime) {
		String sql = " select AVG(t.LOAD_EVENT) as loadTime from page_loadtime_log t " +
						  " WHERE t.VISIT_TIME>=:startTime " +
						  " AND t.VISIT_TIME<=:endTime " +
						  " AND t.PAGE_ID=:pageId ";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("pageId", pageId);
		List<Double> list = getNamedParameterJdbcTemplate().query(sql, param, new RowMapper<Double>() {
			@Override
			public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getDouble("loadTime");
			}
		});
		if(list != null && !list.isEmpty() && list.get(0) != null) {
			return list.get(0);
		}
		return 0.0;
	}
	
	/**
	 * 页面平均停留时间
	 * @param pageId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public double pageAvgStayTime(long pageId, int startTime, int endTime) {
		String sql = " select AVG(t.TIME1) as statTime " +
				  " from page_stay_time_log t " +
				  " WHERE t.VISIT_TIME>=:startTime " +
				  " AND t.VISIT_TIME<=:endTime " +
				  " AND t.PAGE_ID=:pageId ";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("pageId", pageId);
		List<Double> list = getNamedParameterJdbcTemplate().query(sql, param, new RowMapper<Double>() {
			@Override
			public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getDouble("statTime");
			}
		});
		if(list != null && !list.isEmpty() && list.get(0) != null) {
			return list.get(0);
		}
		return 0.0;
	}
	
	/**
	 * 查询一段时间内，某个页面的上游页面（来源页面）,最大返回50个
	 * @param pageId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<String> upperPages(long pageId, int startTime, int endTime) {
		String sql = " select t.REFERER AS referer from page_visit_log t" +
					      " WHERE t.VISIT_TIME>=:startTime " +
				          " AND t.VISIT_TIME<=:endTime " +
				          " AND t.PAGE_ID=:pageId " +
				          " AND t.REFERER IS NOT NULL " +
				          " LIMIT 0,50 ";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("pageId", pageId);
		List<String> result = getNamedParameterJdbcTemplate().query(sql, param, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("referer");
			}
		});
		return result;
	}
	
	/**
	 * 查询一段时间内，某个页面的下游页面（来源页面=当前页面）,最大返回50个
	 * @param pageId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<String> lowerPages(String url, int startTime, int endTime) {
		String sql = " select t.URL AS url from page_visit_log t" +
			      " WHERE t.VISIT_TIME>=:startTime " +
		          " AND t.VISIT_TIME<=:endTime " +
		          " AND t.REFERER=:url " +
		          " AND t.REFERER IS NOT NULL " +
		          " LIMIT 0,50 ";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("url", url);
		List<String> result = getNamedParameterJdbcTemplate().query(sql, param, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("url");
			}
		});
		return result;
	}
	
	
	/**
	 * 分页获取访问页面
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<VisitPage> listByPage(int offset, int limit) {
		
		String sql = " select id as id, url as pageUrl from PAGE order by visit_count desc limit :offset, :limit";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("offset", offset);
		param.put("limit", limit);
		return getNamedParameterJdbcTemplate().query(sql, param,
				ParameterizedBeanPropertyRowMapper.newInstance(VisitPage.class) );
	}
	
	/**
	 * 统计有多少个页面被访问过
	 * @return
	 */
	public int pageCount() {
		String sql = " select count(1) from PAGE";
		return getNamedParameterJdbcTemplate().queryForInt(sql, new HashMap<String, Object>());
	}
	
	/**
	 * //查找最近一段时间访问的浏览器种类
	 * @param startTime
	 * @param endTime
	 * @return 
	 */
	public List<String> findBrowsers( int startTime, int endTime) {	
		String sql = " select t.BROWSER from PAGE_VISIT_LOG t " +
				" WHERE t.VISIT_TIME>=:startTime " +
				" AND t.VISIT_TIME<=:endTime " +
				" group by t.BROWSER";
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		
		List<String> result = getNamedParameterJdbcTemplate().query(sql, param, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("BROWSER");
			}
		});
		return result;
	}
	
	/**
	 * //查找最近一段时间访问的操作系统种类
	 * @param startTime
	 * @param endTime
	 * @return
	 */

	public List<String> findOS( int startTime, int endTime) {
		String sql = " select t.OS from PAGE_VISIT_LOG t " +
				" WHERE t.VISIT_TIME>=:startTime " +
				" AND t.VISIT_TIME<=:endTime " +
				" group by t.OS";
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		
		List<String> result = getNamedParameterJdbcTemplate().query(sql, param, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("OS");
			}
		});
		return result;
	}
	
	/**
	 * 操作系统-PV
	 * @param param
	 */
	public int osPV(int startTime, int endTime, String os) {						
		String sql = " SELECT COUNT(1) " +
				" FROM PAGE_VISIT_LOG t " +
				" WHERE t.VISIT_TIME>=:startTime " +
				" AND t.VISIT_TIME<=:endTime " +
				" AND t.OS = :os";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("os", os);
		return getNamedParameterJdbcTemplate().queryForInt(sql, param);
	}
	
	/**
	 * 操作系统-UV
	 * @param param
	 */
	public int osUV(int startTime, int endTime, String os) {				
		String sql = " SELECT COUNT(DISTINCT t.SID) " +
				" FROM PAGE_VISIT_LOG t " +
				" WHERE t.VISIT_TIME>=:startTime " +
				" AND t.VISIT_TIME<=:endTime" +
				" AND t.OS = :os";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("os", os);
		return getNamedParameterJdbcTemplate().queryForInt(sql, param);
	}
	
	/**
	 * 操作系统-IP数
	 * @param param
	 */
	public int osIP(int startTime, int endTime, String os) {	
		String sql = " SELECT COUNT(DISTINCT t.IP) " +
				" FROM PAGE_VISIT_LOG t " +
				" WHERE t.VISIT_TIME>=:startTime " +
				" AND t.VISIT_TIME<=:endTime " +
				" AND t.OS = :os";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("os", os);
		return getNamedParameterJdbcTemplate().queryForInt(sql, param);
	}
	
	/**
	 * 操作系统-PV
	 * @param param
	 */
	public int browserPV(int startTime, int endTime, String browser) {	
		String sql = " SELECT COUNT(1) " +
				" FROM PAGE_VISIT_LOG t " +
				" WHERE t.VISIT_TIME>=:startTime " +
				" AND t.VISIT_TIME<=:endTime " +
				" AND t.BROWSER = :browser";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("browser", browser);
		return getNamedParameterJdbcTemplate().queryForInt(sql, param);
	}
	
	/**
	 * 操作系统-UV
	 * @param param
	 */
	public int browserUV(int startTime, int endTime, String browser) {	
		String sql = " SELECT COUNT(DISTINCT t.SID) " +
				" FROM PAGE_VISIT_LOG t " +
				" WHERE t.VISIT_TIME>=:startTime " +
				" AND t.VISIT_TIME<=:endTime" +
				" AND t.BROWSER = :browser";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("browser", browser);
		return getNamedParameterJdbcTemplate().queryForInt(sql, param);
	}
	
	/**
	 * 操作系统-IP数
	 * @param param
	 */
	public int browserIP(int startTime, int endTime, String browser) {
		String sql = " SELECT COUNT(DISTINCT t.IP) " +
				" FROM PAGE_VISIT_LOG t " +
				" WHERE t.VISIT_TIME>=:startTime " +
				" AND t.VISIT_TIME<=:endTime " +
				" AND t.BROWSER = :browser";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("browser", browser);
		return getNamedParameterJdbcTemplate().queryForInt(sql, param);
	}
	
	/**
	 * 根据id查找访问页面
	 * @param id
	 * @return
	 */
	public VisitPage findVisitPageById(Integer id) {
		String sql = " select * from page where id=:id";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		List<VisitPage> result = getNamedParameterJdbcTemplate().query(sql, param, new RowMapper<VisitPage>() {
			@Override
			public VisitPage mapRow(ResultSet rs, int rowNum) throws SQLException {
				VisitPage visitPage = new VisitPage();
				visitPage.setId(rs.getLong("id"));
				visitPage.setPageUrl(rs.getString("url"));
				return visitPage;
			}
		});
		if(result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
}
