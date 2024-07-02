package com.redhat.example.rule;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

// Spring
import org.springframework.stereotype.Component;

// Business Object
import com.redhat.example.entity.KijitsuNyukinRequestEntity;
import com.redhat.example.entity.KijitsuNyukinResponseEntity;
import com.redhat.example.entity.DepositDataEntity;

import com.redhat.example.type.DepositResultMessageRequestType;

@Component
public class DepositResultMessageRule {

    public KijitsuNyukinResponseEntity executeRule(DepositResultMessageRequestType rule_request) {

        // Get Data from Rule Request
        KijitsuNyukinRequestEntity deposit_request = rule_request.getDeposit_request();
        String deposit_result = rule_request.getDeposit_result();
        String err_code = rule_request.getErr_code();
        String err_context = rule_request.getErr_context();
        String deposit_category_code = rule_request.getDeposit_category_code();
        DepositDataEntity deposit_data = rule_request.getDeposit_data();

        // Set Data
        KijitsuNyukinResponseEntity result_message = new KijitsuNyukinResponseEntity();
        
        /** Deposit_request */
        result_message.setDeposit_request(deposit_request);

        /** Deposit_result */
        result_message.setDeposit_result(deposit_result);
        result_message.setErr_code(err_code);
        result_message.setErr_context(err_context);   
        
        if(deposit_result.equals("0")){
            /** Deposit_contents */
            result_message.setDeposit_category_code(deposit_category_code);
            result_message.setDeposit_allocation_amount(deposit_data.getDeposit_allocation_amount());
            result_message.setExcess_money(deposit_data.getExcess_money());
            result_message.setEstimated_billing_amount(deposit_data.getEstimated_billing_amount());
            result_message.setBalance_amount(deposit_data.getBalance_amount());

            /** JECCS預り金 */
            if(deposit_data.getExcess_money().compareTo(BigDecimal.ZERO) > 0){
                if(deposit_request.getExcess_money_handling_category().equals("1")){
                    result_message.setJeccs_deposit(deposit_data.getExcess_money());
                    result_message.setExcess_money(BigDecimal.ZERO);
                } else {
                    result_message.setJeccs_deposit(BigDecimal.ZERO);
                }
            } else {
                result_message.setJeccs_deposit(BigDecimal.ZERO);
            }
        }
        return result_message;
    }
}
