package hacom.test.developer.transactional.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hacom.test.developer.persistence.entity.TraceMsg;
import hacom.test.developer.persistence.repository.TraceMsgRepository;
import hacom.test.developer.transactional.service.TraceService;
import hacom.test.developer.utils.AppUtils;
import hacom.test.developer.view.dto.TraceMsgDto;
import hacom.test.developer.view.dto.request.DateRangeRequest;
import hacom.test.developer.view.dto.request.TraceMsgRequestDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("TraceService")
public class TraceServiceImpl implements TraceService {

	@Autowired
	private TraceMsgRepository traceMsgRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Mono<TraceMsgDto> saveTraceMsg(TraceMsgRequestDto traceMsgRequestDto) {
		logger.info("Ingreso a TraceServiceImpl.saveTraceMsg(traceMsgRequestDto)");
		
		Mono<TraceMsgDto> traceMsgRequestDtoMap = Mono.just(new TraceMsgDto(traceMsgRequestDto.getSessionId(), traceMsgRequestDto.getPayload(), traceMsgRequestDto.getTs()));

		return traceMsgRequestDtoMap.map(AppUtils::dtoToEntity).flatMap(traceMsgRepository::insert)
				.map(AppUtils::entityToDto);
	}

	@Override
	public Flux<TraceMsgDto> findAll() {
		logger.info("Ingreso a TraceServiceImpl.findAll()");
		
		return traceMsgRepository.findAll().map(AppUtils::entityToDtoCustom);
	}

	@Override
	public Flux<TraceMsgDto> findByDateRange(DateRangeRequest dateRangeRequest) {
		logger.info("Ingreso a TraceServiceImpl.findByDateRange()");
		
		Flux<TraceMsg> lstTraceMsg = traceMsgRepository.findAll();
		
		List<TraceMsg> list1 = lstTraceMsg.collectList().block();
		list1.forEach(System.out::println);
		String fechaTs = "";
		String fechaFrom = dateRangeRequest.getFrom().replaceAll("[-+.^:,T]","");
		String fechaTo = dateRangeRequest.getTo().replaceAll("[-+.^:,T]","");;
		
		BigInteger fechaList = BigInteger.ZERO;
		BigInteger lfechaFrom = new BigInteger(fechaFrom);
		BigInteger lfechaTo = new BigInteger(fechaTo);
		logger.info("Fecha From ====>"+lfechaFrom);
		logger.info("Fecha To ====>"+lfechaTo);
		
		List<TraceMsg> listaGralTraceMsg = new ArrayList<TraceMsg>();
		
		for (TraceMsg o : list1) {
			fechaTs = o.getTs().replaceAll("[-+.^:,T]","");
			fechaList = new BigInteger(fechaTs);
		
			boolean flag1 = false;
			boolean flag2 = false;
			
			String relation = "";
			switch (fechaList.compareTo(lfechaFrom))
			{
			   case -1:
			      relation = "<";
			      flag1 = false;
			      break;
			   case 0:
			      relation = "=";
			      flag1 = true;
			      break;
			   case 1:
			      relation = ">";
			      flag1 = true;
			      break;
			}
			
			logger.info("Comparar Fechas ===> FechaList " + fechaList + " " + relation + " " + "FechaFrom " + lfechaFrom);

			if (flag1) {
				switch (fechaList.compareTo(lfechaTo))
				{
				   case -1:
				      relation = "<";
				      logger.info("relation "+ relation);
				      flag2 = true;
				      break;
				   case 0:
				      relation = "=";
				      logger.info("relation "+ relation);
				      flag2 = true;
				      break;
				   case 1:
				      relation = ">";
				      logger.info("relation "+ relation);
				      flag2 = false;
				      break;
				}
			}
			
			if (flag2) {
				logger.info("Comparar Fechas ===> FechaList " + fechaList + " " + relation + " " + "FechaTo " + lfechaTo);
				listaGralTraceMsg.add(o);
			}
			
		}
		
		Flux<TraceMsg> finalLstTraceMsg = Flux.fromIterable(listaGralTraceMsg);
		
		Flux<TraceMsgDto> lstTraceMsgDtofinal = finalLstTraceMsg.map(AppUtils::entityToDtoCustom);
		
		return lstTraceMsgDtofinal;
		
	}

}
