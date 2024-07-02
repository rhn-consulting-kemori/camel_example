package com.redhat.example.type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.redhat.example.entity.AvailableDepositAmountDataEntity;

@Data
@Component
public class DepositAllocationRequestType {

    /** REQUEST ID */
    private String request_id;

    /** 個人契約番号 */
    private String customer_contract_number;

    /** 入金日 */
    private String deposit_date;

    /** 顧客請求締年月日 */
    private String customer_billing_due_date;

    /** 決済日 */
    private String contract_settlement_date;

    /** 入金種類区分 */
    private String deposit_category_code;

    /** 入金額 */
    private BigDecimal deposit_amount;

    /** 過剰金調整区分 */
    private String excess_money_handling_category;

    /** 入金可能額情報 */
    private AvailableDepositAmountDataEntity deposit_available_amount_data;

}
