package com.rareatom.rabbitmq.rabbitmqproducerconsumer.models;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SystemState {

    private String currentMenuState;
    private String currentSelection;
}
