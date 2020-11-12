package com.rareatom.rabbitmq.rabbitmqproducerconsumer.ussdmenus;

import com.mtn.madapi.commons.rabbitmq.queuing.configuration.Queue;
import com.mtn.madapi.commons.rabbitmq.queuing.configuration.RabbitMqProperties;
import com.mtn.madapi.commons.rabbitmq.queuing.producer.SendMessageService;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.models.OutboundUSSDRequest;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.models.SystemCache;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.models.SystemState;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
    private final HashMap<Integer, Node> children = new HashMap<>();

    private  String ussdString = "MTN ussd api \n" +
           "Please pick an option \n" +
           "1 For Menu 1 \n" +
           "2 for Menu 2\n" +
           "3 for exit.";

    @PostConstruct
    public void init() {
        add(1 , menu1Selection);
        add(2 , menu2Selection);
        log.info("Loaded the selections");
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
        systemCache.save(msisdn , new SystemState(0));

    }

    @Override
    public void processInput(String input , String msisdn) {
        SystemState systemState = systemCache.get(msisdn);
        if(systemState.getCurrentMenuLevel() == 0){
            Node node = children.get(Integer.parseInt(input));
            node.render(msisdn);
            systemCache.save(msisdn , new SystemState(Integer.parseInt(input)));
        } else if(systemState.getCurrentMenuLevel() == 1){
            Node node = children.get(Integer.parseInt(input));
            node.processInput(input , msisdn);
            systemCache.delete(msisdn);
        }else if (systemState.getCurrentMenuLevel() == 2){
            Node node = children.get(Integer.parseInt(input));
            node.render(msisdn);
            systemCache.delete(msisdn);
        }
    }

    private void add(Integer index , Node node){
        children.put(index, node);
    }

}
