package com.rareatom.rabbitmq.rabbitmqproducerconsumer.services;

import com.mtn.madapi.commons.rabbitmq.queuing.consumer.BaseMessageReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class MessageReceiverService implements BaseMessageReceiver {

    @Autowired
    SendMessageIntegrationService integrationService;

    AtomicInteger processCount = new AtomicInteger(0);

    @Override
    public <OutboundUSSDResponse> Mono<Void> processMessage(OutboundUSSDResponse response, Map<String, Object> map) {
        log.info("UssdMessageRequests from system layer{}" , response);
        processCount.incrementAndGet();
        return null;
    }
}
