package com.rareatom.rabbitmq.rabbitmqproducerconsumer.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UssdMessageRequests {
    private String sessionId;
    private int messageType;
    private String msisdn ;
    private String serviceCode;
    private String ussdString;
    private String callbackUrl;

}
