package com.example.flight.controller;

import com.example.flight.model.Flight;
import com.example.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getAllFlights(){
        return flightService.getAllFlights();
    }

    @PostMapping
    public List<Flight> searchFlights(@RequestBody Map<String, String> request){
        String origin = request.get("origin");
        String destination = request.get("destination");
        return flightService.findFlightsByOriginAndDestination(origin, destination);
    }
}
