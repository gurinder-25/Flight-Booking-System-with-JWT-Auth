package com.example.flight.service;

import com.example.flight.model.Booking;
import com.example.flight.model.Flight;
import com.example.flight.repo.BookingRepo;
import com.example.flight.repo.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private FlightRepo flightRepo;

    @Transactional
    public Booking bookFlight(String username, String origin, String destination){
        Flight flight = flightRepo.findByOriginAndDestination(origin, destination)
                .stream()
                .filter(f->f.getSeatsAvailable()>0)
                .findFirst()
                .orElseThrow(()-> new RuntimeException("No available flights!"));

        flight.setSeatsAvailable(flight.getSeatsAvailable()-1);
        flightRepo.save(flight);

        Booking booking = new Booking();
        booking.setUsername(username);
        booking.setOrigin(origin);
        booking.setDestination(destination);
        return bookingRepo.save(booking);
    }

}
