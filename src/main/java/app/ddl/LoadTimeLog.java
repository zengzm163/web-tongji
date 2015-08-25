package app.ddl;

public class LoadTimeLog extends BaseLog {
	
	private Long netAll;
	
	private Long netDns;
	
	private Long netTcp;
	
	private Long srv;
	
	private Long dom;
	
	private Long loadEvent;
	
	private String qid;
	
	private Long bdDom;
	
	private Long bdRun;
	
	private Long bdDef;

	public Long getNetAll() {
		return netAll;
	}

	public void setNetAll(Long netAll) {
		this.netAll = netAll;
	}

	public Long getNetDns() {
		return netDns;
	}

	public void setNetDns(Long netDns) {
		this.netDns = netDns;
	}

	public Long getNetTcp() {
		return netTcp;
	}

	public void setNetTcp(Long netTcp) {
		this.netTcp = netTcp;
	}

	public Long getSrv() {
		return srv;
	}

	public void setSrv(Long srv) {
		this.srv = srv;
	}

	public Long getDom() {
		return dom;
	}

	public void setDom(Long dom) {
		this.dom = dom;
	}

	public Long getLoadEvent() {
		return loadEvent;
	}

	public void setLoadEvent(Long loadEvent) {
		this.loadEvent = loadEvent;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public Long getBdDom() {
		return bdDom;
	}

	public void setBdDom(Long bdDom) {
		this.bdDom = bdDom;
	}

	public Long getBdRun() {
		return bdRun;
	}

	public void setBdRun(Long bdRun) {
		this.bdRun = bdRun;
	}

	public Long getBdDef() {
		return bdDef;
	}

	public void setBdDef(Long bdDef) {
		this.bdDef = bdDef;
	}
	
	
	
}
