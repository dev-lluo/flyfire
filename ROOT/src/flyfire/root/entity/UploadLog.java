package flyfire.root.entity;

import flyfire.root.sql.ann.IntClmn;
import flyfire.root.sql.ann.StringClmn;
import flyfire.root.sql.ann.Table;

@Table(name="upload_log")
public class UploadLog {
	@StringClmn(name="upld_id",isId=true)
	private String upldId;
	@StringClmn(name="source_name")
	private String sourceName;
	@StringClmn(name="target_path",isUn=true)
	private String targetPath;
	@IntClmn(name="upload_timestamp")
	private Long timestamp;
	@StringClmn(name="ip_addr")
	private String ipAddr;
	public String getUpldId() {
		return upldId;
	}
	public void setUpldId(String upldId) {
		this.upldId = upldId;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getTargetPath() {
		return targetPath;
	}
	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
}
