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
import com.redhat.example.type.CheckAvailableDepositAmountRequestType;
import com.redhat.example.type.CheckAvailableDepositAmountResponseType;

@Service
public class CheckAvailableDepositAmountService {
    @Autowired
    private AppConfig appConfig;

    /** Response Type */
    private CheckAvailableDepositAmountResponseType response_body;

    /** Service Request */
    public CheckAvailableDepositAmountResponseType serviceRequest(CheckAvailableDepositAmountRequestType request) {

        RestTemplate restTemplate = new RestTemplate();

        /** URL */
        String url = appConfig.getUrlCheckAvailableDepositAmount();

        /** Header */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        /** HttpEntity */
        HttpEntity<CheckAvailableDepositAmountRequestType> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<CheckAvailableDepositAmountResponseType> response = restTemplate.exchange (url, HttpMethod.POST, entity ,CheckAvailableDepositAmountResponseType.class);
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
