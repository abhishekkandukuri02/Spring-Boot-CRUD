package com.example.sampleproject.controller;

import com.example.sampleproject.model.Entity;
import com.example.sampleproject.service.EntityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/entities")
    public class EntityController {

        private final EntityService service;

        public EntityController(EntityService service) {
            this.service = service;
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Entity add(@RequestBody Entity entity) {
            return service.add(entity);
        }

        @DeleteMapping
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void remove(@RequestBody Entity entity) {
            service.remove(entity);
        }

        @DeleteMapping("/all")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void removeAll() {
            service.removeAll();
        }

        @GetMapping("/{id}")
        public Optional<Entity> get(@PathVariable Long id) {
            return service.get(id);
        }

        @GetMapping
        public List<Entity> getAll() {
            return service.getAll();
        }
    }
