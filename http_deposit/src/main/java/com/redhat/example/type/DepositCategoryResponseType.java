package com.redhat.example.type;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DepositCategoryResponseType {

    /** Service Request Type */
    private DepositCategoryRequestType service_request;

    /** 結果 */
    private String response_result;
    
    /** エラーコード */
    private String err_code;
        
    /** エラー内容 */
    private String err_context;
    
    /** 入金種類区分 */
    private String deposit_category_code;

}