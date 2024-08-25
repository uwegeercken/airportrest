package com.datamelt.airport.adapter.csv;

import com.datamelt.airport.model.Airport;

public class CsvRowMapper
{
    private static final int FIELD_ID_POSITION = 0;
    private static final int FIELD_NAME_POSITION = 1;
    private static final int FIELD_ICAO_CODE_POSITION = 5;

    public static Airport map(String line)
    {
        String[] fields = line.split("\t");
        long id = Long.parseLong(fields[FIELD_ID_POSITION]);
        return new Airport(id,fields[FIELD_NAME_POSITION], fields[FIELD_ICAO_CODE_POSITION]);
    }
}
