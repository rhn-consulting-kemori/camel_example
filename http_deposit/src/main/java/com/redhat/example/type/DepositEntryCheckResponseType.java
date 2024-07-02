package com.redhat.example.type;

import lombok.Data;
import org.springframework.stereotype.Component;
@Data
@Component
public class DepositEntryCheckResponseType {

    /** Service Request Type */
    private DepositEntryCheckRequestType service_request;

    /** 結果 */
    private String response_result;
    
    /** エラーコード */
    private String err_code;
        
    /** エラー内容 */
    private String err_context;

}
