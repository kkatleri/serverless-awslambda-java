package com.autobots.aws.lambda;

/**
 * Handler for requests to Lambda function.
 */
public class App {

    public void truckTracker(Coordinates coordinates) {

        System.out.println("Latitude - " + coordinates.getLatitude() + ", Longitude - " + coordinates.getLatitude());
    }

    public Ticket getTicket(Payment payment){

        System.out.println("Payment received from " + payment.name + " of the amount " + payment.amount);

        System.out.println("Ticket confirmed... dispatching now.");

        return new Ticket("111", "$300", "Confirmed");
    }
}
