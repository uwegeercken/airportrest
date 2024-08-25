package com.datamelt.airport.app;

import com.datamelt.airport.service.AirportServiceMapImpl;
import com.datamelt.airport.model.Airport;
import com.datamelt.airport.model.AirportException;
import com.datamelt.airport.port.AirportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import spark.Route;

import java.util.Optional;

public class AirportController
{
    private AirportService airportService = null;
    private final ObjectMapper mapper = new JsonMapper();

    public AirportController(String csvFilename)
    {
        this.airportService = new AirportServiceMapImpl(csvFilename);
    }

    public Route addAirport = (request, response) ->  {
        response.type("application/json");

        String id = request.queryParams("id");
        long idAsLong = -1;
        if(id!=null)
        {
            try
            {
                idAsLong = Long.parseLong(id);
            }
            catch (Exception ex)
            {
                throw new AirportException("error converting id to a long value");
            }
        }
        String name = request.queryParams("name");
        String icaoCode = request.queryParams("icaoCode");
        Airport airport = new Airport(idAsLong, name, icaoCode);

        airportService.addAirport(airport);

        response.status(201); // 201 Created
        return mapper.writeValueAsString(airport);
    };

    public Route getAirport = (request, response) -> {
        response.type("application/json");

        try
        {
            long id = Long.parseLong(request.params(":id"));
            Optional<Airport> airport = airportService.getAirport(id);
            if (airport.isPresent())
            {
                return mapper.writeValueAsString(airport.get());
            } else
            {
                response.status(404); // 404 Not found
                return "{}";
            }
        }
        catch (Exception ex)
        {
            response.status(400); // 404 Not found
            return "{}";
        }
    };

    public Route getAirportByIcaoCode = (request, response) -> {
        response.type("application/json");

        try
        {
            String icaoCode = request.params(":icaocode");
            Optional<Airport> airport = airportService.getAirport(icaoCode);
            if (airport.isPresent())
            {
                return mapper.writeValueAsString(airport.get());
            } else
            {
                response.status(404); // 404 Not found
                return "{}";
            }
        }
        catch (Exception ex)
        {
            response.status(400); // 404 Not found
            return "{}";
        }
    };

    public Route deleteAirport = (request, response) -> {
        response.type("application/json");

        long id = Long.parseLong(request.params(":id"));
        if(airportService.existAirport(id))
        {
            airportService.deleteAirport(id);
            return "{}";
        }
        else
        {
            response.status(404); // 404 Not found
            return "{}";
        }
    };

    public Route getAllAirports = (request, response) -> {
        return mapper.writeValueAsString(airportService.getAirports());
    };

    public void loadAllAirports()
    {
        airportService.loadAirports();
    }

}
