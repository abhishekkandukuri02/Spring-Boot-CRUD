package com.example.sampleproject;

import com.example.sampleproject.model.Entity;
import com.example.sampleproject.service.EntityService;
import com.example.sampleproject.repository.EntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EntityServiceTest {

    @Mock
    private EntityRepository repository;

    @InjectMocks
    private EntityService service;

    private Entity entity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        entity = new Entity();
        entity.setId(1L);
        entity.setName("Test Entity");
    }

    @Test
    void testAddEntity() {
        when(repository.save(entity)).thenReturn(entity);
        Entity savedEntity = service.add(entity);
        assertNotNull(savedEntity);
        assertEquals(entity.getName(), savedEntity.getName());
    }

    @Test
    void testRemoveEntity() {
        doNothing().when(repository).delete(entity);
        service.remove(entity);
        verify(repository, times(1)).delete(entity);
    }

    @Test
    void testGetEntity() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        Optional<Entity> retrievedEntity = service.get(1L);
        assertTrue(retrievedEntity.isPresent());
        assertEquals(entity.getName(), retrievedEntity.get().getName());
    }

    @Test
    void testGetAllEntities() {
        List<Entity> entities = List.of(entity);
        when(repository.findAll()).thenReturn(entities);
        List<Entity> result = service.getAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void testRemoveAllEntities() {
        doNothing().when(repository).deleteAll();
        service.removeAll();
        verify(repository, times(1)).deleteAll();
    }
}
