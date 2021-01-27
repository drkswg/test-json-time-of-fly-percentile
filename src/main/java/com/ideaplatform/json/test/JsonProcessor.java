package com.ideaplatform.json.test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JsonProcessor {
    private List<Integer> timeDeltaArrayMinutes = new ArrayList<>();

    public List<Integer> getTimeOfFly(Ticket[] ticketArray) {
        List<Long> timeDeltaArrayTimeStamp = new ArrayList<>();

        try {
            for (Ticket ticket : ticketArray) {
                String departureDateTime = ticket.getDeparture_date() + " "
                        + ticket.getDeparture_time();
                String arrivalDateTime = ticket.getArrival_date() + " "
                        + ticket.getArrival_time();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy hh:mm");
                Timestamp departureTimestamp = new Timestamp(dateFormat.parse(departureDateTime).getTime());
                Timestamp arrivalTimestamp = new Timestamp(dateFormat.parse(arrivalDateTime).getTime());
                timeDeltaArrayTimeStamp.add(arrivalTimestamp.getTime() - departureTimestamp.getTime());
            }

            for(Long timeOfFly : timeDeltaArrayTimeStamp) {
                timeDeltaArrayMinutes.add((int) TimeUnit.MILLISECONDS.toMinutes(timeOfFly));
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return timeDeltaArrayMinutes;
    }

    public double getAverageTimeOfFly(List<Integer> timeOfFlyList) {
        Integer sum = 0;
        if(!timeOfFlyList.isEmpty()) {
            for(Integer delta : timeOfFlyList) {
                sum += delta;
            }
            return sum.doubleValue() / timeOfFlyList.size();
        }
        return sum;
    }

    public double getPercentile(List<Integer> timeOfFlyList, double percentile) {
        Collections.sort(timeOfFlyList);
        int index = (int)Math.ceil((percentile / 100) * timeOfFlyList.size());

        return timeOfFlyList.get(index - 1);
    }
}
