package com.redhat.example.rule;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

// Spring
import org.springframework.stereotype.Component;

// Business Object
import com.redhat.example.entity.KijitsuNyukinExchangeEntity;
import com.redhat.example.entity.KijitsuNyukinResponseEntity;
import com.redhat.example.entity.SaikenCompositeUnitEntity;

@Component
public class DepositResultMessageRule {

    public KijitsuNyukinResponseEntity executeRule(KijitsuNyukinExchangeEntity exchange_message) {

        // Set Data
        KijitsuNyukinResponseEntity result_message = new KijitsuNyukinResponseEntity();
        
        /** Deposit_request */
        result_message.setDeposit_request(exchange_message.getDeposit_request());

        /** Deposit_result */
        result_message.setDeposit_result(exchange_message.getDeposit_result());
        result_message.setErr_code(exchange_message.getErr_code());
        result_message.setErr_context(exchange_message.getErr_context());   
        
        /** Deposit_contents */
        result_message.setDeposit_category_code(exchange_message.getDeposit_category_code());
        result_message.setDeposit_allocation_amount(exchange_message.getDeposit_data().getDeposit_allocation_amount());
        result_message.setExcess_money(exchange_message.getDeposit_data().getExcess_money());
        result_message.setEstimated_billing_amount(exchange_message.getDeposit_data().getEstimated_billing_amount());
        result_message.setBalance_amount(exchange_message.getDeposit_data().getBalance_amount());

        /** JECCS預り金 */
        if(exchange_message.getDeposit_data().getExcess_money().compareTo(BigDecimal.ZERO) > 0){
            if(exchange_message.getDeposit_request().getExcess_money_handling_category().equals("1")){
                result_message.setJeccs_deposit(exchange_message.getDeposit_data().getExcess_money());
                result_message.setExcess_money(BigDecimal.ZERO);
            } else {
                result_message.setJeccs_deposit(BigDecimal.ZERO);
            }
        } else {
            result_message.setJeccs_deposit(BigDecimal.ZERO);
        }
    
        return result_message;
    }
}
