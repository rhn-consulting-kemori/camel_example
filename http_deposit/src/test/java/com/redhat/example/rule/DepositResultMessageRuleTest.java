package com.redhat.example.rule;

// Util
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

// Junit
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.redhat.example.entity.DepositDataEntity;
import com.redhat.example.entity.KijitsuNyukinRequestEntity;
// Business Object
import com.redhat.example.entity.KijitsuNyukinResponseEntity;
import com.redhat.example.entity.SaikenCompositeUnitEntity;
import com.redhat.example.entity.SaikenSimpleUnitEntity;
import com.redhat.example.entity.SeikyuCompositeUnitEntity;
import com.redhat.example.entity.SeikyuSimpleUnitEntity;
import com.redhat.example.type.DepositResultMessageRequestType;

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
    @CsvSource({ "A-001,3540000100010001,0000000001,20240515,20240610,20240611,31300,9,0,'','',9,30000,300,10000,100,20000,200,1000,1000,50000,500,3000,300,20000,200,1000,100,30000,300,2000,200,100000,1000,40000,400,60000,600" })
    void test_normal(
        String request_id, String card_number, String customer_contract_number, String customer_billing_due_date, String contract_settlement_date, String deposit_date, long deposit_amount, String excess_money_handling_category,
        String deposit_result, String err_code, String err_context, String deposit_category_code,
        long alloc_total_principal, long alloc_total_interest, long alloc_sp1_principal, long alloc_sp1_interest, long alloc_sprv_principal, long alloc_sprv_interest,
        long excess_money, long jeccs_deposit,
        long total_billing_principal, long total_billing_interest, long total_deposit_principal, long total_deposit_interest,
        long sp1_billing_principal, long sp1_billing_interest, long sp1_deposit_principal, long sp1_deposit_interest,
        long sprv_billing_principal, long sprv_billing_interest, long sprv_deposit_principal, long sprv_deposit_interest,
        long balance_total_principal, long balance_total_interest, long balance_sp1_principal, long balance_sp1_interest, long balance_sprv_principal, long balance_sprv_interest) {
    
        DepositResultMessageRequestType input = new DepositResultMessageRequestType();

        KijitsuNyukinRequestEntity deposit_request = new KijitsuNyukinRequestEntity();
        deposit_request.setRequest_id(request_id);
        deposit_request.setCard_number(card_number);
        deposit_request.setCustomer_contract_number(customer_contract_number);
        deposit_request.setCustomer_billing_due_date(customer_billing_due_date);
        deposit_request.setContract_settlement_date(contract_settlement_date);
        deposit_request.setDeposit_date(deposit_date);
        deposit_request.setDeposit_amount(BigDecimal.valueOf(deposit_amount));
        deposit_request.setExcess_money_handling_category(excess_money_handling_category);
        input.setDeposit_request(deposit_request);

        input.setDeposit_result(deposit_result);
        input.setErr_code(err_code);
        input.setErr_context(err_context);
        input.setDeposit_category_code(deposit_category_code);
        
        DepositDataEntity deposit_data = new DepositDataEntity();

        SaikenCompositeUnitEntity deposit_allocation_amount = new SaikenCompositeUnitEntity();

        SaikenSimpleUnitEntity total_amout_allc = new SaikenSimpleUnitEntity();
        total_amout_allc.setPrincipal_amount(BigDecimal.valueOf(alloc_total_principal));
        total_amout_allc.setInterest_amount(BigDecimal.valueOf(alloc_total_interest));
        deposit_allocation_amount.setTotal_amout(total_amout_allc);

        Map<String, SaikenSimpleUnitEntity> products_amount_map_allc = new HashMap<String, SaikenSimpleUnitEntity>();
        SaikenSimpleUnitEntity sp1_allc = new SaikenSimpleUnitEntity();
        sp1_allc.setPrincipal_amount(BigDecimal.valueOf(alloc_sp1_principal));
        sp1_allc.setInterest_amount(BigDecimal.valueOf(alloc_sp1_interest));
        SaikenSimpleUnitEntity sprv_allc = new SaikenSimpleUnitEntity();
        sprv_allc.setPrincipal_amount(BigDecimal.valueOf(alloc_sprv_principal));
        sprv_allc.setInterest_amount(BigDecimal.valueOf(alloc_sprv_interest));
        products_amount_map_allc.put("sp1", sp1_allc);
        products_amount_map_allc.put("sprv", sprv_allc);
        deposit_allocation_amount.setProducts_amount_map(products_amount_map_allc);
        deposit_data.setDeposit_allocation_amount(deposit_allocation_amount);

        deposit_data.setExcess_money(BigDecimal.valueOf(excess_money));

        SeikyuCompositeUnitEntity estimated_billing_amount =new SeikyuCompositeUnitEntity();
        SeikyuSimpleUnitEntity total_billing = new SeikyuSimpleUnitEntity();
        total_billing.setBilling_principal_amount(BigDecimal.valueOf(total_billing_principal));
        total_billing.setBilling_interest_amount(BigDecimal.valueOf(total_billing_interest));
        total_billing.setDeposit_principal_amount(BigDecimal.valueOf(total_deposit_principal));
        total_billing.setDeposit_interest_amount(BigDecimal.valueOf(total_deposit_interest));
        estimated_billing_amount.setTotal_billing(total_billing);

        Map<String, SeikyuSimpleUnitEntity> products_billing_map = new HashMap<String, SeikyuSimpleUnitEntity>();
        SeikyuSimpleUnitEntity sp1_billing = new SeikyuSimpleUnitEntity();
        sp1_billing.setBilling_principal_amount(BigDecimal.valueOf(sp1_billing_principal));
        sp1_billing.setBilling_interest_amount(BigDecimal.valueOf(sp1_billing_interest));
        sp1_billing.setDeposit_principal_amount(BigDecimal.valueOf(sp1_deposit_principal));
        sp1_billing.setDeposit_interest_amount(BigDecimal.valueOf(sp1_deposit_interest));
        SeikyuSimpleUnitEntity sprv_billing = new SeikyuSimpleUnitEntity();
        sprv_billing.setBilling_principal_amount(BigDecimal.valueOf(sprv_billing_principal));
        sprv_billing.setBilling_interest_amount(BigDecimal.valueOf(sprv_billing_interest));
        sprv_billing.setDeposit_principal_amount(BigDecimal.valueOf(sprv_deposit_principal));
        sprv_billing.setDeposit_interest_amount(BigDecimal.valueOf(sprv_deposit_interest));
        products_billing_map.put("sp1", sp1_billing);
        products_billing_map.put("sprv", sprv_billing);
        estimated_billing_amount.setProducts_billing_map(products_billing_map);

        deposit_data.setEstimated_billing_amount(estimated_billing_amount);

        SaikenCompositeUnitEntity balance_amount = new SaikenCompositeUnitEntity();
        SaikenSimpleUnitEntity total_amout_balance = new SaikenSimpleUnitEntity();
        total_amout_balance.setPrincipal_amount(BigDecimal.valueOf(balance_total_principal));
        total_amout_balance.setInterest_amount(BigDecimal.valueOf(balance_total_interest));
        balance_amount.setTotal_amout(total_amout_balance);

        Map<String, SaikenSimpleUnitEntity> products_amount_map_balance = new HashMap<String, SaikenSimpleUnitEntity>();
        SaikenSimpleUnitEntity sp1_balance = new SaikenSimpleUnitEntity();
        sp1_balance.setPrincipal_amount(BigDecimal.valueOf(balance_sp1_principal));
        sp1_balance.setInterest_amount(BigDecimal.valueOf(balance_sp1_interest));
        SaikenSimpleUnitEntity sprv_balance = new SaikenSimpleUnitEntity();
        sprv_balance.setPrincipal_amount(BigDecimal.valueOf(balance_sprv_principal));
        sprv_balance.setInterest_amount(BigDecimal.valueOf(balance_sprv_interest));
        products_amount_map_balance.put("sp1", sp1_balance);
        products_amount_map_balance.put("sprv", sprv_balance);
        balance_amount.setProducts_amount_map(products_amount_map_balance);

        deposit_data.setBalance_amount(balance_amount);
        input.setDeposit_data(deposit_data);

        KijitsuNyukinResponseEntity entity = rule.executeRule(input);
        assertEquals(request_id, entity.getDeposit_request().getRequest_id(), "request_id");
        assertEquals(card_number, entity.getDeposit_request().getCard_number(), "card_number");
        assertEquals(customer_contract_number, entity.getDeposit_request().getCustomer_contract_number(), "customer_contract_number");
        assertEquals(customer_billing_due_date, entity.getDeposit_request().getCustomer_billing_due_date(), "customer_billing_due_date");
        assertEquals(contract_settlement_date, entity.getDeposit_request().getContract_settlement_date(), "contract_settlement_date");
        assertEquals(deposit_date, entity.getDeposit_request().getDeposit_date(), "deposit_date");
        assertEquals(BigDecimal.valueOf(deposit_amount), entity.getDeposit_request().getDeposit_amount(), "deposit_amount");
        assertEquals(excess_money_handling_category, entity.getDeposit_request().getExcess_money_handling_category(), "excess_money_handling_category");
        assertEquals(deposit_result, entity.getDeposit_result(), "deposit_result");
        assertEquals(err_code, entity.getErr_code(), "err_code");
        assertEquals(err_context, entity.getErr_context(), "err_context");
        assertEquals(deposit_category_code, entity.getDeposit_category_code(), "deposit_category_code");

        // deposit_allocation_amount
        assertEquals(BigDecimal.valueOf(alloc_total_principal), entity.getDeposit_allocation_amount().getTotal_amout().getPrincipal_amount(), "alloc_total_principal");
        assertEquals(BigDecimal.valueOf(alloc_total_interest), entity.getDeposit_allocation_amount().getTotal_amout().getInterest_amount(), "alloc_total_interest");
        assertEquals(BigDecimal.valueOf(alloc_sp1_principal), entity.getDeposit_allocation_amount().getProducts_amount_map().get("sp1").getPrincipal_amount(), "alloc_sp1_principal");
        assertEquals(BigDecimal.valueOf(alloc_sp1_interest), entity.getDeposit_allocation_amount().getProducts_amount_map().get("sp1").getInterest_amount(), "alloc_sp1_interest");
        assertEquals(BigDecimal.valueOf(alloc_sprv_principal), entity.getDeposit_allocation_amount().getProducts_amount_map().get("sprv").getPrincipal_amount(), "alloc_sprv_principal");
        assertEquals(BigDecimal.valueOf(alloc_sprv_interest), entity.getDeposit_allocation_amount().getProducts_amount_map().get("sprv").getInterest_amount(), "alloc_sprv_interest");

        // excess_money, jeccs_deposit
        assertEquals(BigDecimal.valueOf(excess_money), entity.getExcess_money(), "excess_money");
        assertEquals(BigDecimal.valueOf(0), entity.getJeccs_deposit(), "jeccs_deposit");

        // estimated_billing_amount
        assertEquals(BigDecimal.valueOf(total_billing_principal), entity.getEstimated_billing_amount().getTotal_billing().getBilling_principal_amount(), "total_billing_principal");
        assertEquals(BigDecimal.valueOf(total_billing_interest), entity.getEstimated_billing_amount().getTotal_billing().getBilling_interest_amount(), "total_billing_interest");
        assertEquals(BigDecimal.valueOf(total_deposit_principal), entity.getEstimated_billing_amount().getTotal_billing().getDeposit_principal_amount(), "total_deposit_principal");
        assertEquals(BigDecimal.valueOf(total_deposit_interest), entity.getEstimated_billing_amount().getTotal_billing().getDeposit_interest_amount(), "total_deposit_interest");
        assertEquals(BigDecimal.valueOf(sp1_billing_principal), entity.getEstimated_billing_amount().getProducts_billing_map().get("sp1").getBilling_principal_amount(), "sp1_billing_principal");
        assertEquals(BigDecimal.valueOf(sp1_billing_interest), entity.getEstimated_billing_amount().getProducts_billing_map().get("sp1").getBilling_interest_amount(), "sp1_billing_interest");
        assertEquals(BigDecimal.valueOf(sp1_deposit_principal), entity.getEstimated_billing_amount().getProducts_billing_map().get("sp1").getDeposit_principal_amount(), "sp1_deposit_principal");
        assertEquals(BigDecimal.valueOf(sp1_deposit_interest), entity.getEstimated_billing_amount().getProducts_billing_map().get("sp1").getDeposit_interest_amount(), "sp1_deposit_interest");
        assertEquals(BigDecimal.valueOf(sprv_billing_principal), entity.getEstimated_billing_amount().getProducts_billing_map().get("sprv").getBilling_principal_amount(), "sprv_billing_principal");
        assertEquals(BigDecimal.valueOf(sprv_billing_interest), entity.getEstimated_billing_amount().getProducts_billing_map().get("sprv").getBilling_interest_amount(), "sprv_billing_interest");
        assertEquals(BigDecimal.valueOf(sprv_deposit_principal), entity.getEstimated_billing_amount().getProducts_billing_map().get("sprv").getDeposit_principal_amount(), "sprv_deposit_principal");
        assertEquals(BigDecimal.valueOf(sprv_deposit_interest), entity.getEstimated_billing_amount().getProducts_billing_map().get("sprv").getDeposit_interest_amount(), "sprv_deposit_interest");

        // balance_amount
        assertEquals(BigDecimal.valueOf(balance_total_principal), entity.getBalance_amount().getTotal_amout().getPrincipal_amount(), "balance_total_principal");
        assertEquals(BigDecimal.valueOf(balance_total_interest), entity.getBalance_amount().getTotal_amout().getInterest_amount(), "balance_total_interest");
        assertEquals(BigDecimal.valueOf(balance_sp1_principal), entity.getBalance_amount().getProducts_amount_map().get("sp1").getPrincipal_amount(), "balance_sp1_principal");
        assertEquals(BigDecimal.valueOf(balance_sp1_interest), entity.getBalance_amount().getProducts_amount_map().get("sp1").getInterest_amount(), "balance_sp1_interest");
        assertEquals(BigDecimal.valueOf(balance_sprv_principal), entity.getBalance_amount().getProducts_amount_map().get("sprv").getPrincipal_amount(), "balance_sprv_principal");
        assertEquals(BigDecimal.valueOf(balance_sprv_interest), entity.getBalance_amount().getProducts_amount_map().get("sprv").getInterest_amount(), "balance_sprv_interest");

    }

    @ParameterizedTest
    @DisplayName("JECCS預かり金ありケース")
    @CsvSource({ "A-002,3540000100010002,0000000002,20240515,20240610,20240611,31300,1,0,'','',9,30000,300,10000,100,20000,200,1000,1000,50000,500,3000,300,20000,200,1000,100,30000,300,2000,200,100000,1000,40000,400,60000,600" })
    void test_jeccs_deposit(
        String request_id, String card_number, String customer_contract_number, String customer_billing_due_date, String contract_settlement_date, String deposit_date, long deposit_amount, String excess_money_handling_category,
        String deposit_result, String err_code, String err_context, String deposit_category_code,
        long alloc_total_principal, long alloc_total_interest, long alloc_sp1_principal, long alloc_sp1_interest, long alloc_sprv_principal, long alloc_sprv_interest,
        long excess_money, long jeccs_deposit,
        long total_billing_principal, long total_billing_interest, long total_deposit_principal, long total_deposit_interest,
        long sp1_billing_principal, long sp1_billing_interest, long sp1_deposit_principal, long sp1_deposit_interest,
        long sprv_billing_principal, long sprv_billing_interest, long sprv_deposit_principal, long sprv_deposit_interest,
        long balance_total_principal, long balance_total_interest, long balance_sp1_principal, long balance_sp1_interest, long balance_sprv_principal, long balance_sprv_interest
    ) {
        DepositResultMessageRequestType input = new DepositResultMessageRequestType();

        KijitsuNyukinRequestEntity deposit_request = new KijitsuNyukinRequestEntity();
        deposit_request.setRequest_id(request_id);
        deposit_request.setCard_number(card_number);
        deposit_request.setCustomer_contract_number(customer_contract_number);
        deposit_request.setCustomer_billing_due_date(customer_billing_due_date);
        deposit_request.setContract_settlement_date(contract_settlement_date);
        deposit_request.setDeposit_date(deposit_date);
        deposit_request.setDeposit_amount(BigDecimal.valueOf(deposit_amount));
        deposit_request.setExcess_money_handling_category(excess_money_handling_category);
        input.setDeposit_request(deposit_request);

        input.setDeposit_result(deposit_result);
        input.setErr_code(err_code);
        input.setErr_context(err_context);
        input.setDeposit_category_code(deposit_category_code);
        
        DepositDataEntity deposit_data = new DepositDataEntity();

        SaikenCompositeUnitEntity deposit_allocation_amount = new SaikenCompositeUnitEntity();

        SaikenSimpleUnitEntity total_amout_allc = new SaikenSimpleUnitEntity();
        total_amout_allc.setPrincipal_amount(BigDecimal.valueOf(alloc_total_principal));
        total_amout_allc.setInterest_amount(BigDecimal.valueOf(alloc_total_interest));
        deposit_allocation_amount.setTotal_amout(total_amout_allc);

        Map<String, SaikenSimpleUnitEntity> products_amount_map_allc = new HashMap<String, SaikenSimpleUnitEntity>();
        SaikenSimpleUnitEntity sp1_allc = new SaikenSimpleUnitEntity();
        sp1_allc.setPrincipal_amount(BigDecimal.valueOf(alloc_sp1_principal));
        sp1_allc.setInterest_amount(BigDecimal.valueOf(alloc_sp1_interest));
        SaikenSimpleUnitEntity sprv_allc = new SaikenSimpleUnitEntity();
        sprv_allc.setPrincipal_amount(BigDecimal.valueOf(alloc_sprv_principal));
        sprv_allc.setInterest_amount(BigDecimal.valueOf(alloc_sprv_interest));
        products_amount_map_allc.put("sp1", sp1_allc);
        products_amount_map_allc.put("sprv", sprv_allc);
        deposit_allocation_amount.setProducts_amount_map(products_amount_map_allc);
        deposit_data.setDeposit_allocation_amount(deposit_allocation_amount);

        deposit_data.setExcess_money(BigDecimal.valueOf(excess_money));

        SeikyuCompositeUnitEntity estimated_billing_amount =new SeikyuCompositeUnitEntity();
        SeikyuSimpleUnitEntity total_billing = new SeikyuSimpleUnitEntity();
        total_billing.setBilling_principal_amount(BigDecimal.valueOf(total_billing_principal));
        total_billing.setBilling_interest_amount(BigDecimal.valueOf(total_billing_interest));
        total_billing.setDeposit_principal_amount(BigDecimal.valueOf(total_deposit_principal));
        total_billing.setDeposit_interest_amount(BigDecimal.valueOf(total_deposit_interest));
        estimated_billing_amount.setTotal_billing(total_billing);

        Map<String, SeikyuSimpleUnitEntity> products_billing_map = new HashMap<String, SeikyuSimpleUnitEntity>();
        SeikyuSimpleUnitEntity sp1_billing = new SeikyuSimpleUnitEntity();
        sp1_billing.setBilling_principal_amount(BigDecimal.valueOf(sp1_billing_principal));
        sp1_billing.setBilling_interest_amount(BigDecimal.valueOf(sp1_billing_interest));
        sp1_billing.setDeposit_principal_amount(BigDecimal.valueOf(sp1_deposit_principal));
        sp1_billing.setDeposit_interest_amount(BigDecimal.valueOf(sp1_deposit_interest));
        SeikyuSimpleUnitEntity sprv_billing = new SeikyuSimpleUnitEntity();
        sprv_billing.setBilling_principal_amount(BigDecimal.valueOf(sprv_billing_principal));
        sprv_billing.setBilling_interest_amount(BigDecimal.valueOf(sprv_billing_interest));
        sprv_billing.setDeposit_principal_amount(BigDecimal.valueOf(sprv_deposit_principal));
        sprv_billing.setDeposit_interest_amount(BigDecimal.valueOf(sprv_deposit_interest));
        products_billing_map.put("sp1", sp1_billing);
        products_billing_map.put("sprv", sprv_billing);
        estimated_billing_amount.setProducts_billing_map(products_billing_map);

        deposit_data.setEstimated_billing_amount(estimated_billing_amount);

        SaikenCompositeUnitEntity balance_amount = new SaikenCompositeUnitEntity();
        SaikenSimpleUnitEntity total_amout_balance = new SaikenSimpleUnitEntity();
        total_amout_balance.setPrincipal_amount(BigDecimal.valueOf(balance_total_principal));
        total_amout_balance.setInterest_amount(BigDecimal.valueOf(balance_total_interest));
        balance_amount.setTotal_amout(total_amout_balance);

        Map<String, SaikenSimpleUnitEntity> products_amount_map_balance = new HashMap<String, SaikenSimpleUnitEntity>();
        SaikenSimpleUnitEntity sp1_balance = new SaikenSimpleUnitEntity();
        sp1_balance.setPrincipal_amount(BigDecimal.valueOf(balance_sp1_principal));
        sp1_balance.setInterest_amount(BigDecimal.valueOf(balance_sp1_interest));
        SaikenSimpleUnitEntity sprv_balance = new SaikenSimpleUnitEntity();
        sprv_balance.setPrincipal_amount(BigDecimal.valueOf(balance_sprv_principal));
        sprv_balance.setInterest_amount(BigDecimal.valueOf(balance_sprv_interest));
        products_amount_map_balance.put("sp1", sp1_balance);
        products_amount_map_balance.put("sprv", sprv_balance);
        balance_amount.setProducts_amount_map(products_amount_map_balance);

        deposit_data.setBalance_amount(balance_amount);
        input.setDeposit_data(deposit_data);

        // Debug
        System.out.println(input);

        KijitsuNyukinResponseEntity entity = rule.executeRule(input);
        assertEquals(request_id, entity.getDeposit_request().getRequest_id(), "request_id");
        assertEquals(card_number, entity.getDeposit_request().getCard_number(), "card_number");
        assertEquals(customer_contract_number, entity.getDeposit_request().getCustomer_contract_number(), "customer_contract_number");
        assertEquals(customer_billing_due_date, entity.getDeposit_request().getCustomer_billing_due_date(), "customer_billing_due_date");
        assertEquals(contract_settlement_date, entity.getDeposit_request().getContract_settlement_date(), "contract_settlement_date");
        assertEquals(deposit_date, entity.getDeposit_request().getDeposit_date(), "deposit_date");
        assertEquals(BigDecimal.valueOf(deposit_amount), entity.getDeposit_request().getDeposit_amount(), "deposit_amount");
        assertEquals(excess_money_handling_category, entity.getDeposit_request().getExcess_money_handling_category(), "excess_money_handling_category");
        assertEquals(deposit_result, entity.getDeposit_result(), "deposit_result");
        assertEquals(err_code, entity.getErr_code(), "err_code");
        assertEquals(err_context, entity.getErr_context(), "err_context");
        assertEquals(deposit_category_code, entity.getDeposit_category_code(), "deposit_category_code");

        // deposit_allocation_amount
        assertEquals(BigDecimal.valueOf(alloc_total_principal), entity.getDeposit_allocation_amount().getTotal_amout().getPrincipal_amount(), "alloc_total_principal");
        assertEquals(BigDecimal.valueOf(alloc_total_interest), entity.getDeposit_allocation_amount().getTotal_amout().getInterest_amount(), "alloc_total_interest");
        assertEquals(BigDecimal.valueOf(alloc_sp1_principal), entity.getDeposit_allocation_amount().getProducts_amount_map().get("sp1").getPrincipal_amount(), "alloc_sp1_principal");
        assertEquals(BigDecimal.valueOf(alloc_sp1_interest), entity.getDeposit_allocation_amount().getProducts_amount_map().get("sp1").getInterest_amount(), "alloc_sp1_interest");
        assertEquals(BigDecimal.valueOf(alloc_sprv_principal), entity.getDeposit_allocation_amount().getProducts_amount_map().get("sprv").getPrincipal_amount(), "alloc_sprv_principal");
        assertEquals(BigDecimal.valueOf(alloc_sprv_interest), entity.getDeposit_allocation_amount().getProducts_amount_map().get("sprv").getInterest_amount(), "alloc_sprv_interest");

        // excess_money, jeccs_deposit
        assertEquals(BigDecimal.valueOf(0), entity.getExcess_money(), "excess_money");
        assertEquals(BigDecimal.valueOf(jeccs_deposit), entity.getJeccs_deposit(), "jeccs_deposit");

        // estimated_billing_amount
        assertEquals(BigDecimal.valueOf(total_billing_principal), entity.getEstimated_billing_amount().getTotal_billing().getBilling_principal_amount(), "total_billing_principal");
        assertEquals(BigDecimal.valueOf(total_billing_interest), entity.getEstimated_billing_amount().getTotal_billing().getBilling_interest_amount(), "total_billing_interest");
        assertEquals(BigDecimal.valueOf(total_deposit_principal), entity.getEstimated_billing_amount().getTotal_billing().getDeposit_principal_amount(), "total_deposit_principal");
        assertEquals(BigDecimal.valueOf(total_deposit_interest), entity.getEstimated_billing_amount().getTotal_billing().getDeposit_interest_amount(), "total_deposit_interest");
        assertEquals(BigDecimal.valueOf(sp1_billing_principal), entity.getEstimated_billing_amount().getProducts_billing_map().get("sp1").getBilling_principal_amount(), "sp1_billing_principal");
        assertEquals(BigDecimal.valueOf(sp1_billing_interest), entity.getEstimated_billing_amount().getProducts_billing_map().get("sp1").getBilling_interest_amount(), "sp1_billing_interest");
        assertEquals(BigDecimal.valueOf(sp1_deposit_principal), entity.getEstimated_billing_amount().getProducts_billing_map().get("sp1").getDeposit_principal_amount(), "sp1_deposit_principal");
        assertEquals(BigDecimal.valueOf(sp1_deposit_interest), entity.getEstimated_billing_amount().getProducts_billing_map().get("sp1").getDeposit_interest_amount(), "sp1_deposit_interest");
        assertEquals(BigDecimal.valueOf(sprv_billing_principal), entity.getEstimated_billing_amount().getProducts_billing_map().get("sprv").getBilling_principal_amount(), "sprv_billing_principal");
        assertEquals(BigDecimal.valueOf(sprv_billing_interest), entity.getEstimated_billing_amount().getProducts_billing_map().get("sprv").getBilling_interest_amount(), "sprv_billing_interest");
        assertEquals(BigDecimal.valueOf(sprv_deposit_principal), entity.getEstimated_billing_amount().getProducts_billing_map().get("sprv").getDeposit_principal_amount(), "sprv_deposit_principal");
        assertEquals(BigDecimal.valueOf(sprv_deposit_interest), entity.getEstimated_billing_amount().getProducts_billing_map().get("sprv").getDeposit_interest_amount(), "sprv_deposit_interest");

        // balance_amount
        assertEquals(BigDecimal.valueOf(balance_total_principal), entity.getBalance_amount().getTotal_amout().getPrincipal_amount(), "balance_total_principal");
        assertEquals(BigDecimal.valueOf(balance_total_interest), entity.getBalance_amount().getTotal_amout().getInterest_amount(), "balance_total_interest");
        assertEquals(BigDecimal.valueOf(balance_sp1_principal), entity.getBalance_amount().getProducts_amount_map().get("sp1").getPrincipal_amount(), "balance_sp1_principal");
        assertEquals(BigDecimal.valueOf(balance_sp1_interest), entity.getBalance_amount().getProducts_amount_map().get("sp1").getInterest_amount(), "balance_sp1_interest");
        assertEquals(BigDecimal.valueOf(balance_sprv_principal), entity.getBalance_amount().getProducts_amount_map().get("sprv").getPrincipal_amount(), "balance_sprv_principal");
        assertEquals(BigDecimal.valueOf(balance_sprv_interest), entity.getBalance_amount().getProducts_amount_map().get("sprv").getInterest_amount(), "balance_sprv_interest");

    }

    @ParameterizedTest
    @DisplayName("エラーケース")
    @CsvSource({ "A-003,3540000100010003,0000000003,20240515,20240610,20240611,31300,1,1,E01,Test Error,9,30000,300,10000,100,20000,200,1000,1000,50000,500,3000,300,20000,200,1000,100,30000,300,2000,200,100000,1000,40000,400,60000,600" })
    void test_err(
        String request_id, String card_number, String customer_contract_number, String customer_billing_due_date, String contract_settlement_date, String deposit_date, long deposit_amount, String excess_money_handling_category,
        String deposit_result, String err_code, String err_context, String deposit_category_code,
        long alloc_total_principal, long alloc_total_interest, long alloc_sp1_principal, long alloc_sp1_interest, long alloc_sprv_principal, long alloc_sprv_interest,
        long excess_money, long jeccs_deposit,
        long total_billing_principal, long total_billing_interest, long total_deposit_principal, long total_deposit_interest,
        long sp1_billing_principal, long sp1_billing_interest, long sp1_deposit_principal, long sp1_deposit_interest,
        long sprv_billing_principal, long sprv_billing_interest, long sprv_deposit_principal, long sprv_deposit_interest,
        long balance_total_principal, long balance_total_interest, long balance_sp1_principal, long balance_sp1_interest, long balance_sprv_principal, long balance_sprv_interest
    ) {
        DepositResultMessageRequestType input = new DepositResultMessageRequestType();

        KijitsuNyukinRequestEntity deposit_request = new KijitsuNyukinRequestEntity();
        deposit_request.setRequest_id(request_id);
        deposit_request.setCard_number(card_number);
        deposit_request.setCustomer_contract_number(customer_contract_number);
        deposit_request.setCustomer_billing_due_date(customer_billing_due_date);
        deposit_request.setContract_settlement_date(contract_settlement_date);
        deposit_request.setDeposit_date(deposit_date);
        deposit_request.setDeposit_amount(BigDecimal.valueOf(deposit_amount));
        deposit_request.setExcess_money_handling_category(excess_money_handling_category);
        input.setDeposit_request(deposit_request);

        input.setDeposit_result(deposit_result);
        input.setErr_code(err_code);
        input.setErr_context(err_context);
        input.setDeposit_category_code(deposit_category_code);
        
        DepositDataEntity deposit_data = new DepositDataEntity();

        SaikenCompositeUnitEntity deposit_allocation_amount = new SaikenCompositeUnitEntity();

        SaikenSimpleUnitEntity total_amout_allc = new SaikenSimpleUnitEntity();
        total_amout_allc.setPrincipal_amount(BigDecimal.valueOf(alloc_total_principal));
        total_amout_allc.setInterest_amount(BigDecimal.valueOf(alloc_total_interest));
        deposit_allocation_amount.setTotal_amout(total_amout_allc);

        Map<String, SaikenSimpleUnitEntity> products_amount_map_allc = new HashMap<String, SaikenSimpleUnitEntity>();
        SaikenSimpleUnitEntity sp1_allc = new SaikenSimpleUnitEntity();
        sp1_allc.setPrincipal_amount(BigDecimal.valueOf(alloc_sp1_principal));
        sp1_allc.setInterest_amount(BigDecimal.valueOf(alloc_sp1_interest));
        SaikenSimpleUnitEntity sprv_allc = new SaikenSimpleUnitEntity();
        sprv_allc.setPrincipal_amount(BigDecimal.valueOf(alloc_sprv_principal));
        sprv_allc.setInterest_amount(BigDecimal.valueOf(alloc_sprv_interest));
        products_amount_map_allc.put("sp1", sp1_allc);
        products_amount_map_allc.put("sprv", sprv_allc);
        deposit_allocation_amount.setProducts_amount_map(products_amount_map_allc);
        deposit_data.setDeposit_allocation_amount(deposit_allocation_amount);

        deposit_data.setExcess_money(BigDecimal.valueOf(excess_money));

        SeikyuCompositeUnitEntity estimated_billing_amount =new SeikyuCompositeUnitEntity();
        SeikyuSimpleUnitEntity total_billing = new SeikyuSimpleUnitEntity();
        total_billing.setBilling_principal_amount(BigDecimal.valueOf(total_billing_principal));
        total_billing.setBilling_interest_amount(BigDecimal.valueOf(total_billing_interest));
        total_billing.setDeposit_principal_amount(BigDecimal.valueOf(total_deposit_principal));
        total_billing.setDeposit_interest_amount(BigDecimal.valueOf(total_deposit_interest));
        estimated_billing_amount.setTotal_billing(total_billing);

        Map<String, SeikyuSimpleUnitEntity> products_billing_map = new HashMap<String, SeikyuSimpleUnitEntity>();
        SeikyuSimpleUnitEntity sp1_billing = new SeikyuSimpleUnitEntity();
        sp1_billing.setBilling_principal_amount(BigDecimal.valueOf(sp1_billing_principal));
        sp1_billing.setBilling_interest_amount(BigDecimal.valueOf(sp1_billing_interest));
        sp1_billing.setDeposit_principal_amount(BigDecimal.valueOf(sp1_deposit_principal));
        sp1_billing.setDeposit_interest_amount(BigDecimal.valueOf(sp1_deposit_interest));
        SeikyuSimpleUnitEntity sprv_billing = new SeikyuSimpleUnitEntity();
        sprv_billing.setBilling_principal_amount(BigDecimal.valueOf(sprv_billing_principal));
        sprv_billing.setBilling_interest_amount(BigDecimal.valueOf(sprv_billing_interest));
        sprv_billing.setDeposit_principal_amount(BigDecimal.valueOf(sprv_deposit_principal));
        sprv_billing.setDeposit_interest_amount(BigDecimal.valueOf(sprv_deposit_interest));
        products_billing_map.put("sp1", sp1_billing);
        products_billing_map.put("sprv", sprv_billing);
        estimated_billing_amount.setProducts_billing_map(products_billing_map);

        deposit_data.setEstimated_billing_amount(estimated_billing_amount);

        SaikenCompositeUnitEntity balance_amount = new SaikenCompositeUnitEntity();
        SaikenSimpleUnitEntity total_amout_balance = new SaikenSimpleUnitEntity();
        total_amout_balance.setPrincipal_amount(BigDecimal.valueOf(balance_total_principal));
        total_amout_balance.setInterest_amount(BigDecimal.valueOf(balance_total_interest));
        balance_amount.setTotal_amout(total_amout_balance);

        Map<String, SaikenSimpleUnitEntity> products_amount_map_balance = new HashMap<String, SaikenSimpleUnitEntity>();
        SaikenSimpleUnitEntity sp1_balance = new SaikenSimpleUnitEntity();
        sp1_balance.setPrincipal_amount(BigDecimal.valueOf(balance_sp1_principal));
        sp1_balance.setInterest_amount(BigDecimal.valueOf(balance_sp1_interest));
        SaikenSimpleUnitEntity sprv_balance = new SaikenSimpleUnitEntity();
        sprv_balance.setPrincipal_amount(BigDecimal.valueOf(balance_sprv_principal));
        sprv_balance.setInterest_amount(BigDecimal.valueOf(balance_sprv_interest));
        products_amount_map_balance.put("sp1", sp1_balance);
        products_amount_map_balance.put("sprv", sprv_balance);
        balance_amount.setProducts_amount_map(products_amount_map_balance);

        deposit_data.setBalance_amount(balance_amount);
        input.setDeposit_data(deposit_data);

        KijitsuNyukinResponseEntity entity = rule.executeRule(input);
        assertEquals(request_id, entity.getDeposit_request().getRequest_id(), "request_id");
        assertEquals(card_number, entity.getDeposit_request().getCard_number(), "card_number");
        assertEquals(customer_contract_number, entity.getDeposit_request().getCustomer_contract_number(), "customer_contract_number");
        assertEquals(customer_billing_due_date, entity.getDeposit_request().getCustomer_billing_due_date(), "customer_billing_due_date");
        assertEquals(contract_settlement_date, entity.getDeposit_request().getContract_settlement_date(), "contract_settlement_date");
        assertEquals(deposit_date, entity.getDeposit_request().getDeposit_date(), "deposit_date");
        assertEquals(BigDecimal.valueOf(deposit_amount), entity.getDeposit_request().getDeposit_amount(), "deposit_amount");
        assertEquals(excess_money_handling_category, entity.getDeposit_request().getExcess_money_handling_category(), "excess_money_handling_category");
        assertEquals(deposit_result, entity.getDeposit_result(), "deposit_result");
        assertEquals(err_code, entity.getErr_code(), "err_code");
        assertEquals(err_context, entity.getErr_context(), "err_context");
        
        assertNull(entity.getDeposit_category_code());

        // deposit_allocation_amount
        assertNull(entity.getDeposit_allocation_amount());
        
        // excess_money, jeccs_deposit
        assertNull(entity.getExcess_money());
        assertNull(entity.getJeccs_deposit());

        // estimated_billing_amount
        assertNull(entity.getEstimated_billing_amount());
        
        // balance_amount
        assertNull(entity.getBalance_amount());
        
    }
}
