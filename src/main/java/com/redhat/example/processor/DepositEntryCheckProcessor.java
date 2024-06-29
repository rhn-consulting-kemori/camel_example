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
import com.redhat.example.type.DepositEntryCheckRequestType;
import com.redhat.example.type.DepositEntryCheckResponseType;

// Business Service
import com.redhat.example.service.DepositEntryCheckService;

@Component
public class DepositEntryCheckProcessor implements Processor {

    @Autowired
    private KijitsuNyukinExchangeEntity exchange_message;

    @Autowired
    private DepositEntryCheckService service;

    @Override
    public void process(Exchange exchange) throws Exception {

        /**
         * Exchange IN
         */
        KijitsuNyukinRequestEntity exchange_request = exchange.getMessage().getBody(KijitsuNyukinRequestEntity.class);
        exchange_message.setDeposit_request(exchange_request);

        /** 
         * External Domain Service Call
         * Deposit Category Service
         */ 
        DepositEntryCheckResponseType response_body = service.serviceRequest(
            new DepositEntryCheckRequestType(
                exchange_request.getRequest_id(),
                exchange_request.getCard_number(),
                exchange_request.getCustomer_contract_number(), 
                exchange_request.getCustomer_billing_due_date(), 
                exchange_request.getContract_settlement_date(), 
                exchange_request.getDeposit_date(),
                exchange_request.getDeposit_amount(),
                exchange_request.getExcess_money_handling_category()
            )
        );

        if(response_body.getResponse_result().equals("0")) {
            exchange_message.setErrorInf("0", "", "");
        } else {
            exchange_message.setErrorInf("1", response_body.getErr_code(), response_body.getErr_context());
        }
         
        /**
         * Exchange OUT
         */
        exchange.getMessage().setBody(exchange_message);
    }
}
