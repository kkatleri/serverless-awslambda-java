package com.autobots.aws.lambda.sqs;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClaimManagementFunction {

    Logger logger = LoggerFactory.getLogger(ClaimManagementFunction.class);

    public void handler(SQSEvent sqsEvent){

        sqsEvent.getRecords().forEach(record -> logger.info("Claim Management SQS Event - " + record.getBody()));
    }
}
