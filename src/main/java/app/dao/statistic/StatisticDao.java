package app.dao.statistic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

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
	
}
