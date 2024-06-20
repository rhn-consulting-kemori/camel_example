package com.redhat.example.entity;

import lombok.Data;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Data
@Component
public class KijitsuNyukinResponseEntity {

    /** 期日入金要求 */
    private KijitsuNyukinRequestEntity deposit_request;

    /** 入金結果 */
    private String deposit_result;

    /** 入金種類区分 */
    private String deposit_category_code;

    /** 入金充当額 */
    private BigDecimal deposit_allocation_amount;

    /** 過剰金 */
    private BigDecimal excess_money;

    /** JECCS預り金 */
    private BigDecimal jeccs_deposit;

}
