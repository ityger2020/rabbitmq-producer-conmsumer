package com.rareatom.rabbitmq.rabbitmqproducerconsumer.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OutboundUSSDResponseData {
    private String result;
    private String sessionId;
    private String msisdn;
}
