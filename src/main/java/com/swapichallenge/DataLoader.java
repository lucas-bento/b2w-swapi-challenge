package com.swapichallenge;

import com.swapichallenge.planet.Planet;
import com.swapichallenge.planet.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private PlanetRepository repository;

    public void run(ApplicationArguments args){
        Planet planet = new Planet("1", "Alderaan", "temperate", "grasslands", 1);
        repository.save(planet);
    }
}