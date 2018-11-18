package com.swapichallenge.planet;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@Valid
public class PlanetRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String climate;

    @NotBlank
    private String terrain;

    public Planet asEntity() {
        return new Planet(name,climate,terrain);
    }
}
