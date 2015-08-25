package app.ddl;

/**
 * 页面
 * @author Administrator
 *
 */
public class VisitPage {
	
	private Long id;
	
	/**
	 * 页面url
	 */
	private String pageUrl;
	
	/**
	 * 总访问次数
	 */
	private Integer visitCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	
	
	
}
