package com.redhat.example.entity;

import lombok.Data;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Data
@Component
public class KijitsuNyukinExchangeEntity {

    /** 期日入金要求 */
    private KijitsuNyukinRequestEntity deposit_request;

    // -----------------------------------------------------
    /** 入金結果 */
    private String deposit_result;

    /** エラーコード */
    private String err_code;

    /** エラー理由 */
    private String err_context;

    // -----------------------------------------------------   
    /** 入金種類区分 */
    private String deposit_category_code;

    /** 入金可能額照会 */
    private AvailableDepositAmountDataEntity deposit_available_amount_data;
    
    /** 入金充当試算 */
    private DepositAllocationDataEntity deposit_allocation_data;

    /** 入金結果 */
    private DepositDataEntity deposit_data;

    /**
     * コンストラクタ
     */
    public KijitsuNyukinExchangeEntity() {
        this.deposit_result = "0";
        this.err_code = "";
        this.err_context = "";
    }

    /** 
     * エラー情報設定 
     */
    public void setErrorInf(String deposit_result, String err_code, String err_context) {
        this.deposit_result = deposit_result;
        this.err_code = err_code;
        this.err_context = err_context;
    }
    
}
