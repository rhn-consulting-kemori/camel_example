package com.redhat.example.processor;

import java.math.BigDecimal;

// Camel
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

// Spring
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

// Business Object
import com.redhat.example.entity.KijitsuNyukinExchangeEntity;
import com.redhat.example.entity.DepositDataEntity;
import com.redhat.example.type.DepositRequestType;
import com.redhat.example.type.DepositResponseType;

// Business Service
import com.redhat.example.service.DepositService;

@Component
public class DepositProcessor implements Processor {
  
    @Autowired
    private KijitsuNyukinExchangeEntity exchange_message;

    @Autowired
    private DepositService service;

    @Override
    public void process(Exchange exchange) throws Exception {

        /**
         * Exchange IN
         */
        exchange_message = exchange.getMessage().getBody(KijitsuNyukinExchangeEntity.class);

        /** 
         * External Domain Service Call
         * 入金業務
         */
        if(exchange_message.getDeposit_result().equals("0")){ 
            DepositResponseType response_body = service.serviceRequest(
                new DepositRequestType(
                    exchange_message.getDeposit_request().getRequest_id(),
                    exchange_message.getDeposit_request().getCustomer_contract_number(), 
                    exchange_message.getDeposit_request().getDeposit_date(),
                    exchange_message.getDeposit_request().getCustomer_billing_due_date(), 
                    exchange_message.getDeposit_request().getContract_settlement_date(), 
                    exchange_message.getDeposit_category_code(),
                    exchange_message.getDeposit_request().getDeposit_amount(),
                    exchange_message.getDeposit_request().getExcess_money_handling_category(),
                    exchange_message.getDeposit_allocation_data())
            );

            if(response_body.getResponse_result().equals("0")) {
                exchange_message.setDeposit_data(response_body.getDeposit_data());
            } else {
                exchange_message.setErrorInf("1", response_body.getErr_code(), response_body.getErr_context());
            }
        }
        
        /**
         * Exchange OUT
         */
        exchange.getMessage().setBody(exchange_message);

    }
}
