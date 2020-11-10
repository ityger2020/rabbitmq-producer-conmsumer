package com.rareatom.rabbitmq.rabbitmqproducerconsumer.services;

import com.mtn.madapi.commons.rabbitmq.queuing.consumer.BaseMessageReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Slf4j
public class MessageReceiverService implements BaseMessageReceiver {
    @Override
    public <OutboundUSSDResponse> Mono<Void> processMessage(OutboundUSSDResponse response, Map<String, Object> map) {
        log.info("UssdMessageRequests {}" , response);
        return null;
    }
}
