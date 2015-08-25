package app.dao.log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import app.common.DateUtil;
import app.dao.BaseDao;
import app.ddl.ClickLog;
import app.ddl.LoadTimeLog;
import app.ddl.StayTimeLog;
import app.ddl.VisitLog;
import app.ddl.VisitPage;
import app.ddl.VisitorLog;

@Repository("logDao")
public class LogDao extends BaseDao {
	
	/*public void test() {
		String sql = " select * from ka_scene limit 1, 10";
		System.out.println("sql:" + sql);
		
		
		getNamedParameterJdbcTemplate().query(sql, new HashMap<String, Object>(), new RowMapper() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				System.out.println(rs.getString("name"));
				return "";
			}
		});
	}*/
	
	/**
	 * 创建访问日志
	 * @param visitLog
	 * @return
	 */
	public boolean createVisitLog(VisitLog visitLog) {
		String sql = " insert into PAGE_VISIT_LOG(URL, REFERER, IP, SID, VISIT_TIME, UA, OS, BROWSER, CL, DS, RND, PAGE_ID)" +
				" values(  :url, :referer, :ip, :sid, :visitTime, :ua,  :os,  :browser,  :cl,  :ds,  :rnd, :pageId)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int x = getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(visitLog), keyHolder);
		return x > 0;
	}
	
	/**
	 * 页面停留时间日志
	 * @param stayTimeLog
	 * @return
	 */
	public boolean createStayTimeLog(StayTimeLog stayTimeLog) {
		String sql = " insert into PAGE_STAY_TIME_LOG(URL, REFERER, IP, SID, VISIT_TIME, UA, OS, " +
				"BROWSER, CL, DS, RND, TIME1, TIME2, OUT_PAGE, PAGE_ID)" +
				" values(  :url, :referer, :ip, :sid, :visitTime, :ua,  :os,  :browser,  :cl,  :ds,  :rnd,  :time1, :time2, :outPage, :pageId)";
		int x = getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(stayTimeLog));
		return x > 0;
	}
	
	/**
	 * 根据RND,更新日志的outPage标识位1
	 * @param rnd
	 * @return
	 */
	public int updateStayTimeLogByRnd(Long rnd) {
		String sql = " update PAGE_STAY_TIME_LOG set OUT_PAGE=" + StayTimeLog.OUT_PAGE_NO + " where RND=" + rnd ;
		return getNamedParameterJdbcTemplate().update(sql, new HashMap<String, Object>());
	}
	
	/**
	 * 页面加载时间日志
	 * @param loadTimeLog
	 * @return
	 */
	public boolean createLoadTimeLog(LoadTimeLog loadTimeLog) {
		String sql = " insert into PAGE_LOADTIME_LOG(URL, REFERER, IP, SID, VISIT_TIME, UA, OS, BROWSER, CL, DS, RND, " +
				"NET_ALL, NET_TCP, NET_DNS, SRV, DOM, LOAD_EVENT, QID, BD_DOM, BD_RUN, BD_DEF, PAGE_ID)" +
				" values(  :url, :referer, :ip, :sid, :visitTime, :ua,  :os,  :browser,  :cl,  :ds,  :rnd, " +
				" :netAll, :netTcp, :netDns, :srv, :dom, :loadEvent, :qid, :bdDom, :bdRun, :bdDef, :pageId)";
		int x = getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(loadTimeLog));
		return x > 0;
	}
	
	/**
	 * 创建访问日志
	 * @param visitLog
	 * @return
	 */
	public boolean createClickLog(ClickLog clickLog) {
		String sql = " insert into PAGE_CLICK_LOG(URL, REFERER, IP, SID, VISIT_TIME, UA, OS, BROWSER, CL," +
				" DS, RND, X, Y, TAG, TAG_URL, PAGE_ID)" +
				" values(  :url, :referer, :ip, :sid, :visitTime, :ua,  :os,  :browser,  :cl,  :ds,  :rnd , :x, :y, :tag, :tagUrl,:pageId)";
		int x = getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(clickLog));
		return x > 0;
	}
	
	/**
	 * 创建访客日志
	 * @return
	 */
	public boolean createVisitorLog(VisitorLog visitorLog) {
		String sql = " insert into PAGE_VISITOR_LOG(UID, IP, FIRST_VISIT_TIME, LAST_VISIT_TIME) " +
				"  values(:uid, :ip, :firstVisitTime, :lastVisitTime) ";
		int x = getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(visitorLog));
		return x > 0;
	}
	
	/**
	 * 更新访客最后一次访问时间
	 * @param uid
	 * @return
	 */
	public int updateLastVisitTime(String uid) {
		String sql = " update PAGE_VISITOR_LOG set LAST_VISIT_TIME=" + DateUtil.parseDateToInt(new Date()) + 
				"  where UID='" + uid + "'";
		return getNamedParameterJdbcTemplate().update(sql, new HashMap<String, Object>());
	}
	
	/**
	 * 插入访问页面
	 * @param visitPage
	 * @return
	 */
	public Long createVisitPage(VisitPage visitPage) {
		String sql = " insert into PAGE(url, visit_count)" +
				" values(  :pageUrl, :visitCount)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int x = getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(visitPage), keyHolder);
		if(x > 0){
			return keyHolder.getKey().longValue();
		} else {
			return null;
		}
	}
	
	/**
	 * 更新页面的访问次数
	 * @param url
	 * @return
	 */
	public boolean updateVisitPage(String url) {
		String sql = " update PAGE set visit_count=visit_count+1 where url=:url";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("url", url);
		int x = getNamedParameterJdbcTemplate().update(sql, paramMap);
		return x > 0;
	}
	
	/**
	 * 根据URL查找受访页面编号
	 * @param url
	 * @return
	 */
	public Long findPageIdByUrl(String url) {
		String sql = " select id from PAGE where url=:url";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//System.out.println(sql + "  " + url);
		paramMap.put("url", url);
		List<Long> ids = getNamedParameterJdbcTemplate().query(sql, paramMap, new RowMapper<Long>() {
			@Override
			public Long mapRow(final ResultSet rs, final int rowNum) throws SQLException {				
				return rs.getLong("id");
			}
		});
		if(ids == null || ids.isEmpty()) {
			return null;
		} else {
			return ids.get(0);
		}
	}
	
	
	
}
