package com.swapichallenge.planet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@Valid
@NoArgsConstructor
@AllArgsConstructor
public class PlanetRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String climate;

    @NotBlank
    private String terrain;

    public Planet asEntity() {
        return new Planet(name, climate, terrain);
    }
}
