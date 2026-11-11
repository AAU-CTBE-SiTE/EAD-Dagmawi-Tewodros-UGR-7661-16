package com.craft.spring_boot_crud;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    // Initial dummy data using ArrayList
    private List<Actor> actors = new ArrayList<>(List.of(
            new Actor(1L, "Tom Cruise"),
            new Actor(2L, "John Smith")
    ));

    // 1. GET: Get all actors
    @GetMapping
    public List<Actor> getAllActors() {
        return actors;
    }

    // 2. GET: Get actor by ID
    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable Long id) {
        for (Actor actor : actors) {
            if (actor.getId().equals(id)) {
                return actor;
            }
        }
        return null;
    }

    // 3. POST: Create / Add a new actor
    @PostMapping
    public Actor createActor(@RequestBody Actor actor) {
        actors.add(actor);
        return actor;
    }

    // 4. PUT: Update an actor by ID
    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable Long id, @RequestBody Actor updatedActor) {
        for (Actor actor : actors) {
            if (actor.getId().equals(id)) {
                actor.setName(updatedActor.getName());
                return actor;
            }
        }
        return null;
    }

    // 5. DELETE: Delete an actor by ID
    @DeleteMapping("/{id}")
    public String deleteActorById(@PathVariable Long id) {
        boolean removed = actors.removeIf(actor -> actor.getId().equals(id));
        if (removed) {
            return "Actor deleted successfully";
        }
        return "Actor not found";
    }
}