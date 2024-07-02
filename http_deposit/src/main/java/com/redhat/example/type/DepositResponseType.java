package com.redhat.example.type;

import lombok.Data;
import org.springframework.stereotype.Component;

import com.redhat.example.entity.DepositDataEntity;

@Data
@Component
public class DepositResponseType {

    /** Service Request Type */
    private DepositRequestType service_request;

    /** 結果 */
    private String response_result;
        
    /** エラーコード */
    private String err_code;
            
    /** エラー内容 */
    private String err_context;

    /** 入金結果情報 */
    private DepositDataEntity deposit_data;
    
}
