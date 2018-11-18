package com.swapichallenge.planet;

public class PlanetAlreadyExistsException extends RuntimeException {
    private final Planet planet;

    public PlanetAlreadyExistsException(Planet planet) {
        this.planet = planet;
    }

    public Planet getPlanet() {
        return planet;
    }
}
