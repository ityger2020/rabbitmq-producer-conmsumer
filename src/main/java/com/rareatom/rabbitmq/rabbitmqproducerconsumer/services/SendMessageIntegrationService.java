package com.rareatom.rabbitmq.rabbitmqproducerconsumer.services;

import com.mtn.madapi.commons.rabbitmq.queuing.configuration.RabbitMqProperties;
import com.mtn.madapi.commons.rabbitmq.queuing.producer.SendMessageService;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.models.UssdMessageRequests;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendMessageIntegrationService {
    @Autowired
    RabbitMqProperties mqProperties;

    @Autowired
    SendMessageService messageService;


    public void sendMessage(UssdMessageRequests messageRequests){
        log.info("Sending message to queue {}", messageRequests);
        messageService.sendMessage(messageRequests , null , mqProperties.getSendQueues().get(0));
    }


}
