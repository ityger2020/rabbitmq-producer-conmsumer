package com.rareatom.rabbitmq.rabbitmqproducerconsumer.ussdmenus;
public interface Node {
    public void render(String msisdn);
    public void processInput(String input , String fromMsisdn);

}
