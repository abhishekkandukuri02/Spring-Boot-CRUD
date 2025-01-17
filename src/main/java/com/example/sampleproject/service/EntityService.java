package com.example.sampleproject.service;

import com.example.sampleproject.model.Entity;
import com.example.sampleproject.repository.EntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EntityService {

    private final EntityRepository repository;

    public EntityService(EntityRepository repository) {
        this.repository = repository;
    }

    public Entity add(Entity entity) {
        return repository.save(entity);
    }

    public void remove(Entity entity) {
        repository.delete(entity);
    }

    public void removeAll() {
        repository.deleteAll();
    }

    public Optional<Entity> get(Long id) {
        return repository.findById(id);
    }

    public List<Entity> getAll() {
        return repository.findAll();
    }
}
