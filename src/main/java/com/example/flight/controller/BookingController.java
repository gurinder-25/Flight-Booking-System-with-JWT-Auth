package com.example.flight.controller;

import com.example.flight.model.Booking;
import com.example.flight.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public String createBooking(@RequestBody Map<String, String> request){
        String username = request.get("username");
        String origin = request.get("origin");
        String destination = request.get("destination");

        bookingService.bookFlight(username, origin, destination);
        return "Flight Booked!";
    }
}
