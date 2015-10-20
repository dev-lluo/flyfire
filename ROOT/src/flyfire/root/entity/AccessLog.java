package flyfire.root.entity;

import flyfire.root.sql.ann.IntClmn;
import flyfire.root.sql.ann.StringClmn;
import flyfire.root.sql.ann.Table;
@Table(name="access_log")
public class AccessLog {
	public String getAcsId() {
		return acsId;
	}
	public void setAcsId(String acsId) {
		this.acsId = acsId;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	@StringClmn(isId=true,name="access_id")
	private String acsId;
	@IntClmn(name="acs_timestamp")
	private Long timestamp;
	@StringClmn(name="session_id")
	private String sessionId;
	@StringClmn(name="ip_address")
	private String ipAddr;
	@StringClmn(name="method")
	private String method;
	@StringClmn(name="uri")
	private String uri;
}
