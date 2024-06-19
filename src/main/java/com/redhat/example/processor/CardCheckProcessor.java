package com.redhat.example.processor;

// Camel
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

// Spring
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

// Config
import com.redhat.example.config.AppConfig;

// Business Object
import com.redhat.example.entity.KijitsuNyukinRequestEntity;
import com.redhat.example.entity.KijitsuNyukinExchangeEntity;
import com.redhat.example.type.CardCheckResponseType;

@Component
public class CardCheckProcessor implements Processor {

    @Autowired
    KijitsuNyukinExchangeEntity exchange_message;

    @Autowired
    private AppConfig appConfig;

    @Override
    public void process(Exchange exchange) throws Exception {

        /**
         * Exchange IN
         */
        var body = exchange.getMessage().getBody(KijitsuNyukinRequestEntity.class);
        exchange_message.setDeposit_request(body);

        /** 
         * External Domain Service Call
         * Card Check Service
         */ 
        try {
            String url = appConfig.getUrlCardCheckService() + body.getCard_number();
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<CardCheckResponseType> response = restTemplate.exchange(url, HttpMethod.GET, null, CardCheckResponseType.class);
            exchange_message.setCard_check_result(response.getBody());

        } catch (HttpClientErrorException e) {
            CardCheckResponseType response = new CardCheckResponseType(body.getCard_number());
            response.setResult("2", "E99", "api error");

        } catch (HttpServerErrorException e) {
            CardCheckResponseType response = new CardCheckResponseType(body.getCard_number());
            response.setResult("2", "E99", "api error");

        }
         
        /**
         * Exchange OUT
         */
        exchange.getMessage().setBody(exchange_message);

    }
}
