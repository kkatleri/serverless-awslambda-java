package com.autobots.aws.lambda.apis;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ReadCustomersFunction {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();

    public APIGatewayProxyResponseEvent getCustomers(APIGatewayProxyRequestEvent request) throws JsonProcessingException {

        ScanResult scanResult =  amazonDynamoDB.scan(new ScanRequest(System.getenv("CUSTOMERS_TABLE")));

        List<Customer> customers = scanResult.getItems().stream().map( item ->
            new Customer(
                    Integer.parseInt(item.get("id").getN()),
                    item.get("customerName").getS(),
                    Integer.parseInt(item.get("rewardPoints").getN())
            )).collect(Collectors.toList());

        String jsonOutput = objectMapper.writeValueAsString(customers);

        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonOutput);
    }
}
