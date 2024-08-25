package com.datamelt.airport.port;

import com.datamelt.airport.model.AirportException;
import com.datamelt.airport.model.Airport;

import java.util.Collection;
import java.util.Optional;

public interface AirportService
{
    void addAirport (Airport airport);
    Collection<Airport> getAirports ();
    Optional<Airport> getAirport (long id);
    Optional<Airport> getAirport (String icaoCode);
    void deleteAirport (long id);
    boolean existAirport (long id);
    void loadAirports ();
}
