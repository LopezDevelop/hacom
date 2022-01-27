package hacom.test.developer.view.dto.request;

import java.io.Serializable;

public class TraceMsgRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7155141930492914016L;
	private String sessionId;
	private String payload;
	private String ts;

	public TraceMsgRequestDto() {

	}

	public TraceMsgRequestDto(String sessionId, String payload, String ts) {
		this.sessionId = sessionId;
		this.payload = payload;
		this.ts = ts;
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
	
//	public OffsetDateTime getTs() {
//		return ts;
//	}
//
//	public void setTs(String ts) {
//		/*
//		//final String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSxx";
//		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy'T'HH:mm:ss");
//		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'+'HH:MM");
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//		//OffsetDateTime.from(formatter.parse(ts));
//        //DateTimeFormatter dtfB = DateTimeFormatter.ofPattern(pattern);
//        //this.ts = OffsetDateTime.parse(ts, dtfB);
//        this.ts = OffsetDateTime.from(formatter.parse(ts));
//        */
//        
//        
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
////        OffsetDateTime fromDate = LocalDate.parse(ts, formatter)
////                .atStartOfDay(ZoneOffset.UTC)
////                .toOffsetDateTime();
////        //System.out.println(fromDate);
////        LocalDateTime localDT = null;
//		/*
//        String str = "2016-03-04 11:30"; 
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
//        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
//
//        ZoneId z = ZoneId.of("America/Bogota");
//        LocalDate ld = LocalDate.of( dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth());
//        ZonedDateTime zdt = ld.atStartOfDay(z);
//        ZoneOffset zoneOffset = zdt.getOffset();
//        //OffsetDateTime.of(dateTime, zoneOffset);
//        */
//        this.ts = OffsetDateTime.now();
//        
//	}

}
