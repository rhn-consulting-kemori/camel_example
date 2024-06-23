package com.redhat.example.processor;

// Camel
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

// Spring
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

// Business Object
import com.redhat.example.entity.KijitsuNyukinExchangeEntity;

@Component
public class DepositProcessor implements Processor {
  
    @Autowired
    private KijitsuNyukinExchangeEntity exchange_message;

    @Override
    public void process(Exchange exchange) throws Exception {

        /**
         * Exchange IN
         */
        exchange_message = exchange.getMessage().getBody(KijitsuNyukinExchangeEntity.class);

        /** 
         * External Domain Service Call
         * 
         */ 

         
        /**
         * Exchange OUT
         */
        exchange.getMessage().setBody(exchange_message);

    }
}
