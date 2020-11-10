package com.rareatom.rabbitmq.rabbitmqproducerconsumer.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

@Data
@NoArgsConstructor
public class APIResponse extends ResourceSupport {
    private String statusCode;

}