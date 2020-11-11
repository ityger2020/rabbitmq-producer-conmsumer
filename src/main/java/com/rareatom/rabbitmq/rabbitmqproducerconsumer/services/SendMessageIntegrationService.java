package com.rareatom.rabbitmq.rabbitmqproducerconsumer.services;

import com.mtn.madapi.commons.rabbitmq.queuing.configuration.Queue;
import com.mtn.madapi.commons.rabbitmq.queuing.configuration.RabbitMqProperties;
import com.mtn.madapi.commons.rabbitmq.queuing.producer.SendMessageService;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.models.OutboundUSSDRequest;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.models.SystemCache;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.response.OutboundUSSDResponse;
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

    @Autowired
    SystemCache systemCache;

    private final String msisdn = "2348163641560";
    

    private final String INITIAL_MENU = "MTN ussd api \n" +
            "Please pick an option \n" +
            "1 For Menu 1 \n" +
            "2 for Menu 2\n" +
            "3 for exit.";

    private final String MENU_1 = "You picked Menu 1 \n" +
            "Please use 1 to exit.";

    private final String MENU_2 = "You picked Menu 2 \n" +
            "Please use 2 to exit.";

    private void sendMessage(OutboundUSSDRequest messageRequests){
        Queue queue = mqProperties.getSendQueues().get(0);
        log.info("Sending message to queue {} with request {}", queue.getName() ,messageRequests);
        HashMap<String , String > map = new HashMap<>();
        map.put("transactionId" , String.valueOf(System.currentTimeMillis()));
        messageService.sendMessage(messageRequests , map, queue);
    }


    public void sendInitialMenu(){
        var ussdMessageRequests = new OutboundUSSDRequest();
        ussdMessageRequests.setCallbackUrl("http://localhost:8080/go");
        ussdMessageRequests.setMsisdn(msisdn);
        ussdMessageRequests.setSessionId("333o30303hrriro20900");
        ussdMessageRequests.setServiceCode("*298*778#");
        ussdMessageRequests.setUssdString(INITIAL_MENU);
        ussdMessageRequests.setMessageType(0);
        sendMessage(ussdMessageRequests);
    }



    public void send1Menu(){
        var ussdMessageRequests = new OutboundUSSDRequest();
        ussdMessageRequests.setCallbackUrl("http://localhost:8080/go");
        ussdMessageRequests.setMsisdn(msisdn);
        ussdMessageRequests.setSessionId("333o30303hrriro20900");
        ussdMessageRequests.setServiceCode("*298*778#");
        ussdMessageRequests.setUssdString(MENU_1);
        ussdMessageRequests.setMessageType(1);
        sendMessage(ussdMessageRequests);

    }



    public void processMessages(OutboundUSSDResponse response){
        if (response.getData().getResult().equals("1")){

        }
    }


}
