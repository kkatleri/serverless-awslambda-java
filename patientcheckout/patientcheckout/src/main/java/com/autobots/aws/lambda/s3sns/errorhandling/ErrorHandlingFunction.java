package com.autobots.aws.lambda.s3sns.errorhandling;

import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorHandlingFunction {

    public void handler(SNSEvent snsEvent){

        Logger logger = LoggerFactory.getLogger(ErrorHandlingFunction.class);

        snsEvent.getRecords().forEach(record -> logger.info("Dead Letter Queue Event - " + record.toString()));

    }
}
