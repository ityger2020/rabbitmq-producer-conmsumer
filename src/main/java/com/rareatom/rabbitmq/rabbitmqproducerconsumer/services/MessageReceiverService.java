package com.rareatom.rabbitmq.rabbitmqproducerconsumer.services;

import com.mtn.madapi.commons.rabbitmq.queuing.consumer.BaseMessageReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Slf4j
public class MessageReceiverService implements BaseMessageReceiver {

    @Autowired
    SendMessageIntegrationService integrationService;

    @Override
    public <OutboundUSSDResponse> Mono<Void> processMessage(OutboundUSSDResponse response, Map<String, Object> map) {
        log.info("UssdMessageRequests from system layer{}" , response);
        integrationService.processMessages((com.rareatom.rabbitmq.rabbitmqproducerconsumer.response.OutboundUSSDResponse) response);
        return null;
    }
}
