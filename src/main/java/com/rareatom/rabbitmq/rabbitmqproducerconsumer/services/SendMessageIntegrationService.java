package com.rareatom.rabbitmq.rabbitmqproducerconsumer.services;

import com.rareatom.rabbitmq.rabbitmqproducerconsumer.response.OutboundUSSDResponse;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.ussdmenus.MenuSelection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendMessageIntegrationService {

    @Autowired
    MenuSelection menuSelection;


    public void processMessages(OutboundUSSDResponse response){
        String result = response.getData().getResult();
        String msisdn = response.getData().getMsisdn();
        menuSelection.processInput(result , msisdn );

    }


}
