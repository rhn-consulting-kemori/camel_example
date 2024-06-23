package com.redhat.example.rule;

// Spring
import org.springframework.stereotype.Component;

// Business Object
import com.redhat.example.entity.KijitsuNyukinExchangeEntity;
import com.redhat.example.entity.KijitsuNyukinResponseEntity;

@Component
public class SetDepositResultMessageRule {

    public KijitsuNyukinResponseEntity editResultMessage(KijitsuNyukinExchangeEntity exchange_message) {

        // Set Data
        KijitsuNyukinResponseEntity result_message = new KijitsuNyukinResponseEntity();
        
        /** Deposit_request */
        result_message.setDeposit_request(exchange_message.getDeposit_request());

        /** Deposit_result */
        result_message.setDeposit_result(exchange_message.getDeposit_result());
        result_message.setErr_code(exchange_message.getErr_code());
        result_message.setErr_context(exchange_message.getErr_context());        
        result_message.setDeposit_category_code(exchange_message.getDeposit_category_code());
        result_message.setDeposit_allocation_amount(exchange_message.getDeposit_allocation_amount());
        result_message.setExcess_money(exchange_message.getExcess_money());
        result_message.setJeccs_deposit(exchange_message.getJeccs_deposit());

        return result_message;
    }

}
