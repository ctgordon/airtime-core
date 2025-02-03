package com.airtime.logbook_service.service.impl;

import org.springframework.stereotype.Service;
import com.airtime.logbook_service.persistence.dao.FlightRepository;
import com.airtime.logbook_service.persistence.model.*;
import com.airtime.logbook_service.service.FlightService;
import com.airtime.logbook_service.web.dto.FlightDTO;
import com.airtime.logbook_service.web.dto.FlightSummaryDTO;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service("flightService")
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public FlightDTO getFlight(UUID uuid) {
        return flightRepository.findByUuid(uuid).dto();
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public boolean save(Flight flight) {
        boolean saved = false;
        try {
            this.flightRepository.save(flight);
            saved = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return saved;
    }

    @Override
    public Flight findFlightById(int id) {
        Flight flight = null;
        Optional<Flight> optionalFlight = flightRepository.findById(id);

        if (optionalFlight.isPresent()) {
            flight = optionalFlight.get();
        }

        return flight;
    }

    @Override
    public void deleteFlight(Flight flight) {
        flightRepository.delete(flight);
    }

    @Override
    public FlightDTO getLatestFlight() {
        Flight flight = flightRepository.findTopByOrderByDepartureDatetimeDesc();
        return flight.dto();
    }

    @Override
    public FlightDTO getLatestFlightByAto(Ato ato) {
        Optional<Flight> optionalFlight = Optional.ofNullable(flightRepository.findTopByAndAtoEqualsOrderByDepartureDatetimeDesc(ato));
        return optionalFlight.map(Flight::dto).orElse(null);
    }

    @Override
    public FlightSummaryDTO getFlightSummary(List<Flight> flightList) {
        FlightSummaryDTO flightSummaryDTO = new FlightSummaryDTO();

        long totalMinutes = 0;
        long totalPicMinutes = 0;
        long totalDualMinutes = 0;
        Flight latestFlight = null;

        /*if (!flightList.isEmpty()) {
            totalMinutes = getMinutes(flightList);

            List<Flight> picList = flightList.stream().filter(flight -> flight.getPerson().getMoniker().equals("SELF")).collect(Collectors.toList());

            if (!picList.isEmpty()) {
                totalPicMinutes = getMinutes(picList);
            }

            List<Flight> dualList = flightList.stream().filter(flight -> !flight.getPerson().getMoniker().equals("SELF")).collect(Collectors.toList());

            if (!dualList.isEmpty()) {
                totalDualMinutes = getMinutes(dualList);
            }

            latestFlight = Collections.max(flightList, Comparator.comparing(Flight::getDepartureDatetime));
        }*/

        flightSummaryDTO.setTotalHours(hoursAndMinutes(totalMinutes));
        flightSummaryDTO.setTotalHoursDual(hoursAndMinutes(totalDualMinutes));
        flightSummaryDTO.setTotalHoursPIC(hoursAndMinutes(totalPicMinutes));
        flightSummaryDTO.setTotalFlights(flightList.size());
        if (latestFlight != null) {
            flightSummaryDTO.setLastFlight(latestFlight.dto());
        } else {
            flightSummaryDTO.setLastFlight(null);
        }

        return flightSummaryDTO;
    }

    @Override
    public List<Flight> getFlightsBetween() {
        List<Flight> flightList = null;

        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dt1 = "2021-09-01 00:00:01";
            String dt2 = "2022-08-31 23:59:59";

            Date date1 = formatter.parse(dt1);
            Timestamp timeStampDate1 = new Timestamp(date1.getTime());

            Date date2 = formatter.parse(dt2);
            Timestamp timeStampDate2 = new Timestamp(date2.getTime());

            flightList = flightRepository.getFlightsByDepartureDatetimeAfterAndDepartureDatetimeBefore(timeStampDate1, timeStampDate2);
        } catch (ParseException e) {
            System.out.println(e);
        }

        return flightList;
    }

    /*@Override
    public List<FlightDTO> getFlightsFromCustomReport(CustomReport customReport) {
        List<FlightDTO> flightDTOList = new ArrayList<>();
        List<Flight> flightList = null;

        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dt1 = customReport.getStartDate().toString();
            String dt2 = customReport.getEndDate().toString();

            Date date1 = formatter.parse(dt1);
            Timestamp timeStampDate1 = new Timestamp(date1.getTime());

            Date date2 = formatter.parse(dt2);
            Timestamp timeStampDate2 = new Timestamp(date2.getTime());

            flightList = flightRepository.getFlightsByDepartureDatetimeAfterAndDepartureDatetimeBefore(timeStampDate1, timeStampDate2);

            if (!flightList.isEmpty()) {
                flightList.forEach(flight -> flightDTOList.add(flight.dto()));
            }
        } catch (ParseException e) {
            System.out.println(e);
        }

        return flightDTOList;
    }*/

    @Override
    public List<Flight> getFlightsFromGoal(Goal goal) {
        List<Flight> flightList = new ArrayList<>();

        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dt1 = goal.getStartDate().toString();
            String dt2 = goal.getEndDate().toString();

            Date date1 = formatter.parse(dt1);
            Timestamp timeStampDate1 = new Timestamp(date1.getTime());

            Date date2 = formatter.parse(dt2);
            Timestamp timeStampDate2 = new Timestamp(date2.getTime());

            flightList = flightRepository.getFlightsByDepartureDatetimeAfterAndDepartureDatetimeBefore(timeStampDate1, timeStampDate2);
        } catch (ParseException e) {
            System.out.println(e);
        }

        return flightList;
    }

    private long getMinutes(List<Flight> flightList) {
        return flightList
                .stream()
                .mapToLong(flight -> flight.getArrivalDatetime().getTime() - flight.getDepartureDatetime().getTime())
                .map(TimeUnit.MILLISECONDS::toMinutes)
                .sum();
    }

    private String hoursAndMinutes(long totalMinutes) {
        String minutesAsString = "";
        int hours = Long.valueOf(totalMinutes).intValue() / 60; //since both are ints, you get an int
        int minutes = Long.valueOf(totalMinutes).intValue() % 60;

        minutesAsString = minutes < 10 ? "0" + minutes : String.valueOf(minutes);

        return hours + ":" + minutesAsString;
    }
}
