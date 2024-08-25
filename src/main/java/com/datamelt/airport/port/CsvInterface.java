package com.datamelt.airport.port;

import com.datamelt.airport.model.Airport;

import java.util.List;
import java.util.Optional;

public interface CsvInterface
{
    Optional<Airport> getAirport(long id);
    List<Airport> getAllAirports();
}
