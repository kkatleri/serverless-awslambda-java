package com.autobots.aws.lambda.s3sns;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientCheckoutFunction {

    public static final String PATIENT_CHECKOUT_TOPIC = System.getenv("PATIENT_CHECKOUT_TOPIC");
    private final AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
    private final AmazonSNS snsClient = AmazonSNSAsyncClientBuilder.defaultClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void handler(S3Event event, Context context){

        //LambdaLogger logger = context.getLogger();
        Logger logger = LoggerFactory.getLogger(PatientCheckoutFunction.class);
        List<PatientCheckoutEvent> patientCheckoutEvents = new ArrayList<PatientCheckoutEvent>();

        logger.info("Reading data from S3");
        event.getRecords().stream().forEach(record -> {
            S3ObjectInputStream s3ObjectInputStream = s3Client.getObject(
                    record.getS3().getBucket().getName(), record.getS3().getObject().getKey()).getObjectContent();
            try {
                patientCheckoutEvents.addAll(Arrays.asList(
                        objectMapper.readValue(s3ObjectInputStream, PatientCheckoutEvent[].class)));
                logger.info(patientCheckoutEvents.toString());
                s3ObjectInputStream.close();

            } catch (IOException e) {
                /*StringWriter stringWriter = new StringWriter();
                e.printStackTrace(new PrintWriter(stringWriter));
                logger.log(stringWriter.toString());*/

                logger.error("Error occurred while processing S3 Event - "+ e);
                throw new RuntimeException("Error occurred while processing S3 Event :", e);
            }

            logger.info("publishing patient checkout data to SNS - " + String.valueOf(patientCheckoutEvents));

            publishMessageToSNS(patientCheckoutEvents);

        });

    }

    private void publishMessageToSNS(List<PatientCheckoutEvent> patientCheckoutEvents) {
        patientCheckoutEvents.forEach(patientCheckoutEvent -> {
            try {
                snsClient.publish(PATIENT_CHECKOUT_TOPIC, objectMapper.writeValueAsString(patientCheckoutEvent));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}
