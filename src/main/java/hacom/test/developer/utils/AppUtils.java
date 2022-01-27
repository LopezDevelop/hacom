package hacom.test.developer.utils;

import org.springframework.beans.BeanUtils;

import hacom.test.developer.persistence.entity.TraceMsg;
import hacom.test.developer.view.dto.TraceMsgDto;
import hacom.test.developer.view.dto.request.TraceMsgRequestDto;

public class AppUtils {

    public static TraceMsgDto entityToDto(TraceMsg traceMsg) {
        TraceMsgDto traceMsgDto = new TraceMsgDto();
        BeanUtils.copyProperties(traceMsg, traceMsgDto);
        return traceMsgDto;
    }

    public static TraceMsg dtoToEntity(TraceMsgDto traceMsgDto) {
        TraceMsg traceMsg = new TraceMsg();
        BeanUtils.copyProperties(traceMsgDto, traceMsg);
        return traceMsg;
    }
    
    // Custom
    public static TraceMsgDto entityToDtoCustom(TraceMsg traceMsg) {
        TraceMsgDto traceMsgDto = new TraceMsgDto();
        
        traceMsgDto.setId(traceMsg.getId().toHexString());
        traceMsgDto.setSessionId(traceMsg.getSessionId());
        traceMsgDto.setPayload(traceMsg.getPayload());
        traceMsgDto.setTs(traceMsg.getTs().toString());
        
        return traceMsgDto;
    }

    public static TraceMsg dtoToEntityCustom(TraceMsgRequestDto traceMsgRequestDto) {
        TraceMsg traceMsg = new TraceMsg();
        
        traceMsg.setPayload(traceMsgRequestDto.getPayload());
        traceMsg.setSessionId(traceMsgRequestDto.getSessionId());
        traceMsg.setTs(traceMsgRequestDto.getTs());
        
        return traceMsg;
    }

}
