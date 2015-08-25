package app.ddl;

public class StayTimeLog extends BaseLog {
	
	public static final int OUT_PAGE_YES = 0;
	
	public static final int OUT_PAGE_NO = 1;
	
	private Long time1;
	
	private Long time2;
	
	
	
	/**
	 * 是否退出页面：0：是，1：否
	 */
	private Integer outPage;

	public Long getTime1() {
		return time1;
	}

	public void setTime1(Long time1) {
		this.time1 = time1;
	}

	public Long getTime2() {
		return time2;
	}

	public void setTime2(Long time2) {
		this.time2 = time2;
	}

	

	public Integer getOutPage() {
		return outPage;
	}

	public void setOutPage(Integer outPage) {
		this.outPage = outPage;
	}
	
	
	
}
