package com.rareatom.rabbitmq.rabbitmqproducerconsumer.models;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


/**
 *
 * Map holds the msisdn as a key
 * and then the @{@link SystemState} that handles the
 * currentMenuLevel (Menu level can be 1 for the initial menu with  multiple options )
 * currentSelection can mean for the current menu level what was the selection
 * made by the subscriber.
 *
 */
@Component
public class SystemCache  extends ConcurrentHashMap<String, SystemState> {

    /**
     * Creates a new, empty map with the default initial table size (16).
     */
    public SystemCache() {

        super();
    }



    public void save(String msisdn , SystemState systemState){
        SystemState state = get(msisdn);
        if(state == null ){
            put(msisdn , systemState);
        }
        else{
            replace(msisdn , systemState);
        }
    }


    public void delete(String msisdn){
        remove(msisdn);
    }
}
