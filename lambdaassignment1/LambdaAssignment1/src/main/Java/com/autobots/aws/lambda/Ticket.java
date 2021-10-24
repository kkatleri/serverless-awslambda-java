package com.autobots.aws.lambda;

public class Ticket {

    String ticketID;
    String ticketPrice;
    String status;

    public Ticket() {
    }

    public Ticket(String ticketID, String ticketPrice, String status) {
        this.ticketID = ticketID;
        this.ticketPrice = ticketPrice;
        this.status = status;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
