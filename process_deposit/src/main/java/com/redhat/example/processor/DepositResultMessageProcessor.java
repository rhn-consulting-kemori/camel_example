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
import com.redhat.example.rule.DepositResultMessageRule;

@Component
public class DepositResultMessageProcessor implements Processor{

    @Autowired
    private KijitsuNyukinExchangeEntity exchange_message;

    @Autowired
    private DepositResultMessageRule rule;

    @Override
    public void process(Exchange exchange) throws Exception {
        /**
         * Exchange IN
         */
        exchange_message = exchange.getMessage().getBody(KijitsuNyukinExchangeEntity.class);

        /**
         * Rule Call
         * 入金結果報告ルール
         */
        KijitsuNyukinResponseEntity result_message = rule.executeRule(exchange_message);

        /**
         * Exchange OUT
         */
        exchange.getMessage().setBody(result_message);

    }
}
