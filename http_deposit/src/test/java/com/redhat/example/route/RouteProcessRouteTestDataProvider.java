package com.redhat.example.route;

// Util
import lombok.Data;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;

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
import com.redhat.example.entity.SaikenCompositeUnitEntity;
import com.redhat.example.entity.SeikyuCompositeUnitEntity;
import com.redhat.example.entity.AvailableDepositAmountDataEntity;
import com.redhat.example.entity.DepositAllocationDataEntity;
import com.redhat.example.entity.DepositDataEntity;

@Data
@Component
public class RouteProcessRouteTestDataProvider {
    
    /** Expected Json Data */
    String[] route_process_json;
    String[] format_check_json;
    String[] deposit_entry_check_json;
    String[] deposit_category_json;
    String[] check_available_deposit_amount_json;
    String[] deposit_allocation_json;
    String[] deposit_json;
    String[] deposit_result_message_json;

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

    // Normal Data
    public void setNormalData() {

        ObjectMapper mapper = new ObjectMapper();

        /** route_request */
        try {
            route_request = mapper.readValue(getRoute_process_request_json(),KijitsuNyukinRequestEntity.class);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }

        /** format_check */
        format_check_request = route_request;
        // ----------------------------------------------------------------
        format_check_response = new FormatCheckResponseType();
        format_check_response.setResponse_result("0");
        format_check_response.setErr_code("");
        format_check_response.setErr_context("");
        
        /** deposit_entry_check */
        deposit_entry_check_request = new DepositEntryCheckRequestType();
        deposit_entry_check_request.setRequest_id(route_request.getRequest_id());
        deposit_entry_check_request.setCard_number(route_request.getCard_number());
        deposit_entry_check_request.setCustomer_contract_number(route_request.getCustomer_contract_number());
        deposit_entry_check_request.setCustomer_billing_due_date(route_request.getCustomer_billing_due_date());
        deposit_entry_check_request.setContract_settlement_date(route_request.getContract_settlement_date());
        deposit_entry_check_request.setDeposit_date(route_request.getDeposit_date());
        deposit_entry_check_request.setDeposit_amount(route_request.getDeposit_amount());
        deposit_entry_check_request.setExcess_money_handling_category(route_request.getExcess_money_handling_category());
        // ----------------------------------------------------------------
        deposit_entry_check_response = new DepositEntryCheckResponseType();
        deposit_entry_check_response.setService_request(deposit_entry_check_request);
        deposit_entry_check_response.setResponse_result("0");
        deposit_entry_check_response.setErr_code("");
        deposit_entry_check_response.setErr_context("");

        /** deposit_category */
        deposit_category_request = new DepositCategoryRequestType();
        deposit_category_request.setRequest_id(route_request.getRequest_id());
        deposit_category_request.setCustomer_contract_number(route_request.getCustomer_contract_number());
        deposit_category_request.setCustomer_billing_due_date(route_request.getCustomer_billing_due_date());
        deposit_category_request.setContract_settlement_date(route_request.getContract_settlement_date());
        deposit_category_request.setDeposit_date(route_request.getDeposit_date());
        // ----------------------------------------------------------------
        deposit_category_response = new DepositCategoryResponseType();
        deposit_category_response.setService_request(deposit_category_request);
        deposit_category_response.setResponse_result("0");
        deposit_category_response.setErr_code("");
        deposit_category_response.setErr_context("");
        deposit_category_response.setDeposit_category_code("kijitsu");

        /** check_available_deposit_amount */
        check_available_deposit_amount_request = new CheckAvailableDepositAmountRequestType();
        check_available_deposit_amount_request.setRequest_id(route_request.getRequest_id());
        check_available_deposit_amount_request.setDeposit_date(route_request.getDeposit_date());
        check_available_deposit_amount_request.setCustomer_contract_number(route_request.getCustomer_contract_number());
        check_available_deposit_amount_request.setCustomer_billing_due_date(route_request.getCustomer_billing_due_date());
        check_available_deposit_amount_request.setContract_settlement_date(route_request.getContract_settlement_date());
        check_available_deposit_amount_request.setDeposit_category_code(deposit_category_response.getDeposit_category_code());
        // ----------------------------------------------------------------
        check_available_deposit_amount_response = new CheckAvailableDepositAmountResponseType();
        check_available_deposit_amount_response.setService_request(check_available_deposit_amount_request);
        check_available_deposit_amount_response.setResponse_result("0");
        check_available_deposit_amount_response.setErr_code("");
        check_available_deposit_amount_response.setErr_context("");
        try {
            check_available_deposit_amount_response.setDeposit_available_amount_data(mapper.readValue(getDeposit_available_amount_data_json(), AvailableDepositAmountDataEntity.class));
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        
        /** deposit_allocation */
        deposit_allocation_request = new DepositAllocationRequestType();
        deposit_allocation_request.setRequest_id(route_request.getRequest_id());
        deposit_allocation_request.setCustomer_contract_number(route_request.getCustomer_contract_number());
        deposit_allocation_request.setDeposit_date(route_request.getDeposit_date());
        deposit_allocation_request.setCustomer_billing_due_date(route_request.getCustomer_billing_due_date());
        deposit_allocation_request.setContract_settlement_date(route_request.getContract_settlement_date());
        deposit_allocation_request.setDeposit_category_code(deposit_category_response.getDeposit_category_code());
        deposit_allocation_request.setDeposit_amount(route_request.getDeposit_amount());
        deposit_allocation_request.setExcess_money_handling_category(route_request.getExcess_money_handling_category());
        deposit_allocation_request.setDeposit_available_amount_data(check_available_deposit_amount_response.getDeposit_available_amount_data());
        // ----------------------------------------------------------------
        deposit_allocation_response = new DepositAllocationResponseType();
        deposit_allocation_response.setService_request(deposit_allocation_request);
        deposit_allocation_response.setResponse_result("0");
        deposit_allocation_response.setErr_code("");
        deposit_allocation_response.setErr_context("");
        try {
            deposit_allocation_response.setDeposit_allocation_data(mapper.readValue(getDeposit_allocation_data_json(), DepositAllocationDataEntity.class));
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }

        /** deposit */
        deposit_request = new DepositRequestType();
        deposit_request.setRequest_id(route_request.getRequest_id());
        deposit_request.setCustomer_contract_number(route_request.getCustomer_contract_number());
        deposit_request.setDeposit_date(route_request.getDeposit_date());
        deposit_request.setCustomer_billing_due_date(route_request.getCustomer_billing_due_date());
        deposit_request.setContract_settlement_date(route_request.getContract_settlement_date());
        deposit_request.setDeposit_category_code(deposit_category_response.getDeposit_category_code());
        deposit_request.setDeposit_amount(route_request.getDeposit_amount());
        deposit_request.setExcess_money_handling_category(route_request.getExcess_money_handling_category());
        deposit_request.setDeposit_allocation_data(deposit_allocation_response.getDeposit_allocation_data());
        //----------------------------------------------------------------
        deposit_response = new DepositResponseType();
        deposit_response.setService_request(deposit_request);
        deposit_response.setResponse_result("0");
        deposit_response.setErr_code("");
        deposit_response.setErr_context("");
        try {
            deposit_response.setDeposit_data(mapper.readValue(getDeposit_data_json(), DepositDataEntity.class));
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }

        /** deposit_result_message */
        deposit_result_message_request = new DepositResultMessageRequestType();
        deposit_result_message_request.setDeposit_request(route_request);
        deposit_result_message_request.setDeposit_result("0");
        deposit_result_message_request.setErr_code("");
        deposit_result_message_request.setErr_context("");
        deposit_result_message_request.setDeposit_category_code(deposit_category_response.getDeposit_category_code());
        deposit_result_message_request.setDeposit_data(deposit_response.getDeposit_data());
        //----------------------------------------------------------------
        deposit_result_message_response = new KijitsuNyukinResponseEntity();
        deposit_result_message_response.setDeposit_request(route_request);
        deposit_result_message_response.setDeposit_result("0");
        deposit_result_message_response.setErr_code("");
        deposit_result_message_response.setErr_context("");
        deposit_result_message_response.setDeposit_category_code(deposit_category_response.getDeposit_category_code());
        deposit_result_message_response.setDeposit_allocation_amount(deposit_response.getDeposit_data().getDeposit_allocation_amount());
        deposit_result_message_response.setExcess_money(deposit_response.getDeposit_data().getExcess_money());
        deposit_result_message_response.setJeccs_deposit(deposit_response.getDeposit_data().getExcess_money());
        deposit_result_message_response.setEstimated_billing_amount(deposit_response.getDeposit_data().getEstimated_billing_amount());
        deposit_result_message_response.setBalance_amount(deposit_response.getDeposit_data().getBalance_amount());

        /** route_response */
        route_response = deposit_result_message_response;

        /** Set Json */
        setNormalJsonData();
    }

    // Normal Json Data
    public void setNormalJsonData() {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            route_process_json = new String[] {
                mapper.writeValueAsString(route_request), 
                mapper.writeValueAsString(route_response)
            };
            format_check_json = new String[] {
                mapper.writeValueAsString(format_check_request), 
                mapper.writeValueAsString(format_check_response), 
                mapper.writeValueAsString(format_check_response)
            };
            deposit_entry_check_json = new String[] {
                mapper.writeValueAsString(deposit_entry_check_request),
                mapper.writeValueAsString(deposit_entry_check_response),
                mapper.writeValueAsString(deposit_entry_check_response)
            };
            deposit_category_json = new String[] {
                mapper.writeValueAsString(deposit_category_request),
                mapper.writeValueAsString(deposit_category_response),
                mapper.writeValueAsString(deposit_category_response.getDeposit_category_code())
            };
            check_available_deposit_amount_json = new String[] {
                mapper.writeValueAsString(check_available_deposit_amount_request),
                mapper.writeValueAsString(check_available_deposit_amount_response),
                mapper.writeValueAsString(check_available_deposit_amount_response.getDeposit_available_amount_data())
            };
            deposit_allocation_json = new String[] {
                mapper.writeValueAsString(deposit_allocation_request),
                mapper.writeValueAsString(deposit_allocation_response),
                mapper.writeValueAsString(deposit_allocation_response.getDeposit_allocation_data())
            };
            deposit_json = new String[] {
                mapper.writeValueAsString(deposit_request),
                mapper.writeValueAsString(deposit_response),
                mapper.writeValueAsString(deposit_response.getDeposit_data())
            };
            deposit_result_message_json = new String[] {
                mapper.writeValueAsString(deposit_result_message_request),
                mapper.writeValueAsString(deposit_result_message_response),
                mapper.writeValueAsString(deposit_result_message_response)
            };

        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /** route_process_request_json */
    public String getRoute_process_request_json() {
        String route_process_request_json = """
            {
                "REQUEST_ID": "A-001", 
                "CARD_NUMBER": "3540000100010001", 
                "CUSTOMER_CONTRACT_NUMBER": "0000000001", 
                "CUSTOMER_BILLING_DUE_DATE": "20240515", 
                "CONTRACT_SETTLEMENT_DATE":"20240610", 
                "DEPOSIT_DATE": "20240611", 
                "DEPOSIT_AMOUNT": 10000, 
                "EXCESS_MONEY_HANDLING_CATEGORY": "9"
            }
            """;
        return route_process_request_json;
    }

    /** deposit_available_amount_data_json */
    public String getDeposit_available_amount_data_json() {
        String deposit_available_amount_data_json = """
            {
                "estimated_billing_amount": {
                    "total_billing": {"billing_principal_amount": 80000,"billing_interest_amount": 369,"deposit_principal_amount": 0,"deposit_interest_amount": 0},
                    "products_billing_map": {
                        "sprv": {"billing_principal_amount": 30000,"billing_interest_amount": 369,"deposit_principal_amount": 0,"deposit_interest_amount": 0},
                        "sp1": {"billing_principal_amount": 50000,"billing_interest_amount": 0,"deposit_principal_amount": 0,"deposit_interest_amount": 0}
                    }
                },
                "deposit_available_amount": {
                    "total_amout": {"principal_amount": 80000,"interest_amount": 369},
                    "products_amount_map": {
                        "sprv": {"principal_amount": 30000,"interest_amount": 369},
                        "sp1": {"principal_amount": 50000,"interest_amount": 0}
                    }
                }
            }
            """;
        return deposit_available_amount_data_json;
    }

    /** deposit_allocation_data_json */
    public String getDeposit_allocation_data_json() {
        String deposit_allocation_data_json = """
            {
                "deposit_allocation_amount": {
                    "total_amout": {"principal_amount": 10000,"interest_amount": 0},
                    "products_amount_map": {
                        "sprv": {"principal_amount": 10000,"interest_amount": 0},
                        "sp1": {"principal_amount": 0,"interest_amount": 0}
                    }
                },
                "estimated_billing_amount": {
                    "total_billing": {"billing_principal_amount": 70000,"billing_interest_amount": 369,"deposit_principal_amount": 10000,"deposit_interest_amount": 0},
                    "products_billing_map": {
                        "sprv": {"billing_principal_amount": 20000,"billing_interest_amount": 369,"deposit_principal_amount": 10000,"deposit_interest_amount": 0},
                        "sp1": {"billing_principal_amount": 50000,"billing_interest_amount": 0,"deposit_principal_amount": 0,"deposit_interest_amount": 0}
                    }
                },
                "excess_money": 0
            }
            """;
        return deposit_allocation_data_json;
    }

    /** deposit_data_json */
    public String getDeposit_data_json() {
        String deposit_data_json = """
            {
                "deposit_allocation_amount": {
                    "total_amout": {"principal_amount": 10000,"interest_amount": 0},
                    "products_amount_map": {
                        "sprv": {"principal_amount": 10000,"interest_amount": 0},
                        "sp1": {"principal_amount": 0,"interest_amount": 0}
                    }
                },
                "excess_money": 0,
                "estimated_billing_amount": {
                    "total_billing": {"billing_principal_amount": 70000,"billing_interest_amount": 369,"deposit_principal_amount": 10000,"deposit_interest_amount": 0},
                    "products_billing_map": {
                        "sprv": {"billing_principal_amount": 20000,"billing_interest_amount": 369,"deposit_principal_amount": 10000,"deposit_interest_amount": 0},
                        "sp1": {"billing_principal_amount": 50000,"billing_interest_amount": 0,"deposit_principal_amount": 0,"deposit_interest_amount": 0}
                    }
                },
                "balance_amount": {
                    "total_amout": {"principal_amount": 80000,"interest_amount": 0},
                    "products_amount_map": {
                        "sprv": {"principal_amount": 30000,"interest_amount": 0},
                        "sp1": {"principal_amount": 50000,"interest_amount": 0}
                    }
                }
            }
            """;
        return deposit_data_json;
    }
}
