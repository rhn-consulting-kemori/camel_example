package com.redhat.example.route;

// Util
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonProcessingException;
// ----------------------------------------------------------------
// Spring,Junit
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
// ----------------------------------------------------------------
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Produce;
import org.apache.camel.ServiceStatus;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@CamelSpringBootTest
@UseAdviceWith
public class RouteProcessRouteTest {

    /** Route ID */
    private static final String TARGET_ROUTE_ID_ROUTE_PROCESS = "route-process";
    private static final String TARGET_ROUTE_ID_INITIAL_PROCESS = "initial-process";
    private static final String TARGET_ROUTE_ID_FORMAT_CHECK = "format-check";
    private static final String TARGET_ROUTE_ID_DEPOSIT_ENTRY_CHECK = "deposit-entry-check";
    private static final String TARGET_ROUTE_ID_DEPOSIT_CATEGORY = "deposit-category";
    private static final String TARGET_ROUTE_ID_CHECK_AVAILABLE_DEPOSIT_AMOUNT = "check-available-deposit-amount";
    private static final String TARGET_ROUTE_ID_DEPOSIT_ALLOCATION = "deposit-allocation";
    private static final String TARGET_ROUTE_ID_DEPOSIT = "deposit";
    private static final String TARGET_ROUTE_ID_DEPOSIT_RESULT_MESSAGE = "deposit-result-message";
    private static final String TARGET_ROUTE_ID_DEPOSIT_FINISH_PROCESS = "finish-process";

    /** CamelContext */
    @Autowired
    protected CamelContext camelContext;

    /** Expected Data */
    @Autowired
    RouteProcessRouteTestDataProvider dataProvider;

    /** Mock EndPoint */ 
    // ----------------------------------------------------------------
    // route: route-process
    // ----------------------------------------------------------------
    /** id: from-kafka-deposit, uri: kafka:{{app.input-topic-name}} -> direct:start */
    @Autowired
    protected ProducerTemplate start;

    /** id: to-initial-process, uri: direct:initial-process -> mock:direct:initial-process */
    @EndpointInject("mock:direct:initial-process")
    protected MockEndpoint mock_direct_initial_process;

    /** id: to-format-check, uri: direct:format-check -> mock:direct:format-check */
    @EndpointInject("mock:direct:format-check")
    protected MockEndpoint mock_direct_format_check;

    /** id: to-deposit-entry-check, uri: direct:deposit-entry-check -> mock:direct:deposit-entry-check */
    @EndpointInject("mock:direct:deposit-entry-check")
    protected MockEndpoint mock_direct_deposit_entry_check;

    /** id: to-deposit-category, uri: direct:deposit-category -> mock:direct:deposit-category */
    @EndpointInject("mock:direct:deposit-category")
    protected MockEndpoint mock_direct_deposit_category;

    /** id: to-check-available-deposit-amount, uri: direct:check-available-deposit-amount -> mock:direct:check-available-deposit-amount */
    @EndpointInject("mock:direct:check-available-deposit-amount")
    protected MockEndpoint mock_direct_check_available_deposit_amount;

    /** id: to-deposit-allocation, uri: direct:deposit-allocation -> mock:direct:deposit-allocation */
    @EndpointInject("mock:direct:deposit-allocation")
    protected MockEndpoint mock_direct_deposit_allocation;

    /** id: to-deposit, uri: direct:deposit -> mock:direct:deposit */
    @EndpointInject("mock:direct:deposit")
    protected MockEndpoint mock_direct_deposit;

    /** id: to-deposit-result-message, uri: direct:deposit-result-message -> mock:direct:deposit-result-message */
    @EndpointInject("mock:direct:deposit-result-message")
    protected MockEndpoint mock_direct_deposit_result_message;

    /** id: to-kafka-deposit-end, uri: kafka:{{app.output-topic-name}} -> mock:to-kafka-deposit-end */
    @EndpointInject("mock:to-kafka-deposit-end")
    protected MockEndpoint mock_to_kafka_deposit_end;

    /** id: to-finish-process, uri: direct:finish-process -> mock:direct:finish-process */
    @EndpointInject("mock:direct:finish-process")
    protected MockEndpoint mock_direct_finish_process;
    // ----------------------------------------------------------------
    // route: initial-process
    // ----------------------------------------------------------------
    /** id: from-initial-process, uri: direct:initial-process -> mock:direct:initial-process */
    // ----------------------------------------------------------------
    // route: format-check
    // ----------------------------------------------------------------
    /** id: to-format-check, uri: direct:format-check -> mock:direct:format-check */
    /** id: bean-format-check-rule, uri: "" -> mock:bean-format-check-rule */
    @EndpointInject("mock:bean-format-check-rule")
    protected MockEndpoint mock_bean_format_check_rule;
    // ----------------------------------------------------------------
    // route: deposit-entry-check
    // ----------------------------------------------------------------
    /** id: from-deposit-entry-check, uri: direct:deposit-entry-check -> mock:direct:deposit-entry-check */
    /** id: to-deposit-entry-check-service, uri: http://{{app.url-deposit-entry-check}} -> mock:http-deposit-entry-check-service */
    @EndpointInject("mock:http-deposit-entry-check-service")
    protected MockEndpoint mock_http_deposit_entry_check_service;
    // ----------------------------------------------------------------
    // route: deposit-category
    // ----------------------------------------------------------------
    /** id: from-deposit-category, uri: direct:deposit-category -> mock:direct:deposit-category */
    /** id: to-deposit-category-service, uri: http://{{app.url-deposit-category}} -> mock:http-deposit-category-service */
    @EndpointInject("mock:http-deposit-category-service")
    protected MockEndpoint mock_http_deposit_category_service;
    // ----------------------------------------------------------------
    // route: check-available-deposit-amount
    // ----------------------------------------------------------------
    /** id: to-check-available-deposit-amount, uri: direct:check-available-deposit-amount -> mock:direct:check-available-deposit-amount */
    /** id: to-check-available-deposit-amount-service, uri: http://{{app.url-check-available-deposit-amount}} -> mock:http-check-available-deposit-amount-service */
    @EndpointInject("mock:http-check-available-deposit-amount-service")
    protected MockEndpoint mock_http_check_available_deposit_amount_service;
    // ----------------------------------------------------------------
    // route: deposit-allocation
    // ----------------------------------------------------------------
    /** id: from-deposit-allocation, uri: direct:deposit-allocation -> mock:direct:deposit-allocation */
    /** id: to-deposit-allocation-service, uri: http://{{app.url-deposit-allocation}} -> mock:http-deposit-allocation-service */
    @EndpointInject("mock:http-deposit-allocation-service")
    protected MockEndpoint mock_http_deposit_allocation_service;
    // ----------------------------------------------------------------
    // route: deposit
    // ----------------------------------------------------------------
    /** id: from-deposit, uri: direct:deposit -> mock:direct:deposit */
    /** id: to-deposit-service, uri: http://{{app.url-deposit}} -> mock:http-deposit-service */
    @EndpointInject("mock:http-deposit-service")
    protected MockEndpoint mock_http_deposit_service;
    // ----------------------------------------------------------------
    // route: deposit-result-message
    // ----------------------------------------------------------------
    /** id: from-deposit-result-message, uri: direct:deposit-result-message -> mock:direct:deposit-result-message */
    /** id: bean-deposit-result-message-rule, uri: "" -> mock:bean-deposit-result-message-rule */
    @EndpointInject("mock:bean-deposit-result-message-rule")
    protected MockEndpoint mock_bean_deposit_result_message_rule;
    // ----------------------------------------------------------------
    // route: finish-process
    // ----------------------------------------------------------------
    /** id: from-finish-process, uri: direct:finish-process -> mock:direct:finish-process */

    // ----------------------------------------------------------------
    @BeforeEach
    void beforeEach() throws Exception {

        // Route Mock Setting
        AdviceWith.adviceWith(
            camelContext,
            TARGET_ROUTE_ID_ROUTE_PROCESS,
            advice -> {
                advice.replaceFromWith("direct:start");
                advice.weaveById("to-kafka-deposit-end").replace().to("mock:to-kafka-deposit-end");
                advice.mockEndpoints("direct:.+");
            }
        );
        AdviceWith.adviceWith(
            camelContext,
            TARGET_ROUTE_ID_INITIAL_PROCESS,
            advice -> {
                advice.mockEndpoints("direct:.+");
            }
        );
        AdviceWith.adviceWith(
            camelContext,
            TARGET_ROUTE_ID_FORMAT_CHECK,
            advice -> {
                advice.mockEndpoints("direct:.+");
                advice.weaveById("bean-format-check-rule").replace().to("mock:bean-format-check-rule");
            }
        );
        AdviceWith.adviceWith(
            camelContext,
            TARGET_ROUTE_ID_DEPOSIT_ENTRY_CHECK,
            advice -> {
                advice.mockEndpoints("direct:.+");
                advice.weaveById("to-deposit-entry-check-service").replace().to("mock:http-deposit-entry-check-service");
            }
        );
        AdviceWith.adviceWith(
            camelContext,
            TARGET_ROUTE_ID_DEPOSIT_CATEGORY,
            advice -> {
                advice.mockEndpoints("direct:.+");
                advice.weaveById("to-deposit-category-service").replace().to("mock:http-deposit-category-service");
            }
        );
        AdviceWith.adviceWith(
            camelContext,
            TARGET_ROUTE_ID_CHECK_AVAILABLE_DEPOSIT_AMOUNT,
            advice -> {
                advice.mockEndpoints("direct:.+");
                advice.weaveById("to-check-available-deposit-amount-service").replace().to("mock:http-check-available-deposit-amount-service");
            }
        );
        AdviceWith.adviceWith(
            camelContext,
            TARGET_ROUTE_ID_DEPOSIT_ALLOCATION,
            advice -> {
                advice.mockEndpoints("direct:.+");
                advice.weaveById("to-deposit-allocation-service").replace().to("mock:http-deposit-allocation-service");
            }
        );
        AdviceWith.adviceWith(
            camelContext,
            TARGET_ROUTE_ID_DEPOSIT,
            advice -> {
                advice.mockEndpoints("direct:.+");
                advice.weaveById("to-deposit-service").replace().to("mock:http-deposit-service");
            }
        );
        AdviceWith.adviceWith(
            camelContext,
            TARGET_ROUTE_ID_DEPOSIT_RESULT_MESSAGE,
            advice -> {
                advice.mockEndpoints("direct:.+");
                advice.weaveById("bean-deposit-result-message-rule").replace().to("mock:bean-deposit-result-message-rule");
            }
        );
        AdviceWith.adviceWith(
            camelContext,
            TARGET_ROUTE_ID_DEPOSIT_FINISH_PROCESS,
            advice -> {
                advice.mockEndpoints("direct:.+");
            }
        );
        camelContext.start();
    }

    @Test
    public void test_No1_Normal() throws Exception {

        // Given
        dataProvider.setNormalData();
        Exchange exchange = ExchangeBuilder.anExchange(camelContext).withBody(dataProvider.getRoute_process_json()[0]).build();
        setMockDirectEndpoint();
        setMockBeanEndpoint();
        setMockHttpEndpoint();

        // When
        start.send("direct:start", exchange);

        //Then
        MockEndpoint.assertIsSatisfied(camelContext);
        assertThat(exchange.getProperty("deposit_request"), is(dataProvider.getRoute_request()));
        assertThat(exchange.getProperty("format-check_response"), is(dataProvider.getFormat_check_response()));
        assertThat(exchange.getProperty("deposit_entry_check_response"), is(dataProvider.getDeposit_entry_check_response()));
        assertThat(exchange.getProperty("deposit_category_code"), is(dataProvider.getDeposit_category_response().getDeposit_category_code()));
        assertThat(exchange.getProperty("deposit_available_amount_data"), is(dataProvider.getCheck_available_deposit_amount_response().getDeposit_available_amount_data()));
        assertThat(exchange.getProperty("deposit_allocation_data"), is(dataProvider.getDeposit_allocation_response().getDeposit_allocation_data()));
        assertThat(exchange.getProperty("deposit_data"), is(dataProvider.getDeposit_response().getDeposit_data()));
        assertThat(exchange.getProperty("deposit_result_message_response"), is(dataProvider.getDeposit_result_message_response()));

    }

    // Mock設定: Direct
    public void setMockDirectEndpoint() {
        mock_direct_initial_process.expectedMessageCount(1);
        mock_direct_format_check.expectedMessageCount(1);
        mock_direct_deposit_entry_check.expectedMessageCount(1);
        mock_direct_deposit_category.expectedMessageCount(1);
        mock_direct_check_available_deposit_amount.expectedMessageCount(1);
        mock_direct_deposit_allocation.expectedMessageCount(1);
        mock_direct_deposit.expectedMessageCount(1);
        mock_direct_deposit_result_message.expectedMessageCount(1);
        mock_to_kafka_deposit_end.expectedMessageCount(1);
        mock_direct_finish_process.expectedMessageCount(1);
    }

    // Mock設定: Bean
    public void setMockBeanEndpoint() {
        mock_bean_format_check_rule.whenExchangeReceived(
            1,
            e -> { 
                assertThat(e.getMessage().getBody(), is(dataProvider.getFormat_check_request()));
                e.getMessage().setBody(dataProvider.getFormat_check_response()); 
            });
        mock_bean_deposit_result_message_rule.whenExchangeReceived(
            1,
            e -> { 
                assertThat(e.getMessage().getBody(), is(dataProvider.getDeposit_result_message_request()));
                e.getMessage().setBody(dataProvider.getDeposit_result_message_response()); 
            });
    }

    // Mock設定: Http
    public void setMockHttpEndpoint() {
        ObjectMapper mapper = new ObjectMapper();
        mock_http_deposit_entry_check_service.whenExchangeReceived(
            1,
            e -> { 
                assertEquals(mapper.readTree(dataProvider.getDeposit_entry_check_json()[0]), mapper.readTree(e.getMessage().getBody().toString()));
                e.getMessage().setBody(dataProvider.getDeposit_entry_check_json()[1]); 
            });
        mock_http_deposit_category_service.whenExchangeReceived(
            1,
            e -> { 
                assertEquals(mapper.readTree(dataProvider.getDeposit_category_json()[0]), mapper.readTree(e.getMessage().getBody().toString()));
                e.getMessage().setBody(dataProvider.getDeposit_category_json()[1]); 
            });
        mock_http_check_available_deposit_amount_service.whenExchangeReceived(
            1,
            e -> { 
                assertEquals(mapper.readTree(dataProvider.getCheck_available_deposit_amount_json()[0]), mapper.readTree(e.getMessage().getBody().toString()));
                e.getMessage().setBody(dataProvider.getCheck_available_deposit_amount_json()[1]); 
            });
        mock_http_deposit_allocation_service.whenExchangeReceived(
            1,
            e -> { 
                assertEquals(mapper.readTree(dataProvider.getDeposit_allocation_json()[0]), mapper.readTree(e.getMessage().getBody().toString()));
                e.getMessage().setBody(dataProvider.getDeposit_allocation_json()[1]); 
            });
        mock_http_deposit_service.whenExchangeReceived(
            1,
            e -> { 
                assertEquals(mapper.readTree(dataProvider.getDeposit_json()[0]), mapper.readTree(e.getMessage().getBody().toString()));
                e.getMessage().setBody(dataProvider.getDeposit_json()[1]); 
            });

    }

    @Test
    public void test_No2_Error() throws Exception {

        // Given
        dataProvider.setErrorData();
        Exchange exchange = ExchangeBuilder.anExchange(camelContext).withBody(dataProvider.getRoute_process_json()[0]).build();
        setMockDirectEndpoint();
        setMockBeanEndpoint();
        setErrorMockHttpEndpoint();

        // When
        start.send("direct:start", exchange);

        //Then
        MockEndpoint.assertIsSatisfied(camelContext);
        assertThat(exchange.getProperty("deposit_request"), is(dataProvider.getRoute_request()));
        assertThat(exchange.getProperty("format-check_response"), is(dataProvider.getFormat_check_response()));
        assertThat(exchange.getProperty("deposit_result_message_response"), is(dataProvider.getDeposit_result_message_response()));

    }

    // Mock設定: Http: Error
    public void setErrorMockHttpEndpoint() {
        mock_http_deposit_entry_check_service.expectedMessageCount(0);
        mock_http_deposit_category_service.expectedMessageCount(0);
        mock_http_check_available_deposit_amount_service.expectedMessageCount(0);
        mock_http_deposit_allocation_service.expectedMessageCount(0);
        mock_http_deposit_service.expectedMessageCount(0);
    }
}
