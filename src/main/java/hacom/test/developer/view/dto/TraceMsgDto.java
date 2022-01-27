package hacom.test.developer.view.dto;

import java.io.Serializable;

public class TraceMsgDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1987246053758732029L;
	private String id;
	private String sessionId;
	private String payload;
	private String ts;

	public TraceMsgDto() {

	}

	public TraceMsgDto(String sessionId, String payload, String ts) {
		this.sessionId = sessionId;
		this.payload = payload;
		this.ts = ts;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

}
