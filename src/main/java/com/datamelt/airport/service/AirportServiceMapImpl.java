package com.datamelt.airport.service;

import com.datamelt.airport.adapter.csv.CsvReader;
import com.datamelt.airport.model.Airport;
import com.datamelt.airport.port.AirportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class AirportServiceMapImpl implements AirportService
{
    private static final Logger logger =  LoggerFactory.getLogger(AirportServiceMapImpl.class);
    private final HashMap<Long, Airport> airportMap;
    private final CsvReader csvReader;

    public AirportServiceMapImpl(String csvFilename)
    {
        this.airportMap = new HashMap<>();
        this.csvReader = new CsvReader(csvFilename);
    }

    @Override
    public void addAirport(Airport airport)
    {
        airportMap.put(airport.getId(), airport);
    }

    @Override
    public Collection<Airport> getAirports()
    {
        return airportMap.values();
    }

    @Override
    public Optional<Airport> getAirport(long id)
    {
        Airport airport = airportMap.get(id);
        if(airport==null)
        {
            return Optional.empty();
        }
        else
        {
            return Optional.of(airport);
        }
    }

    @Override
    public Optional<Airport> getAirport(String icaoCode)
    {
        return airportMap.entrySet().stream()
                .filter(entry -> entry.getValue().getIcaoCode().toUpperCase().equals(icaoCode.toUpperCase()))
                .map(entry -> entry.getValue())
                .findFirst();
    }

    @Override
    public void deleteAirport(long id)
    {
        airportMap.remove(id);
    }

    @Override
    public boolean existAirport(long id)
    {
        return airportMap.containsKey(id);
    }

    @Override
    public void loadAirports()
    {
        csvReader.getAllAirports().forEach(this::addAirport);
    }
}
