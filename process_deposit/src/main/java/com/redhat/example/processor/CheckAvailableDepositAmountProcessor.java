package com.redhat.example.processor;

// Camel
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

// Spring
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

// Business Object
import com.redhat.example.entity.KijitsuNyukinExchangeEntity;
import com.redhat.example.entity.AvailableDepositAmountDataEntity;
import com.redhat.example.type.CheckAvailableDepositAmountRequestType;
import com.redhat.example.type.CheckAvailableDepositAmountResponseType;

// Business Service
import com.redhat.example.service.CheckAvailableDepositAmountService;

@Component
public class CheckAvailableDepositAmountProcessor implements Processor {

    @Autowired
    private KijitsuNyukinExchangeEntity exchange_message;

    @Autowired
    private CheckAvailableDepositAmountService service;

    @Override
    public void process(Exchange exchange) throws Exception {

        /**
         * Exchange IN
         */
        exchange_message = exchange.getMessage().getBody(KijitsuNyukinExchangeEntity.class);

        /** 
         * External Domain Service Call
         * 入金可能額照会業務
         */
        if(exchange_message.getDeposit_result().equals("0")){ 
            CheckAvailableDepositAmountResponseType response_body = service.serviceRequest(
                new CheckAvailableDepositAmountRequestType(
                    exchange_message.getDeposit_request().getRequest_id(),
                    exchange_message.getDeposit_request().getCustomer_contract_number(), 
                    exchange_message.getDeposit_request().getDeposit_date(),
                    exchange_message.getDeposit_request().getCustomer_billing_due_date(), 
                    exchange_message.getDeposit_request().getContract_settlement_date(), 
                    exchange_message.getDeposit_category_code())
            );

            if(response_body.getResponse_result().equals("0")) {
                exchange_message.setDeposit_available_amount_data(response_body.getDeposit_available_amount_data());
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
