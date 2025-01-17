package com.example.sampleproject;

import com.example.sampleproject.controller.EntityController;
import com.example.sampleproject.model.Entity;
import com.example.sampleproject.service.EntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EntityController.class)
class EntityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntityService service;

    private Entity sampleEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleEntity = new Entity();
        sampleEntity.setId(1L);
        sampleEntity.setName("Sample Entity");
    }

    @Test
    void testAdd() throws Exception {
        when(service.add(any(Entity.class))).thenReturn(sampleEntity);

        mockMvc.perform(post("/entities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Sample Entity\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Sample Entity"));
    }

    @Test
    void testRemove() throws Exception {
        doNothing().when(service).remove(any(Entity.class));

        mockMvc.perform(delete("/entities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Sample Entity\"}"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testRemoveAll() throws Exception {
        doNothing().when(service).removeAll();

        mockMvc.perform(delete("/entities/all"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetAll() throws Exception {
        when(service.getAll()).thenReturn(Collections.singletonList(sampleEntity));

        mockMvc.perform(get("/entities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Sample Entity"));
    }

    @Test
    void testGetById() throws Exception {
        when(service.get(1L)).thenReturn(Optional.of(sampleEntity));

        mockMvc.perform(get("/entities/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sample Entity"));
    }

    @Test
    void testGetAllWhenIdAll() throws Exception {
        when(service.getAll()).thenReturn(Collections.singletonList(sampleEntity));

        mockMvc.perform(get("/entities/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Sample Entity"));
    }


}