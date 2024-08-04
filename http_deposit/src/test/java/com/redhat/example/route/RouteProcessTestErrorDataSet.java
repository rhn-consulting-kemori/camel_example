package com.redhat.example.route;

// Util
import lombok.Data;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

// Business Object
import com.redhat.example.entity.KijitsuNyukinRequestEntity;
import com.redhat.example.type.FormatCheckResponseType;
import com.redhat.example.type.DepositEntryCheckRequestType;
import com.redhat.example.type.DepositEntryCheckResponseType;
import com.redhat.example.type.DepositCategoryRequestType;
import com.redhat.example.type.DepositCategoryResponseType;
import com.redhat.example.type.CheckAvailableDepositAmountRequestType;
import com.redhat.example.type.CheckAvailableDepositAmountResponseType;
import com.redhat.example.type.DepositAllocationRequestType;
import com.redhat.example.type.DepositAllocationResponseType;
import com.redhat.example.type.DepositRequestType;
import com.redhat.example.type.DepositResponseType;
import com.redhat.example.type.DepositResultMessageRequestType;
import com.redhat.example.entity.KijitsuNyukinResponseEntity;
import com.redhat.example.entity.AvailableDepositAmountDataEntity;
import com.redhat.example.entity.DepositAllocationDataEntity;
import com.redhat.example.entity.DepositDataEntity;

@Data
@Component
public class RouteProcessTestErrorDataSet {

    /** Expected Object Data */
    KijitsuNyukinRequestEntity route_request;
    KijitsuNyukinRequestEntity format_check_request;
    FormatCheckResponseType format_check_response;
    DepositEntryCheckRequestType deposit_entry_check_request;
    DepositEntryCheckResponseType deposit_entry_check_response;
    DepositCategoryRequestType deposit_category_request;
    DepositCategoryResponseType deposit_category_response;
    CheckAvailableDepositAmountRequestType check_available_deposit_amount_request;
    CheckAvailableDepositAmountResponseType check_available_deposit_amount_response;
    DepositAllocationRequestType deposit_allocation_request;
    DepositAllocationResponseType deposit_allocation_response;
    DepositRequestType deposit_request;
    DepositResponseType deposit_response;
    DepositResultMessageRequestType deposit_result_message_request;
    KijitsuNyukinResponseEntity deposit_result_message_response;
    KijitsuNyukinResponseEntity route_response;

    ObjectMapper mapper = new ObjectMapper();

    // Constractor: Set Test Data
    public RouteProcessTestErrorDataSet() {
        setRoute_request();
        setFormat_check_request();
        setFormat_check_response();
        setDeposit_entry_check_request();
        setDeposit_entry_check_response();
        setDeposit_category_request();
        setDeposit_category_response();
        setCheck_available_deposit_amount_request();
        setCheck_available_deposit_amount_response();
        setDeposit_allocation_request();
        setDeposit_allocation_response();
        setDeposit_request();
        setDeposit_response();
        setDeposit_result_message_request();
        setDeposit_result_message_response();
        setRoute_response();
    }

    /** KijitsuNyukinRequestEntity route_request; */
    public void setRoute_request() {
        String route_process_request_json = """
            {
                "REQUEST_ID": "A-002", 
                "CARD_NUMBER": "3540000100010002", 
                "CUSTOMER_CONTRACT_NUMBER": "A000000002", 
                "CUSTOMER_BILLING_DUE_DATE": "20240515", 
                "CONTRACT_SETTLEMENT_DATE":"20240610", 
                "DEPOSIT_DATE": "20240611", 
                "DEPOSIT_AMOUNT": 10000, 
                "EXCESS_MONEY_HANDLING_CATEGORY": "9"
            }
            """;
        try {
            route_request = mapper.readValue(route_process_request_json,KijitsuNyukinRequestEntity.class);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /** KijitsuNyukinRequestEntity format_check_request */
    public void setFormat_check_request() {
        format_check_request = route_request;
    }

    /** FormatCheckResponseType format_check_response */
    public void setFormat_check_response() {
        format_check_response = new FormatCheckResponseType();
        format_check_response.setResponse_result("1");
        format_check_response.setErr_code("E02");
        format_check_response.setErr_context("customer_contract_number: numeric error");
    }

    /** DepositEntryCheckRequestType deposit_entry_check_request */
    public void setDeposit_entry_check_request() {
        deposit_entry_check_request = new DepositEntryCheckRequestType();
    }

    /** DepositEntryCheckResponseType deposit_entry_check_response */
    public void setDeposit_entry_check_response() {
        deposit_entry_check_response = new DepositEntryCheckResponseType();
    }

    /** DepositCategoryRequestType deposit_category_request */
    public void setDeposit_category_request() {
        deposit_category_request = new DepositCategoryRequestType();
    }

    /** DepositCategoryResponseType deposit_category_response */
    public void setDeposit_category_response() {
        deposit_category_response = new DepositCategoryResponseType();
    }

    /** CheckAvailableDepositAmountRequestType check_available_deposit_amount_request */
    public void setCheck_available_deposit_amount_request() {
        check_available_deposit_amount_request = new CheckAvailableDepositAmountRequestType();
    }

    /** CheckAvailableDepositAmountResponseType check_available_deposit_amount_response */
    public void setCheck_available_deposit_amount_response() {
        check_available_deposit_amount_response = new CheckAvailableDepositAmountResponseType();
    }

    /** DepositAllocationRequestType deposit_allocation_request */
    public void setDeposit_allocation_request() {
        deposit_allocation_request = new DepositAllocationRequestType();
    }

    /** DepositAllocationResponseType deposit_allocation_response */
    public void setDeposit_allocation_response() {
        deposit_allocation_response = new DepositAllocationResponseType();
    }

    /** DepositRequestType deposit_request */
    public void setDeposit_request() {
        deposit_request = new DepositRequestType();
    }

    /** DepositResponseType deposit_response */
    public void setDeposit_response() {
        deposit_response = new DepositResponseType();
    }

    /** DepositResultMessageRequestType deposit_result_message_request */
    public void setDeposit_result_message_request() {
        deposit_result_message_request = new DepositResultMessageRequestType();
        deposit_result_message_request.setDeposit_request(route_request);
        deposit_result_message_request.setDeposit_result("1");
        deposit_result_message_request.setErr_code("E02");
        deposit_result_message_request.setErr_context("customer_contract_number: numeric error");
        deposit_result_message_request.setDeposit_category_code("");
        deposit_result_message_request.setDeposit_data(new DepositDataEntity());
    }

    /** KijitsuNyukinResponseEntity deposit_result_message_response */
    public void setDeposit_result_message_response() {
        deposit_result_message_response = new KijitsuNyukinResponseEntity();
        deposit_result_message_response.setDeposit_request(route_request);
        deposit_result_message_response.setDeposit_result("1");
        deposit_result_message_response.setErr_code("E02");
        deposit_result_message_response.setErr_context("customer_contract_number: numeric error");
    }

    /** KijitsuNyukinResponseEntity route_response */
    public void setRoute_response() {
        route_response = deposit_result_message_response;
    }

}
