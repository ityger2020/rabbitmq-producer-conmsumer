package com.rareatom.rabbitmq.rabbitmqproducerconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mtn.madapi.commons.rabbitmq.queuing", "com.mtn.system"})
@EnableScheduling
public class RabbitmqProducerConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerConsumerApplication.class, args);
	}

}
