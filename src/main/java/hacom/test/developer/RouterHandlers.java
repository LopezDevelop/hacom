package hacom.test.developer;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import hacom.test.developer.transactional.service.TraceService;
import hacom.test.developer.view.dto.TraceMsgDto;
import hacom.test.developer.view.dto.request.TraceMsgRequestDto;
import reactor.core.publisher.Mono;

@Component
public class RouterHandlers {

	private final TraceService traceService;

	public RouterHandlers(TraceService traceService) {
		this.traceService = traceService;
	}

	public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
		return ServerResponse.ok().body(traceService.findAll(), TraceMsgDto.class);
	}

	public Mono<ServerResponse> saveTrace(ServerRequest serverRequest) {

		TraceMsgRequestDto traceMsgRequestDto = new TraceMsgRequestDto();
		traceMsgRequestDto.setSessionId(serverRequest.attribute("sessionId").toString());
		traceMsgRequestDto.setPayload(serverRequest.attribute("payload").toString());
		traceMsgRequestDto.setTs(serverRequest.attribute("ts").toString());

		return ServerResponse.ok().body(traceService.saveTraceMsg(traceMsgRequestDto), TraceMsgRequestDto.class);
	}

}
