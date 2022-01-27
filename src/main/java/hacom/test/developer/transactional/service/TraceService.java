package hacom.test.developer.transactional.service;

import hacom.test.developer.view.dto.TraceMsgDto;
import hacom.test.developer.view.dto.request.DateRangeRequest;
import hacom.test.developer.view.dto.request.TraceMsgRequestDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TraceService {
	
	public Mono<TraceMsgDto> saveTraceMsg(TraceMsgRequestDto traceMsgRequestDto);
	
	public Flux<TraceMsgDto> findAll();
	
	public Flux<TraceMsgDto> findByDateRange(DateRangeRequest dateRangeRequest);
	 
}
