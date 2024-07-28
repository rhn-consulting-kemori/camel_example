package com.redhat.example.rule;

// Util
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

// Junit
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

// Business Object
import com.redhat.example.entity.KijitsuNyukinResponseEntity;
import com.redhat.example.entity.SaikenCompositeUnitEntity;
import com.redhat.example.entity.SaikenSimpleUnitEntity;
import com.redhat.example.entity.SeikyuCompositeUnitEntity;
import com.redhat.example.entity.SeikyuSimpleUnitEntity;
import com.redhat.example.type.DepositResultMessageRequestType;
import com.redhat.example.entity.DepositDataEntity;
import com.redhat.example.entity.KijitsuNyukinRequestEntity;

// Rule
import com.redhat.example.rule.DepositResultMessageRule;

public class DepositResultMessageRuleTest {
    DepositResultMessageRule rule;

    @BeforeEach
    void setUp() {
        rule = new DepositResultMessageRule();
    }

    @ParameterizedTest
    @DisplayName("正常ケース")
    @MethodSource("testNormalDataProvider")
    void test_normal(DepositResultMessageRequestType inputobj, Map<String, Object> expected) {
        // given
        
        // when
        KijitsuNyukinResponseEntity entity = rule.executeRule(inputobj);

        // then
        // ----------------------------------------------------------------
        assertEquals(expected.get("request_id").toString(), entity.getDeposit_request().getRequest_id(), "request_id");
        assertEquals(expected.get("card_number").toString(), entity.getDeposit_request().getCard_number(), "card_number");
        assertEquals(expected.get("customer_contract_number").toString(), entity.getDeposit_request().getCustomer_contract_number(), "customer_contract_number");
        assertEquals(expected.get("customer_billing_due_date").toString(), entity.getDeposit_request().getCustomer_billing_due_date(), "customer_billing_due_date");
        assertEquals(expected.get("contract_settlement_date").toString(), entity.getDeposit_request().getContract_settlement_date(), "contract_settlement_date");
        assertEquals(expected.get("deposit_date").toString(), entity.getDeposit_request().getDeposit_date(), "deposit_date");
        assertEquals(new BigDecimal(expected.get("deposit_amount").toString()), entity.getDeposit_request().getDeposit_amount(), "deposit_amount");
        assertEquals(expected.get("excess_money_handling_category").toString(), entity.getDeposit_request().getExcess_money_handling_category(), "excess_money_handling_category");
        assertEquals(expected.get("deposit_result").toString(), entity.getDeposit_result(), "deposit_result");
        assertEquals(expected.get("err_code").toString(), entity.getErr_code(), "err_code");
        assertEquals(expected.get("err_context").toString(), entity.getErr_context(), "err_context");
        assertEquals(expected.get("deposit_category_code").toString(), entity.getDeposit_category_code(), "deposit_category_code");
        // ----------------------------------------------------------------
        // deposit_allocation_amount
        assertEquals(new BigDecimal(expected.get("alloc_total_principal").toString()), entity.getDeposit_allocation_amount().getTotal_amout().getPrincipal_amount(), "alloc_total_principal");
        assertEquals(new BigDecimal(expected.get("alloc_total_interest").toString()), entity.getDeposit_allocation_amount().getTotal_amout().getInterest_amount(), "alloc_total_interest");
        assertEquals(new BigDecimal(expected.get("alloc_sp1_principal").toString()), entity.getDeposit_allocation_amount().getProducts_amount_map().get("sp1").getPrincipal_amount(), "alloc_sp1_principal");
        assertEquals(new BigDecimal(expected.get("alloc_sp1_interest").toString()), entity.getDeposit_allocation_amount().getProducts_amount_map().get("sp1").getInterest_amount(), "alloc_sp1_interest");
        assertEquals(new BigDecimal(expected.get("alloc_sprv_principal").toString()), entity.getDeposit_allocation_amount().getProducts_amount_map().get("sprv").getPrincipal_amount(), "alloc_sprv_principal");
        assertEquals(new BigDecimal(expected.get("alloc_sprv_interest").toString()), entity.getDeposit_allocation_amount().getProducts_amount_map().get("sprv").getInterest_amount(), "alloc_sprv_interest");
        // ----------------------------------------------------------------
        // excess_money, jeccs_deposit
        assertEquals(new BigDecimal(expected.get("excess_money").toString()), entity.getExcess_money(), "excess_money");
        assertEquals(new BigDecimal(expected.get("jeccs_deposit").toString()), entity.getJeccs_deposit(), "jeccs_deposit");
        // ----------------------------------------------------------------
        // estimated_billing_amount
        assertEquals(new BigDecimal(expected.get("total_billing_principal").toString()), entity.getEstimated_billing_amount().getTotal_billing().getBilling_principal_amount(), "total_billing_principal");
        assertEquals(new BigDecimal(expected.get("total_billing_interest").toString()), entity.getEstimated_billing_amount().getTotal_billing().getBilling_interest_amount(), "total_billing_interest");
        assertEquals(new BigDecimal(expected.get("total_deposit_principal").toString()), entity.getEstimated_billing_amount().getTotal_billing().getDeposit_principal_amount(), "total_deposit_principal");
        assertEquals(new BigDecimal(expected.get("total_deposit_interest").toString()), entity.getEstimated_billing_amount().getTotal_billing().getDeposit_interest_amount(), "total_deposit_interest");
        assertEquals(new BigDecimal(expected.get("sp1_billing_principal").toString()), entity.getEstimated_billing_amount().getProducts_billing_map().get("sp1").getBilling_principal_amount(), "sp1_billing_principal");
        assertEquals(new BigDecimal(expected.get("sp1_billing_interest").toString()), entity.getEstimated_billing_amount().getProducts_billing_map().get("sp1").getBilling_interest_amount(), "sp1_billing_interest");
        assertEquals(new BigDecimal(expected.get("sp1_deposit_principal").toString()), entity.getEstimated_billing_amount().getProducts_billing_map().get("sp1").getDeposit_principal_amount(), "sp1_deposit_principal");
        assertEquals(new BigDecimal(expected.get("sp1_deposit_interest").toString()), entity.getEstimated_billing_amount().getProducts_billing_map().get("sp1").getDeposit_interest_amount(), "sp1_deposit_interest");
        assertEquals(new BigDecimal(expected.get("sprv_billing_principal").toString()), entity.getEstimated_billing_amount().getProducts_billing_map().get("sprv").getBilling_principal_amount(), "sprv_billing_principal");
        assertEquals(new BigDecimal(expected.get("sprv_billing_interest").toString()), entity.getEstimated_billing_amount().getProducts_billing_map().get("sprv").getBilling_interest_amount(), "sprv_billing_interest");
        assertEquals(new BigDecimal(expected.get("sprv_deposit_principal").toString()), entity.getEstimated_billing_amount().getProducts_billing_map().get("sprv").getDeposit_principal_amount(), "sprv_deposit_principal");
        assertEquals(new BigDecimal(expected.get("sprv_deposit_interest").toString()), entity.getEstimated_billing_amount().getProducts_billing_map().get("sprv").getDeposit_interest_amount(), "sprv_deposit_interest");
        // ----------------------------------------------------------------
        // balance_amount
        assertEquals(new BigDecimal(expected.get("balance_total_principal").toString()), entity.getBalance_amount().getTotal_amout().getPrincipal_amount(), "balance_total_principal");
        assertEquals(new BigDecimal(expected.get("balance_total_interest").toString()), entity.getBalance_amount().getTotal_amout().getInterest_amount(), "balance_total_interest");
        assertEquals(new BigDecimal(expected.get("balance_sp1_principal").toString()), entity.getBalance_amount().getProducts_amount_map().get("sp1").getPrincipal_amount(), "balance_sp1_principal");
        assertEquals(new BigDecimal(expected.get("balance_sp1_interest").toString()), entity.getBalance_amount().getProducts_amount_map().get("sp1").getInterest_amount(), "balance_sp1_interest");
        assertEquals(new BigDecimal(expected.get("balance_sprv_principal").toString()), entity.getBalance_amount().getProducts_amount_map().get("sprv").getPrincipal_amount(), "balance_sprv_principal");
        assertEquals(new BigDecimal(expected.get("balance_sprv_interest").toString()), entity.getBalance_amount().getProducts_amount_map().get("sprv").getInterest_amount(), "balance_sprv_interest");
    }

    @ParameterizedTest
    @DisplayName("エラーケース")
    @MethodSource("testErrorDataProvider")
    void test_err(DepositResultMessageRequestType inputobj, Map<String, Object> expected) {
        // given
        // when
        KijitsuNyukinResponseEntity entity = rule.executeRule(inputobj);

        // then
        // ----------------------------------------------------------------
        assertEquals(expected.get("request_id").toString(), entity.getDeposit_request().getRequest_id(), "request_id");
        assertEquals(expected.get("card_number").toString(), entity.getDeposit_request().getCard_number(), "card_number");
        assertEquals(expected.get("customer_contract_number").toString(), entity.getDeposit_request().getCustomer_contract_number(), "customer_contract_number");
        assertEquals(expected.get("customer_billing_due_date").toString(), entity.getDeposit_request().getCustomer_billing_due_date(), "customer_billing_due_date");
        assertEquals(expected.get("contract_settlement_date").toString(), entity.getDeposit_request().getContract_settlement_date(), "contract_settlement_date");
        assertEquals(expected.get("deposit_date").toString(), entity.getDeposit_request().getDeposit_date(), "deposit_date");
        assertEquals(new BigDecimal(expected.get("deposit_amount").toString()), entity.getDeposit_request().getDeposit_amount(), "deposit_amount");
        assertEquals(expected.get("excess_money_handling_category").toString(), entity.getDeposit_request().getExcess_money_handling_category(), "excess_money_handling_category");
        assertEquals(expected.get("deposit_result").toString(), entity.getDeposit_result(), "deposit_result");
        assertEquals(expected.get("err_code").toString(), entity.getErr_code(), "err_code");
        assertEquals(expected.get("err_context").toString(), entity.getErr_context(), "err_context");
        // ----------------------------------------------------------------
        assertNull(entity.getDeposit_category_code());
        assertNull(entity.getDeposit_allocation_amount());
        assertNull(entity.getExcess_money());
        assertNull(entity.getJeccs_deposit());
        assertNull(entity.getEstimated_billing_amount());
        assertNull(entity.getBalance_amount());   
    }

    static Stream<Arguments> testNormalDataProvider() {
        List<Map<String, Object>> dataProvider = depositResultMessageRequestTypeProvider();
        List<Map<String, Object>> expectedProvider = new ArrayList<Map<String, Object>>();
        List<DepositResultMessageRequestType> inputDataObjList = new ArrayList<DepositResultMessageRequestType>();
        for (int i=0; i<dataProvider.size(); i++) {
            inputDataObjList.add(getInputType(dataProvider.get(i)));
            expectedProvider.add(getExpectedData(dataProvider.get(i)));
        }
        return Stream.of(
            arguments(inputDataObjList.get(0), expectedProvider.get(0)), 
            arguments(inputDataObjList.get(1), expectedProvider.get(1))
        );
    }
    
    static Stream<Arguments> testErrorDataProvider() {
        List<Map<String, Object>> dataProvider = depositResultMessageRequestTypeProvider();
        List<Map<String, Object>> expectedProvider = new ArrayList<Map<String, Object>>();
        List<DepositResultMessageRequestType> inputDataObjList = new ArrayList<DepositResultMessageRequestType>();
        for (Map<String, Object> entry : dataProvider) {
            inputDataObjList.add(getInputType(entry));
            expectedProvider.add(getExpectedData(entry));
        }
        return Stream.of(
            arguments(inputDataObjList.get(2), expectedProvider.get(2))
        );
    }

    /** 入力データ設定 */
    static List<Map<String, Object>> depositResultMessageRequestTypeProvider() {

        // deposit_request
        String[] request_id = {"A-001","A-002","A-003"};
        String[] card_number = {"3540000100010001","3540000100010002","3540000100010003"};
        String[] customer_contract_number = {"0000000001","0000000002","0000000003"};
        String[] customer_billing_due_date = {"20240515","20240515","20240515"};
        String[] contract_settlement_date = {"20240610","20240610","20240610"};
        String[] deposit_date = {"20240611","20240611","20240611"};
        long[] deposit_amount = {31300,31300,31300};
        String[] excess_money_handling_category = {"9","1","9"};

        // deposit_result, err_code, err_context
        String[] deposit_result = {"0","0","1"};
        String[] err_code = {"","","E01"};
        String[] err_context = {"","","Test Error"};

        // deposit_category_code
        String deposit_category_code = "9";

        // deposit_allocation_amount
        long alloc_total_principal = 30000;
        long alloc_total_interest = 300;
        long alloc_sp1_principal = 10000;
        long alloc_sp1_interest = 100;
        long alloc_sprv_principal = 20000;
        long alloc_sprv_interest = 200;

        // excess_money, jeccs_deposit
        long excess_money = 1000;
        long jeccs_deposit = 1000;

        // estimated_billing_amount
        long total_billing_principal = 50000;
        long total_billing_interest = 500;
        long total_deposit_principal = 3000;
        long total_deposit_interest = 300;
        long sp1_billing_principal = 20000;
        long sp1_billing_interest = 200;
        long sp1_deposit_principal = 1000;
        long sp1_deposit_interest = 100;
        long sprv_billing_principal = 30000;
        long sprv_billing_interest = 300;
        long sprv_deposit_principal = 2000;
        long sprv_deposit_interest = 200;

        // balance_amount
        long balance_total_principal = 100000;
        long balance_total_interest = 1000;
        long balance_sp1_principal = 40000;
        long balance_sp1_interest = 400;
        long balance_sprv_principal = 60000;
        long balance_sprv_interest = 600;

        List<Map<String, Object>> parametermaplist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> parametermap = new HashMap<String, Object>();
            // ----------------------------------------------------------------
            parametermap.put("request_id", request_id[i]);
            parametermap.put("card_number", card_number[i]);
            parametermap.put("customer_contract_number", customer_contract_number[i]);
            parametermap.put("customer_billing_due_date", customer_billing_due_date[i]);
            parametermap.put("contract_settlement_date", contract_settlement_date[i]);
            parametermap.put("deposit_date", deposit_date[i]);
            parametermap.put("deposit_amount", deposit_amount[i]);
            parametermap.put("excess_money_handling_category", excess_money_handling_category[i]);
            parametermap.put("deposit_result", deposit_result[i]);
            parametermap.put("err_code", err_code[i]);
            parametermap.put("err_context", err_context[i]);
            // ----------------------------------------------------------------
            parametermap.put("deposit_category_code", deposit_category_code);
            parametermap.put("alloc_total_principal", alloc_total_principal);
            parametermap.put("alloc_total_interest", alloc_total_interest);
            parametermap.put("alloc_sp1_principal", alloc_sp1_principal);
            parametermap.put("alloc_sp1_interest", alloc_sp1_interest);
            parametermap.put("alloc_sprv_principal", alloc_sprv_principal);
            parametermap.put("alloc_sprv_interest", alloc_sprv_interest);
            parametermap.put("excess_money", excess_money);
            parametermap.put("jeccs_deposit", jeccs_deposit);
            parametermap.put("total_billing_principal", total_billing_principal);
            parametermap.put("total_billing_interest", total_billing_interest);
            parametermap.put("total_deposit_principal", total_deposit_principal);
            parametermap.put("total_deposit_interest", total_deposit_interest);
            parametermap.put("sp1_billing_principal", sp1_billing_principal);
            parametermap.put("sp1_billing_interest", sp1_billing_interest);
            parametermap.put("sp1_deposit_principal", sp1_deposit_principal);
            parametermap.put("sp1_deposit_interest", sp1_deposit_interest);
            parametermap.put("sprv_billing_principal", sprv_billing_principal);
            parametermap.put("sprv_billing_interest", sprv_billing_interest);
            parametermap.put("sprv_deposit_principal", sprv_deposit_principal);
            parametermap.put("sprv_deposit_interest", sprv_deposit_interest);
            parametermap.put("balance_total_principal", balance_total_principal);
            parametermap.put("balance_total_interest", balance_total_interest);
            parametermap.put("balance_sp1_principal", balance_sp1_principal);
            parametermap.put("balance_sp1_interest", balance_sp1_interest);
            parametermap.put("balance_sprv_principal", balance_sprv_principal);
            parametermap.put("balance_sprv_interest", balance_sprv_interest);
            parametermaplist.add(parametermap);
        }
        return parametermaplist;

    }

    /** DepositResultMessageRequestTypeの設定 */
    static DepositResultMessageRequestType getInputType(Map<String, Object> parametermap) {
        DepositResultMessageRequestType input = new DepositResultMessageRequestType();
        // ----------------------------------------------------------------
        KijitsuNyukinRequestEntity deposit_request = new KijitsuNyukinRequestEntity();        
        deposit_request.setRequest_id(parametermap.get("request_id").toString());
        deposit_request.setCard_number(parametermap.get("card_number").toString());
        deposit_request.setCustomer_contract_number(parametermap.get("customer_contract_number").toString());
        deposit_request.setCustomer_billing_due_date(parametermap.get("customer_billing_due_date").toString());
        deposit_request.setContract_settlement_date(parametermap.get("contract_settlement_date").toString());
        deposit_request.setDeposit_date(parametermap.get("deposit_date").toString());
        deposit_request.setDeposit_amount(new BigDecimal(parametermap.get("deposit_amount").toString()));
        deposit_request.setExcess_money_handling_category(parametermap.get("excess_money_handling_category").toString());
        input.setDeposit_request(deposit_request);
        // ----------------------------------------------------------------
        input.setDeposit_result(parametermap.get("deposit_result").toString());
        input.setErr_code(parametermap.get("err_code").toString());
        input.setErr_context(parametermap.get("err_context").toString());
        input.setDeposit_category_code(parametermap.get("deposit_category_code").toString());
        // ----------------------------------------------------------------
        DepositDataEntity deposit_data = new DepositDataEntity();
        SaikenCompositeUnitEntity deposit_allocation_amount = new SaikenCompositeUnitEntity();
        // ----------------------------------------------------------------
        SaikenSimpleUnitEntity total_amout_allc = new SaikenSimpleUnitEntity();
        total_amout_allc.setPrincipal_amount(new BigDecimal(parametermap.get("alloc_total_principal").toString()));
        total_amout_allc.setInterest_amount(new BigDecimal(parametermap.get("alloc_total_interest").toString()));
        deposit_allocation_amount.setTotal_amout(total_amout_allc);
        // ----------------------------------------------------------------
        Map<String, SaikenSimpleUnitEntity> products_amount_map_allc = new HashMap<String, SaikenSimpleUnitEntity>();
        SaikenSimpleUnitEntity sp1_allc = new SaikenSimpleUnitEntity();
        sp1_allc.setPrincipal_amount(new BigDecimal(parametermap.get("alloc_sp1_principal").toString()));
        sp1_allc.setInterest_amount(new BigDecimal(parametermap.get("alloc_sp1_interest").toString()));
        SaikenSimpleUnitEntity sprv_allc = new SaikenSimpleUnitEntity();
        sprv_allc.setPrincipal_amount(new BigDecimal(parametermap.get("alloc_sprv_principal").toString()));
        sprv_allc.setInterest_amount(new BigDecimal(parametermap.get("alloc_sprv_interest").toString()));
        products_amount_map_allc.put("sp1", sp1_allc);
        products_amount_map_allc.put("sprv", sprv_allc);
        deposit_allocation_amount.setProducts_amount_map(products_amount_map_allc);
        deposit_data.setDeposit_allocation_amount(deposit_allocation_amount);
        // ----------------------------------------------------------------
        deposit_data.setExcess_money(new BigDecimal(parametermap.get("excess_money").toString()));
        // ----------------------------------------------------------------
        SeikyuCompositeUnitEntity estimated_billing_amount =new SeikyuCompositeUnitEntity();
        SeikyuSimpleUnitEntity total_billing = new SeikyuSimpleUnitEntity();
        total_billing.setBilling_principal_amount(new BigDecimal(parametermap.get("total_billing_principal").toString()));
        total_billing.setBilling_interest_amount(new BigDecimal(parametermap.get("total_billing_interest").toString()));
        total_billing.setDeposit_principal_amount(new BigDecimal(parametermap.get("total_deposit_principal").toString()));
        total_billing.setDeposit_interest_amount(new BigDecimal(parametermap.get("total_deposit_interest").toString()));
        estimated_billing_amount.setTotal_billing(total_billing);
        // ----------------------------------------------------------------
        Map<String, SeikyuSimpleUnitEntity> products_billing_map = new HashMap<String, SeikyuSimpleUnitEntity>();
        SeikyuSimpleUnitEntity sp1_billing = new SeikyuSimpleUnitEntity();
        sp1_billing.setBilling_principal_amount(new BigDecimal(parametermap.get("sp1_billing_principal").toString()));
        sp1_billing.setBilling_interest_amount(new BigDecimal(parametermap.get("sp1_billing_interest").toString()));
        sp1_billing.setDeposit_principal_amount(new BigDecimal(parametermap.get("sp1_deposit_principal").toString()));
        sp1_billing.setDeposit_interest_amount(new BigDecimal(parametermap.get("sp1_deposit_interest").toString()));
        SeikyuSimpleUnitEntity sprv_billing = new SeikyuSimpleUnitEntity();
        sprv_billing.setBilling_principal_amount(new BigDecimal(parametermap.get("sprv_billing_principal").toString()));
        sprv_billing.setBilling_interest_amount(new BigDecimal(parametermap.get("sprv_billing_interest").toString()));
        sprv_billing.setDeposit_principal_amount(new BigDecimal(parametermap.get("sprv_deposit_principal").toString()));
        sprv_billing.setDeposit_interest_amount(new BigDecimal(parametermap.get("sprv_deposit_interest").toString()));
        products_billing_map.put("sp1", sp1_billing);
        products_billing_map.put("sprv", sprv_billing);
        estimated_billing_amount.setProducts_billing_map(products_billing_map);
        // ----------------------------------------------------------------
        deposit_data.setEstimated_billing_amount(estimated_billing_amount);
        // ----------------------------------------------------------------
        SaikenCompositeUnitEntity balance_amount = new SaikenCompositeUnitEntity();
        SaikenSimpleUnitEntity total_amout_balance = new SaikenSimpleUnitEntity();
        total_amout_balance.setPrincipal_amount(new BigDecimal(parametermap.get("balance_total_principal").toString()));
        total_amout_balance.setInterest_amount(new BigDecimal(parametermap.get("balance_total_interest").toString()));
        balance_amount.setTotal_amout(total_amout_balance);
        // ----------------------------------------------------------------
        Map<String, SaikenSimpleUnitEntity> products_amount_map_balance = new HashMap<String, SaikenSimpleUnitEntity>();
        SaikenSimpleUnitEntity sp1_balance = new SaikenSimpleUnitEntity();
        sp1_balance.setPrincipal_amount(new BigDecimal(parametermap.get("balance_sp1_principal").toString()));
        sp1_balance.setInterest_amount(new BigDecimal(parametermap.get("balance_sp1_interest").toString()));
        SaikenSimpleUnitEntity sprv_balance = new SaikenSimpleUnitEntity();
        sprv_balance.setPrincipal_amount(new BigDecimal(parametermap.get("balance_sprv_principal").toString()));
        sprv_balance.setInterest_amount(new BigDecimal(parametermap.get("balance_sprv_interest").toString()));
        products_amount_map_balance.put("sp1", sp1_balance);
        products_amount_map_balance.put("sprv", sprv_balance);
        balance_amount.setProducts_amount_map(products_amount_map_balance);
        // ----------------------------------------------------------------
        deposit_data.setBalance_amount(balance_amount);
        input.setDeposit_data(deposit_data);
        // ----------------------------------------------------------------
        return input;
    }

    // 期待値設定
    static Map<String, Object> getExpectedData(Map<String, Object> parametermap) {
        if (parametermap.get("request_id").toString().equals("A-001")) {
            parametermap.replace("jeccs_deposit", 0);
        } else if (parametermap.get("request_id").toString().equals("A-002")) {
            parametermap.replace("excess_money", 0);
        } else {
        }
        return parametermap;
    }
}
