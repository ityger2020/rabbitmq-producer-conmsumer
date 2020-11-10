package com.rareatom.rabbitmq.rabbitmqproducerconsumer.models;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


/**
 *
 * Map holds the current menu state which can be level 1 menu or level 2 menu
 * and then another String which is the current selection.
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
}
