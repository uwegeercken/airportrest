package com.datamelt.airport.adapter.csv;

import com.datamelt.airport.model.Airport;
import com.datamelt.airport.port.CsvInterface;
import com.datamelt.airport.service.AirportServiceMapImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsvReader implements CsvInterface
{
    private static final Logger logger =  LoggerFactory.getLogger(CsvReader.class);
    private static final int headerLinesToSkip = 1;
    private final String filename;

    public CsvReader(String filename)
    {
        this.filename = filename;
    }

    @Override
    public Optional<Airport> getAirport(long id)
    {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename)))
        {
            long start = System.currentTimeMillis();
            Optional<Airport> airportFromFile = bufferedReader.lines()
                    .skip(headerLinesToSkip)
                    .map(CsvRowMapper::map)
                    .filter(airport -> airport.getId()==id)
                    .findFirst();
            double runtime = (System.currentTimeMillis()-start)/1000d;
            logger.info("runtime loading airport with id: [{}] from file: [{}] secs", id, runtime);
            return airportFromFile;

        }
        catch (Exception ex)
        {
            logger.error("error processing csv file: [{}]", ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Airport> getAllAirports()
    {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename)))
        {
            long start = System.currentTimeMillis();
            List<Airport> airportsFromFile = bufferedReader.lines()
                    .skip(headerLinesToSkip)
                    .map(CsvRowMapper::map)
                    .toList();
            double runtime = (System.currentTimeMillis()-start)/1000d;
            logger.info("runtime loading [{}] airports: [{}] secs", airportsFromFile.size(), runtime);
            return airportsFromFile;
        }
        catch (Exception ex)
        {
            logger.error("error processing csv file: [{}]", ex.getMessage());
            return new ArrayList<>();
        }
    }
}
