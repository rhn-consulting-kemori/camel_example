package com.redhat.example.processor;

// Camel
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

// Spring
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

// Business Object
import com.redhat.example.entity.KijitsuNyukinExchangeEntity;
import com.redhat.example.entity.KijitsuNyukinResponseEntity;

// Business Rule
import com.redhat.example.rule.SetDepositResultMessageRule;

@Component
public class SetDepositResultMessageProcessor implements Processor{

    @Autowired
    private KijitsuNyukinExchangeEntity exchange_message;

    @Autowired
    private SetDepositResultMessageRule message_rule;

    @Override
    public void process(Exchange exchange) throws Exception {
        /**
         * Exchange IN
         */
        exchange_message = exchange.getMessage().getBody(KijitsuNyukinExchangeEntity.class);

        /**
         * Process: Convert Exchange
         */
        KijitsuNyukinResponseEntity result_message = message_rule.editResultMessage(exchange_message);

        /**
         * Exchange OUT
         */
        exchange.getMessage().setBody(result_message);

    }
}
