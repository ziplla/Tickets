package com.tickets;

import com.google.gson.annotations.SerializedName;

public class Ticket {

    @SerializedName("origin")
    private String origin;

    @SerializedName("origin_name")
    private String originName;

    @SerializedName("destination")
    private String destination;

    @SerializedName("destination_name")
    private String destinationName;

    @SerializedName("departure_date")
    private String departureDate;

    @SerializedName("departure_time")
    private String departureTime;

    @SerializedName("arrival_date")
    private String arrivalDate;

    @SerializedName("arrival_time")
    private String arrivalTime;

    @SerializedName("carrier")
    private String carrier;

    @SerializedName("stops")
    private int stops;

    @SerializedName("price")
    private int price;

    public String getOrigin() {
        return origin;
    }

    public String getOriginName() {
        return originName;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public int getStops() {
        return stops;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "origin='" + origin + '\'' +
                ", originName='" + originName + '\'' +
                ", destination='" + destination + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", carrier='" + carrier + '\'' +
                ", stops=" + stops +
                ", price=" + price +
                '}';
    }
}
