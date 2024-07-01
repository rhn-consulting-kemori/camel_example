package com.redhat.example.type;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DepositCategoryRequestType {

    /** REQUEST ID */
    private String request_id;

    /** 顧客契約番号 */
    private String customer_contract_number;

    /** 顧客請求締年月日 */
    private String customer_billing_due_date;

    /** 約定決済年月日 */
    private String contract_settlement_date;

    /** 入金年月日 */
    private String deposit_date;

}