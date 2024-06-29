package com.redhat.example.processor;

// Camel
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

// Spring
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

// Business Object
import com.redhat.example.entity.KijitsuNyukinExchangeEntity;
import com.redhat.example.type.DepositCategoryRequestType;
import com.redhat.example.type.DepositCategoryResponseType;

// Business Service
import com.redhat.example.service.DepositCategoryService;

@Component
public class DepositCategoryProcessor implements Processor {
 
    @Autowired
    private KijitsuNyukinExchangeEntity exchange_message;

    @Autowired
    private DepositCategoryService service;

    @Override
    public void process(Exchange exchange) throws Exception {

        /**
         * Exchange IN
         */
        exchange_message = exchange.getMessage().getBody(KijitsuNyukinExchangeEntity.class);

        /** 
         * External Domain Service Call
         * Deposit Category Service
         */
        if(exchange_message.getDeposit_result().equals("0")){ 
            DepositCategoryResponseType response_body = service.serviceRequest(
                new DepositCategoryRequestType(
                    exchange_message.getDeposit_request().getRequest_id(),
                    exchange_message.getDeposit_request().getCustomer_contract_number(), 
                    exchange_message.getDeposit_request().getCustomer_billing_due_date(), 
                    exchange_message.getDeposit_request().getContract_settlement_date(), 
                    exchange_message.getDeposit_request().getDeposit_date())
            );

            if(response_body.getResponse_result().equals("0")) {
                exchange_message.setDeposit_category_code(response_body.getDeposit_category_code());
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
