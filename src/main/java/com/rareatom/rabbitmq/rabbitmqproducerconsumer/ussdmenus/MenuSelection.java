package com.rareatom.rabbitmq.rabbitmqproducerconsumer.ussdmenus;

import com.mtn.madapi.commons.rabbitmq.queuing.configuration.Queue;
import com.mtn.madapi.commons.rabbitmq.queuing.configuration.RabbitMqProperties;
import com.mtn.madapi.commons.rabbitmq.queuing.producer.SendMessageService;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.models.OutboundUSSDRequest;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.models.SystemCache;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@Slf4j
@Component
public class MenuSelection implements Node {
    @Autowired
    SendMessageService messageService;

    @Autowired
    RabbitMqProperties mqProperties;

    @Autowired
    Menu1Selection menu1Selection ;

    @Autowired
    Menu2Selection menu2Selection;

    @Autowired
    SystemCache systemCache;
    private HashMap<Integer , Node> children = new HashMap<>();

    private  String ussdString = "MTN ussd api \n" +
           "Please pick an option \n" +
           "1 For Menu 1 \n" +
           "2 for Menu 2\n" +
           "3 for exit.";


    public MenuSelection() {
        add(1 , menu1Selection);
        add(2 , menu2Selection);
    }

    @Override
    public void render(String msisdn) {
        var ussdMessageRequests = new OutboundUSSDRequest();
        ussdMessageRequests.setCallbackUrl("http://localhost:8080/go");
        ussdMessageRequests.setMsisdn(msisdn);
        ussdMessageRequests.setSessionId("333o30303hrriro20900");
        ussdMessageRequests.setServiceCode("*298*778#");
        ussdMessageRequests.setUssdString(ussdString);
        ussdMessageRequests.setMessageType(0);
        Queue queue = mqProperties.getSendQueues().get(0);
        log.info("Sending message to queue {} with request {}", queue.getName() , ussdMessageRequests);
        HashMap<String , String > map = new HashMap<>();
        map.put("transactionId" , String.valueOf(System.currentTimeMillis()));
        messageService.sendMessage(ussdMessageRequests , map, queue);

    }

    @Override
    public void processInput(String input , String msisdn) {
        int parseInt = Integer.parseInt(input);
        Node node = children.get(parseInt + 1);
        node.render(msisdn);

    }

    private void add(Integer index , Node node){
        children.put(index, node);
    }

}
