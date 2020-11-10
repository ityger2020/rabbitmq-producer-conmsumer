package com.rareatom.rabbitmq.rabbitmqproducerconsumer.models;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SystemState {
    private int currentMenuLevel;
    private String currentSelection;
}
