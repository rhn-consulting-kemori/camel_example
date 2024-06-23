package com.redhat.example.service;

// Spring
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

// Config
import com.redhat.example.config.AppConfig;

// Business Object
import com.redhat.example.type.DepositCategoryRequestType;
import com.redhat.example.type.DepositCategoryResponseType;

@Service
public class DepositCategoryService {

    @Autowired
    private AppConfig appConfig;

    /** Response Type */
    private DepositCategoryResponseType response_body;

    /** Service Request */
    public DepositCategoryResponseType serviceRequest(DepositCategoryRequestType request) {

        RestTemplate restTemplate = new RestTemplate();

        /** URL */
        String url = appConfig.getUrlDepositCategoryService();

        /** Header */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        /** HttpEntity */
        HttpEntity<DepositCategoryRequestType> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<DepositCategoryResponseType> response = restTemplate.exchange (url, HttpMethod.POST, entity ,DepositCategoryResponseType.class);
            response_body = response.getBody();

        } catch (HttpClientErrorException e) {
            response_body.setService_request(request);
            response_body.setResult("1","E400", "HttpClientError");

        } catch (HttpServerErrorException e) {
            response_body.setService_request(request);
            response_body.setResult("1","E500", "HttpServerError");

        }

        return response_body;

    }
        
}
