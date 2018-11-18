package com.swapichallenge.planet;

import lombok.Data;

@Data
public class Planet {
    private String id;
    private String name;
    private String climate;
    private String terrain;
    private int movieAppearances;

    public Planet(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }
}
