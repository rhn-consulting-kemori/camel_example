package com.redhat.example.rule;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

// Spring
import org.springframework.stereotype.Component;

// Business Object
import com.redhat.example.entity.KijitsuNyukinRequestEntity;
import com.redhat.example.type.FormatCheckResponseType;

@Component
public class FormatCheckRule {

    /** ルール実行 */
    public FormatCheckResponseType executeRule(KijitsuNyukinRequestEntity rule_request) {
        
        FormatCheckResponseType response = new FormatCheckResponseType();
        response.setResponse_result("0");
        response.setErr_code("");
        response.setErr_context("");

        /**
         * 未設定チェック
         */
        if(Objects.isNull(rule_request.getRequest_id()) || 
            Objects.isNull(rule_request.getCard_number()) || 
            Objects.isNull(rule_request.getCustomer_contract_number()) || 
            Objects.isNull(rule_request.getCustomer_billing_due_date()) || 
            Objects.isNull(rule_request.getContract_settlement_date()) || 
            Objects.isNull(rule_request.getDeposit_date()) || 
            Objects.isNull(rule_request.getDeposit_amount()) || 
            Objects.isNull(rule_request.getExcess_money_handling_category())) {

            response.setResponse_result("1");
            response.setErr_code("E99");
            response.setErr_context("Parameter non set error");
            
        } else {
            /**
            * Null
            * - request_id
            */
            if(rule_request.getRequest_id().length() == 0) {
                response.setResponse_result("1");
                response.setErr_code("E01");
                response.setErr_context("request_id: null error");
            }
        
            /** 
            * Numeric & Length & Exsist
            * - card_number
            */
            if(!check_length(rule_request.getCard_number(), 16)) {
                response.setResponse_result("1");
                response.setErr_code("E01");
                response.setErr_context("card_number: length error");
            }
            if(!check_numerical(rule_request.getCard_number())){
                response.setResponse_result("1");
                response.setErr_code("E02");
                response.setErr_context("card_number: numeric error");
            }

            /** 
            * Numeric & Length & Exsist
            * - customer_contract_number
            */
            if(!check_length(rule_request.getCustomer_contract_number(), 10)) {
                response.setResponse_result("1");
                response.setErr_code("E01");
                response.setErr_context("customer_contract_number: length error");
            }
            if(!check_numerical(rule_request.getCustomer_contract_number())){
                response.setResponse_result("1");
                response.setErr_code("E02");
                response.setErr_context("customer_contract_number: numeric error");
            }

            /**
             * Numeric
             * - customer_billing_due_date
             * - contract_settlement_date
             * - deposit_date
             */
            if(!check_numerical(rule_request.getCustomer_billing_due_date())){
                response.setResponse_result("1");
                response.setErr_code("E02");
                response.setErr_context("customer_billing_due_date: numeric error");
            }
            if(!check_numerical(rule_request.getContract_settlement_date())){
                response.setResponse_result("1");
                response.setErr_code("E02");
                response.setErr_context("contract_settlement_date: numeric error");
            }
            if(!check_numerical(rule_request.getDeposit_date())){
                response.setResponse_result("1");
                response.setErr_code("E02");
                response.setErr_context("deposit_date: numeric error");
            }

            /**
             * Numeric
             * - excess_money_handling_category
             */
            if(!check_numerical(rule_request.getExcess_money_handling_category())){
                response.setResponse_result("1");
                response.setErr_code("E02");
                response.setErr_context("excess_money_handling_category: numeric error");
            }
            
        }

        return response;

    }

    /** 数値チェック */
    private boolean check_numerical(String text){
        return text.matches("[+-]?\\d*(\\.\\d+)?");
    }

    /** 桁数チェック */
    private boolean check_length(String text, int str_length){
        if(text.length() == str_length) {
            return true;
        } else {
            return false;
        }
    }

}
