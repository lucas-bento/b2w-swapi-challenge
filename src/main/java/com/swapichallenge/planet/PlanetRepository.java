package com.swapichallenge.planet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, String> {
    Optional<Planet> findByNameIgnoreCase(String name);
}
