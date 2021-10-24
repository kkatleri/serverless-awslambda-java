package com.autobots.aws.lambda.apis;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateCustomerFunction {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DynamoDB dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());

    public APIGatewayProxyResponseEvent createCustomer(APIGatewayProxyRequestEvent request) throws JsonProcessingException {

        Customer customer = objectMapper.readValue(request.getBody(), Customer.class);

        Table customersTable = dynamoDB.getTable(System.getenv("CUSTOMERS_TABLE"));
        Item item = new Item().withPrimaryKey("id", customer.getId())
                .withString("customerName", customer.getCustomerName())
                .withNumber("rewardPoints", customer.getRewardPoints());
        customersTable.putItem(item);

        return new APIGatewayProxyResponseEvent().withStatusCode(201).withBody("Customer created with id : " + customer.getId());

    }
}
