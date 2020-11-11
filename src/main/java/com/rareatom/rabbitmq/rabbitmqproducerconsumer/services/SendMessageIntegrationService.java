package com.rareatom.rabbitmq.rabbitmqproducerconsumer.services;

import com.rareatom.rabbitmq.rabbitmqproducerconsumer.models.SystemCache;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.response.OutboundUSSDResponse;
import com.rareatom.rabbitmq.rabbitmqproducerconsumer.ussdmenus.MenuSelection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendMessageIntegrationService {
    @Autowired
    SystemCache systemCache;

    @Autowired
    MenuSelection menuSelection;

    private final String MENU_1 = "";

    private final String MENU_2 = "You picked Menu 2 \n" +
            "Please use 2 to exit.";


    public void processMessages(OutboundUSSDResponse response){
        if (response.getData().getResult().equals("1")){

        }
    }


}
