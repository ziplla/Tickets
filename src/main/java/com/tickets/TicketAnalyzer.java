package com.tickets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TicketAnalyzer {

    public static void main(String[] args) {
        String filePath = args[0];
        List<Ticket> tickets = convertJsonFileToTickets(filePath);
        processTickets(tickets);
        calculateStatistics(tickets);
    }

    /**
     * Converts JSON file containing flight tickets data into a list of Ticket objects.
     *
     * @param filePath Path to the JSON file containing flight tickets data.
     * @return List of Ticket objects parsed from the JSON file, or null if there was an error reading or parsing the file.
     */
    public static List<Ticket> convertJsonFileToTickets(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type ticketListType = new TypeToken<TicketsWrapper>() {
            }.getType();
            TicketsWrapper ticketsWrapper = gson.fromJson(reader, ticketListType);
            return ticketsWrapper.getTickets();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Processes the list of flight tickets to calculate the minimum flight time for each carrier
     * on flights from Vladivostok to Tel Aviv.
     *
     * @param tickets List of Ticket objects representing flight tickets.
     */
    public static void processTickets(List<Ticket> tickets) {
        List<Ticket> vvoToTlvTickets = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getOrigin().equals("VVO") && ticket.getDestination().equals("TLV")) {
                vvoToTlvTickets.add(ticket);
            }
        }

        Map<String, Long> carrierMinFlightTimes = new HashMap<>();
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yy HH:mm");


        for (Ticket ticket : vvoToTlvTickets) {
            try {
                String departureDateTime = ticket.getDepartureDate() + " " + ticket.getDepartureTime();
                String arrivalDateTime = ticket.getArrivalDate() + " " + ticket.getArrivalTime();

                Date departureDate = dateTimeFormat.parse(departureDateTime);
                Date arrivalDate = dateTimeFormat.parse(arrivalDateTime);

                long flightTimeMillis = arrivalDate.getTime() - departureDate.getTime();
                long flightTimeMinutes = flightTimeMillis / (1000 * 60);


                carrierMinFlightTimes.put(ticket.getCarrier(),
                        Math.min(carrierMinFlightTimes.getOrDefault(ticket.getCarrier(), Long.MAX_VALUE), flightTimeMinutes));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        System.out.println("Minimum flight times by carrier:");
        for (Map.Entry<String, Long> entry : carrierMinFlightTimes.entrySet()) {
            System.out.println("Carrier: " + entry.getKey() + ", Min Flight Time: " + entry.getValue() + " minutes");
        }
    }

    /**
     * Calculates and prints statistics for flights from Vladivostok to Tel Aviv,
     * including average price, median price, and the difference between average and median prices
     * for each carrier and overall.
     *
     * @param tickets List of Ticket objects representing flight tickets.
     */
    public static void calculateStatistics(List<Ticket> tickets) {
        List<Ticket> vvoToTlvTickets = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getOrigin().equals("VVO") && ticket.getDestination().equals("TLV")) {
                vvoToTlvTickets.add(ticket);
            }
        }

        Map<String, List<Integer>> carrierPricesMap = new HashMap<>();
        for (Ticket ticket : vvoToTlvTickets) {
            carrierPricesMap.computeIfAbsent(ticket.getCarrier(), k -> new ArrayList<>()).add(ticket.getPrice());
        }

        System.out.println("Statistics for flights from Vladivostok to Tel Aviv:");
        for (Map.Entry<String, List<Integer>> entry : carrierPricesMap.entrySet()) {
            String carrier = entry.getKey();
            List<Integer> prices = entry.getValue();


            double averagePrice = calculateAverage(prices);


            double medianPrice = calculateMedian(prices);

            double diffAverageMedian = averagePrice - medianPrice;

            System.out.println("Carrier: " + carrier);
            System.out.println("Average Price: " + averagePrice);
            System.out.println("Median Price: " + medianPrice);
            System.out.println("Difference between Average and Median: " + diffAverageMedian);
            System.out.println();
        }

        List<Integer> allPrices = new ArrayList<>();
        for (Ticket ticket : vvoToTlvTickets) {
            allPrices.add(ticket.getPrice());
        }

        double averageAllPrices = calculateAverage(allPrices);

        double medianAllPrices = calculateMedian(allPrices);

        double diffAverageMedianAll = averageAllPrices - medianAllPrices;

        System.out.println("Overall Statistics for all carriers:");
        System.out.println("Average Price: " + averageAllPrices);
        System.out.println("Median Price: " + medianAllPrices);
        System.out.println("Difference between Average and Median: " + diffAverageMedianAll);
    }

    /**
     * Calculates the average of a list of prices.
     *
     * @param prices List of integers representing prices.
     * @return Average price as a double.
     */
    public static double calculateAverage(List<Integer> prices) {
        if (prices.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int price : prices) {
            sum += price;
        }
        return (double) sum / prices.size();
    }

    /**
     * Calculates the median of a list of prices.
     *
     * @param prices List of integers representing prices.
     * @return Median price as a double.
     */
    public static double calculateMedian(List<Integer> prices) {
        if (prices.isEmpty()) {
            return 0;
        }

        Collections.sort(prices);


        int size = prices.size();
        if (size % 2 == 0) {
            return (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2.0;
        } else {
            return prices.get(size / 2);
        }
    }
}

