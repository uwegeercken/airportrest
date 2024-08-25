package com.datamelt.airport.model;

public enum Endpoint
{
    AIRPORT_GET             ("/airports/:id"),
    AIRPORT_GET_BY_ICAOCODE ("/airports/icaocode/:icaocode"),
    AIRPORT_GET_ALL         ("/airports"),
    AIRPORT_ADD             ("/airports"),
    AIRPORT_DELETE          ("/airports/:id");

    private final String path;

    Endpoint(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        return path;
    }
}
