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
import com.redhat.example.type.DepositAllocationRequestType;
import com.redhat.example.type.DepositAllocationResponseType;

@Service
public class DepositAllocationService {
    
    @Autowired
    private AppConfig appConfig;

    /** Response Type */
    private DepositAllocationResponseType response_body;

    /** Service Request */
    public DepositAllocationResponseType serviceRequest(DepositAllocationRequestType request) {

        RestTemplate restTemplate = new RestTemplate();

        /** URL */
        String url = appConfig.getUrlDepositAllocation();

        /** Header */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        /** HttpEntity */
        HttpEntity<DepositAllocationRequestType> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<DepositAllocationResponseType> response = restTemplate.exchange (url, HttpMethod.POST, entity ,DepositAllocationResponseType.class);
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
