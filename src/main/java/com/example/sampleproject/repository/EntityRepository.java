package com.example.sampleproject.repository;


import com.example.sampleproject.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepository extends JpaRepository<Entity, Long> {
    
}

