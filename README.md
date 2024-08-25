# airportrest
Java application with a rest interface based on spark java microframework.

For testing purposes.

Contains a list of 7733 airports with id, name and ICAO code.

Offers following REST endpoints (return a Json representation):
- get airport by id: /airports/:id
- get airport by ICAO code: /airports/icaocode/:icaocode
- get all airports: /airports
- post airport: /airports?id=<id of airport>&name=<name of airport>&icaoCode=<ICAO code of airport>
- delete airport by id: /airports/:id

Examples:
- create airport (post): http://localhost:4567/airports?id=9999&name=DummyAirport&icaoCode=XXXX
- get airport by id: http://localhost:4567/airports/9999
- get airport by ICAO code: http://localhost:4567/airports/icaocode/XXXX
- delete airport by id: http://localhost:4567/airports/9999
- get all airports: http://localhost:4567/airports

You can run 

    mvn clean install

to build the code, create a jar file and then a Docker image using buildah. An image is build and can be run like this:

    podman run --name "airportrest-test" --rm -it -p 5000:4567 airportrest:latest

The image can of course also be run using Docker.

last update: uwe.geercken@web.de - 2024-08-25