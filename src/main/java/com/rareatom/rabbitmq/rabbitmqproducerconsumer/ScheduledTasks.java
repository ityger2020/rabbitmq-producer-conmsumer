package com.rareatom.rabbitmq.rabbitmqproducerconsumer;

import com.rareatom.rabbitmq.rabbitmqproducerconsumer.ussdmenus.MenuSelection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    MenuSelection menuSelection;

    private final String msisdn = "2348163641560";

    @Scheduled(fixedRate = 450000)
    public void scheduledSendMessage(){
       menuSelection.render(msisdn);
    }

}
