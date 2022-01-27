package hacom.test.developer.persistence.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TraceMsg")
public class TraceMsg {

	@Id
	private ObjectId _id;
	private String sessionId;
	private String payload;
	private String ts;
	
	public TraceMsg() {
	}

	public TraceMsg(String sessionId, String payload, String ts) {
		this.sessionId = sessionId;
		this.payload = payload;
		this.ts = ts;
	}

	public ObjectId getId() {
		return _id;
	}

	public void setId(ObjectId _id) {
		this._id = _id;
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

	public void setTs(String dt) {
		this.ts = dt;
	}

}
