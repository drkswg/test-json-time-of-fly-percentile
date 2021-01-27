package com.ideaplatform.json.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File json = new File("data/tickets.json");
            TicketWrapper ticketWrapper = mapper.readValue(json, TicketWrapper.class);
            Ticket[] ticketArray = ticketWrapper.getTickets();
            JsonProcessor jsonProcessor = new JsonProcessor();
            List<Integer> list = jsonProcessor.getTimeOfFly(ticketArray);
            System.out.println("Среднее время перелета Владивосток - Тель-Авив составляет " +
                    jsonProcessor.getAverageTimeOfFly(list) + " минут");
            System.out.println("90-ый процентиль времени полета составляет " +
                    jsonProcessor.getPercentile(list, 90));

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
