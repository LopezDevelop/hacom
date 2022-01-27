package hacom.test.developer.view.dto.request;

import javax.validation.constraints.NotNull;

public class DateRangeRequest {

	@NotNull
	private String from;
	@NotNull
	private String to;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
