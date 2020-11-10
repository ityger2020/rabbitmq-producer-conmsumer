package com.rareatom.rabbitmq.rabbitmqproducerconsumer.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OutboundUSSDResponse extends APIResponse {
    private String statusMessage;
    private String transactionId;
    private OutboundUSSDResponseData data ;
}
