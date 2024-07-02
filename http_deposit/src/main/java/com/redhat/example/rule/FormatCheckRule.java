package com.redhat.example.rule;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

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
         * - customer_contract_number
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

        // -------------------------------------------------------------------------
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
