package com.datamelt.airport.model;

public class Airport
{
    private final long id;
    private final String name;
    private final String icaoCode;

    public Airport(long id, String name, String icaoCode)
    {
        this.id = id;
        this.name = name;
        this.icaoCode = icaoCode;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getIcaoCode()
    {
        return icaoCode;
    }
}
