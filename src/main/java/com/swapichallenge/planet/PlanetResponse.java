package com.swapichallenge.planet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanetResponse extends ResourceSupport {
    private String name;
    private String climate;
    private String terrain;
    private int movieAppearances;

    public static PlanetResponse fromEntity(Planet planet) {
        PlanetResponse response = new PlanetResponse(planet.getName(),
                planet.getClimate(),
                planet.getTerrain(),
                planet.getMovieAppearances());

        response.add(linkTo(methodOn(PlanetController.class).getPlanet(planet.getId()))
                .withSelfRel());

        return response;
    }


}
