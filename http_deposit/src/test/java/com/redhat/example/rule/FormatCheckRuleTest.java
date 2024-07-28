package com.redhat.example.rule;

// Util
import java.math.BigDecimal;

// Junit
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvFileSource;

// Business Object
import com.redhat.example.entity.KijitsuNyukinRequestEntity;
import com.redhat.example.type.FormatCheckResponseType;

public class FormatCheckRuleTest {
    FormatCheckRule rule;

    @BeforeEach
    void setUp() {
        rule = new FormatCheckRule();
    }

    @ParameterizedTest
    @DisplayName("バリデーションリスト")
    @CsvFileSource(resources = "/format_check_rule_test.csv", numLinesToSkip = 1)
    //@CsvSource({ "A-001,3540000100010001,0000000001,20240515,20240610,20240611,10000,9,0,'','',A-001: NORMAL DATA OK" })
    void test_normal(String request_id, String card_number, String customer_contract_number, String customer_billing_due_date, String contract_settlement_date, 
        String deposit_date, long deposit_amount, String excess_money_handling_category, String response_result, String err_code, String err_context, String case_message) {

        // Given
        KijitsuNyukinRequestEntity rule_request = new KijitsuNyukinRequestEntity();
        rule_request.setRequest_id(request_id);
        rule_request.setCard_number(card_number);
        rule_request.setCustomer_contract_number(customer_contract_number);
        rule_request.setCustomer_billing_due_date(customer_billing_due_date);
        rule_request.setContract_settlement_date(contract_settlement_date);
        rule_request.setDeposit_date(deposit_date);
        rule_request.setDeposit_amount(BigDecimal.valueOf(deposit_amount));
        rule_request.setExcess_money_handling_category(excess_money_handling_category);

        // When
        FormatCheckResponseType response = rule.executeRule(rule_request);

        // then
        assertEquals(response_result, response.getResponse_result(), case_message);
        assertEquals(err_code, response.getErr_code(), case_message);
        assertEquals(err_context, response.getErr_context(), case_message);
    }
}