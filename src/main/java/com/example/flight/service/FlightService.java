package com.example.flight.service;

import com.example.flight.model.Flight;
import com.example.flight.repo.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepo flightRepo;

    public List<Flight> getAllFlights(){
        return flightRepo.findAll();
    }

    public List<Flight> findFlightsByOriginAndDestination(String origin, String destination){
        return flightRepo.findByOriginAndDestination(origin, destination);
    }
}
