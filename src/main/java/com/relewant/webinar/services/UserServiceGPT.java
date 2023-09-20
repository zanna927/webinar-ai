package com.relewant.webinar.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relewant.webinar.entities.UserGPT;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceGPT {

    private final ResourceLoader resourceLoader;

    public UserServiceGPT(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<UserGPT> getUsers() throws IOException {
        // Ottieni un oggetto Resource per il file JSON
        Resource resource = new ClassPathResource("users.json");

        // Leggi il file JSON e converti i dati
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(resource.getInputStream());

        // Estrai l'array "users" dal nodo radice
        JsonNode usersNode = rootNode.get("users");

        if (usersNode != null && usersNode.isArray()) {
            // Deserializza l'array "users" in una lista di oggetti UserGPT
            return objectMapper.convertValue(usersNode, new TypeReference<>() {
            });
        } else {
            // Il campo "users" non è presente o non è un array
            // Gestisci l'errore o restituisci una lista vuota o un altro valore appropriato
            return List.of(); // Ritorna una lista vuota in caso di errore
        }
    }
}
