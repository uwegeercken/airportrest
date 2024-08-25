package com.datamelt.airport.app;
import com.datamelt.airport.model.Endpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;
public class AirportRestApp
{
    private static final Logger logger =  LoggerFactory.getLogger(AirportRestApp.class);

    private AirportController airportController;

    public static void main(String[] args)
    {
        logger.info("initializing airport application");
        AirportRestApp application = new AirportRestApp();
        application.airportController = new AirportController(args[0]);
        logger.info("loading all airports from csv file [{}]", args[0]);
        application.loadAllAirports();
        application.run();
    }
    private void loadAllAirports()
    {
        airportController.loadAllAirports();
    }

    private void run()
    {
        post(Endpoint.AIRPORT_ADD.getPath(), airportController.addAirport);
        get(Endpoint.AIRPORT_GET.getPath(), airportController.getAirport );
        get(Endpoint.AIRPORT_GET_BY_ICAOCODE.getPath(), airportController.getAirportByIcaoCode );
        delete(Endpoint.AIRPORT_DELETE.getPath(), airportController.deleteAirport);
        get(Endpoint.AIRPORT_GET_ALL.getPath(), airportController.getAllAirports);
    }
}