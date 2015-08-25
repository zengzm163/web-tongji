package app.ddl;

/**
 * 访客日志
 * @author Administrator
 *
 */
public class VisitorLog {
	private Long id;
	
	private String uid;
	
	private String ip;
	
	private Integer firstVisitTime;
	
	private Integer lastVisitTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getFirstVisitTime() {
		return firstVisitTime;
	}

	public void setFirstVisitTime(Integer firstVisitTime) {
		this.firstVisitTime = firstVisitTime;
	}

	public Integer getLastVisitTime() {
		return lastVisitTime;
	}

	public void setLastVisitTime(Integer lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}
	
	
}
