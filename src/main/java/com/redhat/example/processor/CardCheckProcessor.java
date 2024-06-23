package com.redhat.example.processor;

// Camel
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

// Spring
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

// Business Object
import com.redhat.example.entity.KijitsuNyukinRequestEntity;
import com.redhat.example.entity.KijitsuNyukinExchangeEntity;
import com.redhat.example.type.CardCheckRequestType;
import com.redhat.example.type.CardCheckResponseType;

// Business Service
import com.redhat.example.service.CardCheckService;

@Component
public class CardCheckProcessor implements Processor {

    @Autowired
    private KijitsuNyukinExchangeEntity exchange_message;

    @Autowired
    private CardCheckService service;

    @Override
    public void process(Exchange exchange) throws Exception {

        /**
         * Exchange IN
         */
        var body = exchange.getMessage().getBody(KijitsuNyukinRequestEntity.class);
        exchange_message.setDeposit_request(body);

        /** 
         * External Domain Service Call
         * Card Check Service
         */ 
        CardCheckRequestType request = new CardCheckRequestType(body.getRequest_id(), body.getCard_number());
        CardCheckResponseType response_body = service.serviceRequest(request);

        if(response_body.getResponse_result().equals("0")) {
            exchange_message.setDeposit_result("0");
        } else {
            exchange_message.setErrorInf("1", response_body.getErr_code(), response_body.getErr_context());
        }
         
        /**
         * Exchange OUT
         */
        exchange.getMessage().setBody(exchange_message);

    }
}
