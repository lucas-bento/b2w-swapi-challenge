package com.swapichallenge.planet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CONFLICT;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @PostMapping("/")
    public ResponseEntity<PlanetResponse> insertPlanet(@RequestBody @Valid PlanetRequest request) {

        PlanetResponse planet = planetService.insertPlanet(request);

        return ResponseEntity.created( planet.getLink("self").getTemplate().expand())
                             .body(planet);
    }

    @DeleteMapping("/{id}")
    public void deletePlanet(@PathVariable("id") String id) {
        planetService.delete(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<PlanetResponse>> updatePlanet(){
        List<PlanetResponse> planetResponses = planetService.findAll();

        return ResponseEntity.ok(planetResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanetResponse> getPlanet(@PathVariable("id") String id) {

        Optional<PlanetResponse> optional = planetService.getPlanet(id);

        ResponseEntity<PlanetResponse> responseEntity = optional.map(ResponseEntity::ok)
                                                                .orElseGet(() -> ResponseEntity.notFound().build());

        return responseEntity;
    }

    @ResponseStatus(value=CONFLICT, reason="Planet already exists")
    @ExceptionHandler(PlanetAlreadyExistsException.class)
    public void conflict(PlanetAlreadyExistsException e) {
    }
}
