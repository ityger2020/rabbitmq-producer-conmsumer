package com.rareatom.rabbitmq.rabbitmqproducerconsumer;

import com.rareatom.rabbitmq.rabbitmqproducerconsumer.models.OutboundUSSDRequest;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.services.SendMessageIntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    SendMessageIntegrationService integrationService;

    @Scheduled(fixedRate = 20000)
    public void scheduledSendMessage(){
        var ussdMessageRequests = new OutboundUSSDRequest();
        ussdMessageRequests.setCallbackUrl("http://localhost:8080/go");
        ussdMessageRequests.setMsisdn("2348163641560");
        ussdMessageRequests.setSessionId("333o30303hrriro20900");
        ussdMessageRequests.setServiceCode("*298*778#");
        ussdMessageRequests.setUssdString("Hello please pick an option");
        ussdMessageRequests.setMessageType(0);
        integrationService.sendMessage(ussdMessageRequests);
    }

}
