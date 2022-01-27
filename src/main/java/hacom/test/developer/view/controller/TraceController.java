package hacom.test.developer.view.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hacom.test.developer.transactional.service.TraceService;
import hacom.test.developer.view.dto.TraceMsgDto;
import hacom.test.developer.view.dto.request.DateRangeRequest;
import hacom.test.developer.view.dto.request.TraceMsgRequestDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rest/tracemsg")
public class TraceController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final TraceService traceService;

	public TraceController(TraceService traceService) {
		this.traceService = traceService;
	}

	@GetMapping("/all")
	public Flux<TraceMsgDto> getAll() throws Exception{
		logger.info("Inicio de TraceController - getAll()");
		
		Flux<TraceMsgDto> traceMsgDtoFlux = null;
		
		try {
			traceMsgDtoFlux = traceService.findAll();	
		}catch (Exception ex) {
			logger.error("Error ===> "+ex);
		}
        
		return traceMsgDtoFlux;
		
    }
	
	@PostMapping
    public Mono<TraceMsgDto> saveTrace(@RequestBody TraceMsgRequestDto traceMsgRequestDto) throws Exception{
		logger.info("Inicio de TraceController - saveTrace()");
		
		Mono<TraceMsgDto> traceMsgResponseDto = traceService.saveTraceMsg(traceMsgRequestDto);
		
        return traceMsgResponseDto;
    }
	
	@PostMapping("/findByRange")
	public Flux<TraceMsgDto> findByRange(@RequestBody DateRangeRequest dateRangeRequest) throws Exception{
		logger.info("Inicio de TraceController - findByRange()");
		
		Flux<TraceMsgDto> traceMsgDtoFlux = null;
		
		try {
			traceMsgDtoFlux = traceService.findByDateRange(dateRangeRequest);	
		}catch (Exception ex) {
			logger.error("Error ===> "+ex);
		}
        
		return traceMsgDtoFlux;
		
    }
	
}
