package com.rareatom.rabbitmq.rabbitmqproducerconsumer.services;

import com.mtn.madapi.commons.rabbitmq.queuing.configuration.RabbitMqProperties;
import com.mtn.madapi.commons.rabbitmq.queuing.producer.SendMessageService;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.models.UssdMessageRequests;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class SendMessageIntegrationService {
    @Autowired
    RabbitMqProperties mqProperties;

    @Autowired
    SendMessageService messageService;


    @Scheduled(fixedRate = 10000)
    public void sendMessage(){
        var ussdMessageRequests = new UssdMessageRequests();
        ussdMessageRequests.setCallbackUrl("http://localhost:8080/go");
        ussdMessageRequests.setMsisdn("08163641560");
        ussdMessageRequests.setSessionId("333o30303hrriro20900");
        ussdMessageRequests.setServiceCode("*298*778#");
        ussdMessageRequests.setMessageType(0);
        log.info("Sending message to queue {}", ussdMessageRequests);
        messageService.sendMessage(ussdMessageRequests , null , mqProperties.getSendQueues().get(0));
    }


}
