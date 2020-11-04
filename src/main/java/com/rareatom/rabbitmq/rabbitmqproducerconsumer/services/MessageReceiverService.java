package com.rareatom.rabbitmq.rabbitmqproducerconsumer.services;

import com.mtn.madapi.commons.rabbitmq.queuing.consumer.BaseMessageReceiver;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.models.UssdMessageRequests;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
public class MessageReceiverService implements BaseMessageReceiver {
    @Override
    public <UssdMessageRequests> Mono<Void> processMessage(UssdMessageRequests messageRequests, Map<String, Object> map) {
        log.info("UssdMessageRequests {}" , messageRequests);
        return null;
    }
}
