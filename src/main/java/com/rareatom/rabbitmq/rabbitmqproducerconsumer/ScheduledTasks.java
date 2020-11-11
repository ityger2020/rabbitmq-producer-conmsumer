package com.rareatom.rabbitmq.rabbitmqproducerconsumer;

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

    @Scheduled(fixedRate = 900000)
    public void scheduledSendMessage(){
       // integrationService.sendInitialMenu();
    }

}
