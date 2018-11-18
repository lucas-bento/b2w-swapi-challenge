package com.swapichallenge.planet;

import com.swapichallenge.planet.external.SWAPIClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanetService {

    private PlanetRepository repository;
    private SWAPIClient swapi;

    public PlanetService(PlanetRepository repository, SWAPIClient swapi){
        this.repository = repository;
        this.swapi = swapi;
    }

    public PlanetResponse insertPlanet(PlanetRequest request) {

        repository.findByNameIgnoreCase(request.getName())
                  .ifPresent( planet -> {
                      throw new PlanetAlreadyExistsException(planet);
                  } );


        Planet planetRequested = request.asEntity();

        int movieAppearances = swapi.getMovieAppearances(request.getName());
        planetRequested.setMovieAppearances(movieAppearances);

        Planet planedCreated  = repository.save(request.asEntity());

        return PlanetResponse.fromEntity(planedCreated);
    }

    public Optional<PlanetResponse> getPlanet(String id) {
        Optional<Planet> planet = repository.findById(id);

        return planet.map(PlanetResponse::fromEntity);
    }
}
