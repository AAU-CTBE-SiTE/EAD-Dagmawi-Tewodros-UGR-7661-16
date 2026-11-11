package com.craft.spring_boot_crud;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    // In-memory data using an ArrayList
    private final List<Actor> actors = new ArrayList<>(List.of(
            new Actor(1L, "Tom Cruise"),
            new Actor(2L, "John Smith")
    ));

    // READ all
    @GetMapping
    public List<Actor> getAllActors() {
        return actors;
    }

    // READ one (throws 404 if missing, so the aspect's @AfterThrowing can be seen)
    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable Long id) {
        for (Actor actor : actors) {
            if (actor.getId().equals(id)) {
                return actor;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor " + id + " not found");
    }

    // CREATE
    @PostMapping
    public Actor createActor(@RequestBody Actor actor) {
        actors.add(actor);
        return actor;
    }

    // UPDATE
    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable Long id, @RequestBody Actor updatedActor) {
        for (Actor actor : actors) {
            if (actor.getId().equals(id)) {
                actor.setName(updatedActor.getName());
                return actor;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor " + id + " not found");
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteActorById(@PathVariable Long id) {
        boolean removed = actors.removeIf(actor -> actor.getId().equals(id));
        if (!removed) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor " + id + " not found");
        }
        return "Actor deleted successfully";
    }
}
