package hacom.test.developer.transactional.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hacom.test.developer.transactional.service.TraceService;
import hacom.test.developer.view.dto.TraceMsgDto;
import hacom.test.developer.view.dto.request.TraceMsgRequestDto;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
@Import(TraceService.class)
public class TraceServiceImplTest {
	
	public static Gson gson; 
	
	private final TraceService traceService;
	
	public TraceServiceImplTest(@Autowired TraceService traceService) {
		this.traceService = traceService; 
	}
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		gson = new GsonBuilder().serializeNulls().create(); 
	}   
	
	@Test
	void addTraceMsgTest() throws Exception{
		
		//OffsetDateTime date1 = OffsetDateTime.now();
		
		TraceMsgRequestDto traceMsgRequestDto = new TraceMsgRequestDto("1","12345688","2022-01-22");
		Mono<TraceMsgDto> traceMsgDtoMonoResponse = traceService.saveTraceMsg(traceMsgRequestDto);
		
		StepVerifier
			.create(traceMsgDtoMonoResponse)
			.expectNextMatches(saved -> StringUtils.hasText(saved.getId()))
			.verifyComplete();
		
	}
	

}
