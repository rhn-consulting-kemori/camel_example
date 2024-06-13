package com.redhat.example.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.redhat.example.entity.KijitsuNyukinRequestEntity;
import com.redhat.example.entity.KijitsuNyukinExchangeEntity;

@Component
public class NyukinRequestCheckProcessor implements Processor {

    @Autowired
    KijitsuNyukinExchangeEntity exchange_message;

    @Override
    public void process(Exchange exchange) throws Exception {
        var body = exchange.getMessage().getBody(KijitsuNyukinRequestEntity.class);
        exchange_message.setDeposit_request(body);

        // Service Call
        exchange_message.setDeposit_check_result("1");
        exchange.getMessage().setBody(exchange_message);
    }
}
