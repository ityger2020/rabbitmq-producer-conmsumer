package com.rareatom.rabbitmq.rabbitmqproducerconsumer.ussdmenus;

import com.mtn.madapi.commons.rabbitmq.queuing.configuration.Queue;
import com.mtn.madapi.commons.rabbitmq.queuing.configuration.RabbitMqProperties;
import com.mtn.madapi.commons.rabbitmq.queuing.producer.SendMessageService;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.models.OutboundUSSDRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Data
@NoArgsConstructor
@Slf4j
@Component
public class Menu1Selection implements Node {

    @Autowired
    SendMessageService messageService;

    @Autowired
    RabbitMqProperties mqProperties;
    private  List<Node> children = new ArrayList<>();

    private  String title = "MTN ussd api \n" +
           "Please pick an option \n" +
           "1 For Menu 1 \n" +
           "2 for Menu 2\n" +
           "3 for exit.";

    @Override
    public void render(String msisdn) {
        var ussdMessageRequests = new OutboundUSSDRequest();
        ussdMessageRequests.setCallbackUrl("http://localhost:8080/go");
        ussdMessageRequests.setMsisdn(msisdn);
        ussdMessageRequests.setSessionId("333o30303hrriro20900");
        ussdMessageRequests.setServiceCode("*298*778#");
        ussdMessageRequests.setUssdString(title);
        ussdMessageRequests.setMessageType(0);
        Queue queue = mqProperties.getSendQueues().get(0);
        log.info("Sending message to queue {} with request {}", queue.getName() , ussdMessageRequests);
        HashMap<String , String > map = new HashMap<>();
        map.put("transactionId" , String.valueOf(System.currentTimeMillis()));
        messageService.sendMessage(ussdMessageRequests , map, queue);

    }

    @Override
    public void processInput(String input , String msisdn) {

    }

}
