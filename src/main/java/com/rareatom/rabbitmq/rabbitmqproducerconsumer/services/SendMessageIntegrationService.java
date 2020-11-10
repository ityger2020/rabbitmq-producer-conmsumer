package com.rareatom.rabbitmq.rabbitmqproducerconsumer.services;

import com.mtn.madapi.commons.rabbitmq.queuing.configuration.Queue;
import com.mtn.madapi.commons.rabbitmq.queuing.configuration.RabbitMqProperties;
import com.mtn.madapi.commons.rabbitmq.queuing.producer.SendMessageService;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.models.OutboundUSSDRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Slf4j
public class SendMessageIntegrationService {
    @Autowired
    RabbitMqProperties mqProperties;

    @Autowired
    SendMessageService messageService;

    public void sendMessage(OutboundUSSDRequest messageRequests){
        Queue queue = mqProperties.getSendQueues().get(0);
        log.info("Sending message to queue {} with request {}", queue.getName() ,messageRequests);
        HashMap<String , String > map = new HashMap<>();
        map.put("transactionId" , String.valueOf(System.currentTimeMillis()));
        messageService.sendMessage(messageRequests , map, queue);
    }


}
