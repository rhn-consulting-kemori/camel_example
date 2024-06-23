package com.redhat.example.service;

// Spring
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

// Config
import com.redhat.example.config.AppConfig;

// Business Object
import com.redhat.example.type.CardCheckRequestType;
import com.redhat.example.type.CardCheckResponseType;

@Service
public class CardCheckService {

    @Autowired
    private AppConfig appConfig;

    /** Response Type */
    private CardCheckResponseType response_body;

    /** Service Request */
    public CardCheckResponseType serviceRequest(CardCheckRequestType request) {

        RestTemplate restTemplate = new RestTemplate();
        String url = appConfig.getUrlCardCheckService() + request.getRequest_id() + "/" + request.getCardnumber();

        try {
            ResponseEntity<CardCheckResponseType> response = restTemplate.exchange(url, HttpMethod.GET, null, CardCheckResponseType.class);
            response_body = response.getBody();

        } catch (HttpClientErrorException e) {
            response_body.setService_request(request);
            response_body.setResult("1", "E400", "HttpClientError");

        } catch (HttpServerErrorException e) {
            response_body.setService_request(request);
            response_body.setResult("1", "E500", "HttpServerErrorException");

        }

        return response_body;
    }

}
