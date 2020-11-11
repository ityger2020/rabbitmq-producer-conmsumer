package com.rareatom.rabbitmq.rabbitmqproducerconsumer.ussdmenus;

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
public class Menu2Selection implements Node{

    @Autowired
    RabbitMqProperties mqProperties;

    @Autowired
    SendMessageService messageService;

    private String ussdString = "You picked menu 2 please use 1 to exit.";
    @Override
    public void render(String msisdn) {
        var ussdMessageRequests = new OutboundUSSDRequest();
        ussdMessageRequests.setCallbackUrl("http://localhost:8080/go");
        ussdMessageRequests.setMsisdn(msisdn);
        ussdMessageRequests.setSessionId("333o30303hrriro20900");
        ussdMessageRequests.setServiceCode("*298*778#");
        ussdMessageRequests.setUssdString(ussdString);
        ussdMessageRequests.setMessageType(1);
        Queue queue = mqProperties.getSendQueues().get(0);
        log.info("Sending message to queue {} with request {}", queue.getName() , ussdMessageRequests);
        HashMap<String , String > map = new HashMap<>();
        map.put("transactionId" , String.valueOf(System.currentTimeMillis()));
        messageService.sendMessage(ussdMessageRequests , map, queue);
    }

    @Override
    public void processInput(String input, String fromMsisdn) {
        int parseInt = Integer.parseInt(input);
        if (parseInt == 1){
            var ussdMessageRequests = new OutboundUSSDRequest();
            ussdMessageRequests.setCallbackUrl("http://localhost:8080/go");
            ussdMessageRequests.setMsisdn(fromMsisdn);
            ussdMessageRequests.setSessionId("333o30303hrriro20900");
            ussdMessageRequests.setServiceCode("*298*778#");
            ussdMessageRequests.setUssdString("Thanks for choosing with MTN.");
            ussdMessageRequests.setMessageType(2);
            Queue queue = mqProperties.getSendQueues().get(0);
            log.info("Sending message to queue {} with request {}", queue.getName() , ussdMessageRequests);
            HashMap<String , String > map = new HashMap<>();
            map.put("transactionId" , String.valueOf(System.currentTimeMillis()));
            messageService.sendMessage(ussdMessageRequests , map, queue);
        }
    }
}
