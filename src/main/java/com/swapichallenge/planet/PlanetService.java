package com.swapichallenge.planet;

import com.swapichallenge.planet.external.SWAPIClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PlanetService {

    private PlanetRepository repository;
    private SWAPIClient swapi;

    public PlanetService(PlanetRepository repository, SWAPIClient swapi){
        this.repository = repository;
        this.swapi = swapi;
    }

    public PlanetResponse insertPlanet(PlanetRequest request) throws PlanetAlreadyExistsException{

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

    public void delete(String id) {
        repository.deleteById(id);
    }

    public List<PlanetResponse> findAll() {
        Iterable<Planet> planets = repository.findAll();
        return StreamSupport.stream(planets.spliterator(), false)
                            .map(planet -> PlanetResponse.fromEntity(planet))
                            .collect(Collectors.toList());
    }
}
