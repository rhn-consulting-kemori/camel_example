package com.redhat.example.route;

// Util
import lombok.Data;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

// Business Object
import com.redhat.example.entity.KijitsuNyukinRequestEntity;
import com.redhat.example.entity.KijitsuNyukinResponseEntity;
import com.redhat.example.type.DepositResultMessageRequestType;
import com.redhat.example.type.KijitsuAllocationDepositRequestType;
import com.redhat.example.type.KijitsuAllocationDepositResponseType;
import com.redhat.example.type.FormatCheckResponseType;
import com.redhat.example.type.DepositEntryCheckRequestType;
import com.redhat.example.type.DepositEntryCheckResponseType;

@Data
@Component
public class RouteProcessTestDataProvider {

    /** Test Config */
    public static final boolean RULE_INTEGRATION_FLG = false;

    /** Expected Object Data */
    KijitsuNyukinRequestEntity route_request;
    KijitsuNyukinResponseEntity route_response;
    DepositResultMessageRequestType deposit_result_message_request;
    KijitsuNyukinResponseEntity deposit_result_message_response;
    KijitsuAllocationDepositRequestType kijitsu_allocation_deposit_request;
    KijitsuAllocationDepositResponseType kijitsu_allocation_deposit_response;
    KijitsuNyukinRequestEntity format_check_request;
    FormatCheckResponseType format_check_response;
    DepositEntryCheckRequestType deposit_entry_check_request;
    DepositEntryCheckResponseType deposit_entry_check_response;

    /** Expected Json Data */
    String[] route_process_json;
    String[] deposit_result_message_json;
    String[] kijitsu_allocation_deposit_json;
    String[] format_check_json;
    String[] deposit_entry_check_json;

    // Normal Data
    public void setNormalData() {

        RouteProcessTestNormalDataSet normalDataSet = new RouteProcessTestNormalDataSet();
        route_request = normalDataSet.getRoute_request();
        route_response = normalDataSet.getRoute_response();
        deposit_result_message_request = normalDataSet.getDeposit_result_message_request();
        deposit_result_message_response = normalDataSet.getDeposit_result_message_response();
        kijitsu_allocation_deposit_request = normalDataSet.getKijitsu_allocation_deposit_request();
        kijitsu_allocation_deposit_response = normalDataSet.getKijitsu_allocation_deposit_response();
        format_check_request = normalDataSet.getFormat_check_request();
        format_check_response = normalDataSet.getFormat_check_response();
        deposit_entry_check_request = normalDataSet.getDeposit_entry_check_request();
        deposit_entry_check_response = normalDataSet.getDeposit_entry_check_response();

        /** Set Json */
        setNormalJsonData();
    }

    // Error Data
    public void setErrorData() {

        RouteProcessTestErrorDataSet errorDataSet = new RouteProcessTestErrorDataSet();
        route_request = errorDataSet.getRoute_request();
        route_response = errorDataSet.getRoute_response();
        deposit_result_message_request = errorDataSet.getDeposit_result_message_request();
        deposit_result_message_response = errorDataSet.getDeposit_result_message_response();
        kijitsu_allocation_deposit_request = errorDataSet.getKijitsu_allocation_deposit_request();
        kijitsu_allocation_deposit_response = errorDataSet.getKijitsu_allocation_deposit_response();
        format_check_request = errorDataSet.getFormat_check_request();
        format_check_response = errorDataSet.getFormat_check_response();
        deposit_entry_check_request = errorDataSet.getDeposit_entry_check_request();
        deposit_entry_check_response = errorDataSet.getDeposit_entry_check_response();

        /** Set Json */
        setErrorJsonData();
    }

    // Normal Json Data
    public void setNormalJsonData() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            route_process_json = new String[] {
                mapper.writeValueAsString(route_request),
                mapper.writeValueAsString(route_response),
                mapper.writeValueAsString(route_response)
            };
            deposit_result_message_json = new String[] {
                mapper.writeValueAsString(deposit_result_message_request),
                mapper.writeValueAsString(deposit_result_message_response),
                mapper.writeValueAsString(deposit_result_message_response)
            };
            kijitsu_allocation_deposit_json = new String[] {
                mapper.writeValueAsString(kijitsu_allocation_deposit_request),
                mapper.writeValueAsString(kijitsu_allocation_deposit_response),
                mapper.writeValueAsString(kijitsu_allocation_deposit_response.getDeposit_data())
            };
            format_check_json = new String[] {
                mapper.writeValueAsString(format_check_request),
                mapper.writeValueAsString(format_check_response),
                mapper.writeValueAsString(format_check_response)
            };
            deposit_entry_check_json = new String[] {
                mapper.writeValueAsString(deposit_entry_check_request),
                mapper.writeValueAsString(deposit_entry_check_response),
                mapper.writeValueAsString(deposit_entry_check_response.getResponse_result())
            };
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    // Error Json Data
    public void setErrorJsonData() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            route_process_json = new String[] {
                mapper.writeValueAsString(route_request),
                mapper.writeValueAsString(route_response),
                mapper.writeValueAsString(route_response)
            };
            deposit_result_message_json = new String[] {
                mapper.writeValueAsString(deposit_result_message_request),
                mapper.writeValueAsString(deposit_result_message_response),
                mapper.writeValueAsString(deposit_result_message_response)
            };
            kijitsu_allocation_deposit_json = new String[] {
                mapper.writeValueAsString(kijitsu_allocation_deposit_request),
                mapper.writeValueAsString(kijitsu_allocation_deposit_response),
                mapper.writeValueAsString(kijitsu_allocation_deposit_response.getDeposit_data())
            };
            format_check_json = new String[] {
                mapper.writeValueAsString(format_check_request),
                mapper.writeValueAsString(format_check_response),
                mapper.writeValueAsString(format_check_response)
            };
            deposit_entry_check_json = new String[] {
                mapper.writeValueAsString(deposit_entry_check_request),
                mapper.writeValueAsString(deposit_entry_check_response),
                mapper.writeValueAsString(deposit_entry_check_response.getResponse_result())
            };
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
