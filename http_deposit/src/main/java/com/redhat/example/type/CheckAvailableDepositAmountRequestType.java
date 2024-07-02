package com.redhat.example.type;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CheckAvailableDepositAmountRequestType {
    
    /** REQUEST ID */
    private String request_id;

    /** 個人契約番号 */
    private String customer_contract_number;

    /** 入金日 */
    private String deposit_date;

    /** 顧客請求締年月日 */
    private String customer_billing_due_date;

    /** 約定決済日 */
    private String contract_settlement_date;

    /** 入金種類区分 */
    private String deposit_category_code;

}
